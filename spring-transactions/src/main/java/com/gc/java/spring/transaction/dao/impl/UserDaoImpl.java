package com.gc.java.spring.transaction.dao.impl;

import com.gc.java.spring.transaction.dao.UserDao;
import com.gc.java.spring.transaction.model.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by gc on 19.01.2016.
 */
@Service
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User findById(int id){
        return entityManager.find(User.class, id);
    }


}
