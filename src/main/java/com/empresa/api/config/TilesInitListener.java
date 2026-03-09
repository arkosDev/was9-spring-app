package com.empresa.api.config;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.impl.BasicTilesContainer;
import org.apache.tiles.preparer.factory.BasicPreparerFactory;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.servlet.ServletApplicationContext;
import org.apache.tiles.startup.DefaultTilesInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Inicializa Apache Tiles 3 manualmente al arrancar el WAR.
 *
 * Se usa un listener propio en lugar de los listeners de Tiles
 * para evitar conflictos de ClassLoader en JBoss EAP 7.4.
 * Todas las clases de Tiles vienen empacadas dentro del WAR
 * (scope compile en pom.xml), por lo que este listener las
 * encuentra sin depender de modulos del servidor.
 */
public class TilesInitListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(TilesInitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        try {
            log.info("Inicializando Apache Tiles 3...");

            // Leer la ruta del tiles.xml desde context-param (definido en web.xml)
            String definitions = servletContext.getInitParameter(
                "org.apache.tiles.impl.BasicTilesContainer.DEFINITIONS_CONFIG"
            );
            if (definitions != null) {
                servletContext.setAttribute(
                    BasicTilesContainer.DEFINITIONS_CONFIG, definitions
                );
            }

            // Inicializar via DefaultTilesInitializer — bootstrap estándar de Tiles 3
            ApplicationContext applicationContext =
                new ServletApplicationContext(servletContext);

            DefaultTilesInitializer initializer = new DefaultTilesInitializer();
            initializer.initialize(applicationContext);

            log.info("Apache Tiles 3 inicializado correctamente.");

        } catch (Exception e) {
            log.error("Error al inicializar Apache Tiles 3", e);
            throw new RuntimeException("No se pudo inicializar Tiles", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ApplicationContext applicationContext =
                new ServletApplicationContext(sce.getServletContext());
            TilesContainer container = TilesAccess.getContainer(applicationContext);
            if (container != null) {
                log.info("Destruyendo contenedor de Tiles...");
            }
        } catch (Exception e) {
            log.warn("Error al destruir Tiles", e);
        }
    }
}
