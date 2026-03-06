package com.empresa.app.repository;

import com.empresa.app.model.Producto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA usando EntityManager puro.
 * El proveedor JPA (IBM OpenJPA) es suministrado por WAS 9.
 * No depende de Hibernate ni de Spring Data JPA.
 */
@Repository
public class ProductoRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Producto> findAll() {
        return em.createQuery("SELECT p FROM Producto p", Producto.class)
                 .getResultList();
    }

    public List<Producto> findByActivoTrue() {
        return em.createQuery(
            "SELECT p FROM Producto p WHERE p.activo = true", Producto.class)
                 .getResultList();
    }

    public Optional<Producto> findById(Long id) {
        return Optional.ofNullable(em.find(Producto.class, id));
    }

    public List<Producto> findByNombreContaining(String nombre) {
        TypedQuery<Producto> query = em.createQuery(
            "SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE :nombre AND p.activo = true",
            Producto.class);
        query.setParameter("nombre", "%" + nombre.toLowerCase() + "%");
        return query.getResultList();
    }

    public List<Producto> findByRangoPrecio(Double min, Double max) {
        TypedQuery<Producto> query = em.createQuery(
            "SELECT p FROM Producto p WHERE p.precio BETWEEN :min AND :max AND p.activo = true",
            Producto.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        return query.getResultList();
    }

    public Producto save(Producto producto) {
        if (producto.getId() == null) {
            em.persist(producto);
            return producto;
        } else {
            return em.merge(producto);
        }
    }

    public void delete(Producto producto) {
        em.remove(em.contains(producto) ? producto : em.merge(producto));
    }
}
