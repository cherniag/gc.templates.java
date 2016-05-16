package com.gc.java.spring.transaction.dao;

import com.gc.java.spring.transaction.model.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gc on 19.01.2016.
 */
public interface UserDao {

    @Transactional
    User save(User user);

    User findById(int id);
}
