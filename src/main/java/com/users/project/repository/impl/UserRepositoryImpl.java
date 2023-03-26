package com.users.project.repository.impl;

import com.users.project.model.User;
import com.users.project.repository.UserRepository;
import com.users.project.repository.exception.UserRepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    private static final Logger LOGGER = LogManager.getRootLogger();
    public static final String GET_USERS_QUERY = "FROM User ORDER BY email ASC";
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) throws UserRepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(user);
        } catch (HibernateException e) {
            LOGGER.warn("Problems with saving info to data or another exception occurred: {} - {}", e.getClass().getSimpleName(), e.getMessage());
            throw new UserRepositoryException("Database saving info problems", e);
        }
    }

    @Override
    public List<User> getAllUsers(int page, int size) throws UserRepositoryException {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session.createQuery(GET_USERS_QUERY, User.class)
                    .setFirstResult((page - 1) * size)
                    .setMaxResults(size)
                    .list();
        } catch (HibernateException e) {
            LOGGER.warn("Problems with getting info from data or another exception occurred: {} - {}", e.getClass().getSimpleName(), e.getMessage());
            throw new UserRepositoryException("Database getting info problems", e);
        }

    }
}
