package com.empresa.api.config;

import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.servlet.ServletApplicationContext;
import org.apache.tiles.startup.DefaultTilesInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TilesInitListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(TilesInitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        try {
            log.info("Inicializando Apache Tiles 3...");

            // Indicar la ruta del tiles.xml como atributo del contexto
            // usando el nombre de parametro que Tiles 3 reconoce internamente
            servletContext.setAttribute(
                "org.apache.tiles.impl.BasicTilesContainer.DEFINITIONS_CONFIG",
                "/WEB-INF/tiles/tiles.xml"
            );

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
        log.info("Contexto destruido, Tiles finalizado.");
    }
}
