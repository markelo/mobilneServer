package com.seba.mobile.dao.implementations;

import com.google.common.base.Optional;
import com.seba.mobile.dao.interfaces.UserDAO;
import com.seba.mobile.entities.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sebastian on 31.10.15.
 */
@Repository
public class UserDAOImpl implements UserDAO {
    private Map<String, User> users;

    public UserDAOImpl() {
        this.users = new HashMap<>();
    }
    @Override
    public boolean persist(final User user) {
        if(userExists(user.getLogin()))
            return false;
        users.put(user.getLogin(), user);
        return true;
    }

    @Override
    public Optional<User> find(final String login) {
        return Optional.fromNullable(users.get(login));
    }

    private boolean userExists(final String login) {
        return users.containsKey(login);
    }
}
