package com.gc.java.spring.transaction;

import com.gc.java.spring.transaction.dao.UserDao;
import com.gc.java.spring.transaction.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by gc on 19.01.2016.
 */
public class Start {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        UserDao userDao = applicationContext.getBean(UserDao.class);

        User saved = userDao.save(new User("first"));
        User found = userDao.findById(saved.getId());
    }

}
