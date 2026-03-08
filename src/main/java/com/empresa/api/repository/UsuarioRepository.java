package com.empresa.api.repository;

import com.empresa.api.model.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Optional<Usuario> findByUsername(String username) {
        Query<Usuario> query = sessionFactory.getCurrentSession()
            .createQuery("FROM Usuario u WHERE u.username = :username AND u.activo = true", Usuario.class);
        query.setParameter("username", username);
        return query.uniqueResultOptional();
    }

    public Usuario save(Usuario usuario) {
        sessionFactory.getCurrentSession().saveOrUpdate(usuario);
        return usuario;
    }
}
