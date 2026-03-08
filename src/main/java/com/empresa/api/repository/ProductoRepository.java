package com.empresa.api.repository;

import com.empresa.api.model.Producto;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Producto> findAll() {
        return sessionFactory.getCurrentSession()
            .createQuery("FROM Producto p ORDER BY p.nombre", Producto.class)
            .list();
    }

    public List<Producto> findByActivoTrue() {
        return sessionFactory.getCurrentSession()
            .createQuery("FROM Producto p WHERE p.activo = true ORDER BY p.nombre", Producto.class)
            .list();
    }

    public Optional<Producto> findById(Long id) {
        Producto p = sessionFactory.getCurrentSession().get(Producto.class, id);
        return Optional.ofNullable(p);
    }

    public List<Producto> findByCategoria(String categoria) {
        Query<Producto> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Producto p WHERE p.categoria = :cat AND p.activo = true", Producto.class);
        query.setParameter("cat", categoria);
        return query.list();
    }

    public List<Producto> buscarPorNombre(String termino) {
        Query<Producto> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Producto p WHERE LOWER(p.nombre) LIKE :termino AND p.activo = true", Producto.class);
        query.setParameter("termino", "%" + termino.toLowerCase() + "%");
        return query.list();
    }

    public Producto save(Producto producto) {
        sessionFactory.getCurrentSession().saveOrUpdate(producto);
        return producto;
    }

    public void delete(Long id) {
        Producto p = sessionFactory.getCurrentSession().get(Producto.class, id);
        if (p != null) {
            sessionFactory.getCurrentSession().delete(p);
        }
    }
}
