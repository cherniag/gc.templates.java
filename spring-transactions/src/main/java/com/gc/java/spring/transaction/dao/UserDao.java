package com.gc.java.spring.transaction.dao;

import com.gc.java.spring.transaction.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by gc on 19.01.2016.
 */
@Service
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    public User findById(int id){
        return entityManager.find(User.class, id);
    }


}
