package com.empresa.app.service;

import com.empresa.app.exception.RecursoNoEncontradoException;
import com.empresa.app.model.Producto;
import com.empresa.app.repository.ProductoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto;

    @Before
    public void setUp() {
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto Test");
        producto.setPrecio(100.0);
        producto.setActivo(true);
    }

    @Test
    public void listarActivos_debeRetornarListaNoVacia() {
        when(productoRepository.findByActivoTrue()).thenReturn(Arrays.asList(producto));
        List<Producto> resultado = productoService.listarActivos();
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }

    @Test
    public void buscarPorId_existente_debeRetornarProducto() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        Optional<Producto> resultado = productoService.buscarPorId(1L);
        assertTrue(resultado.isPresent());
        assertEquals("Producto Test", resultado.get().getNombre());
    }

    @Test(expected = RecursoNoEncontradoException.class)
    public void actualizar_noExistente_debeLanzarExcepcion() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());
        productoService.actualizar(99L, producto);
    }
}
