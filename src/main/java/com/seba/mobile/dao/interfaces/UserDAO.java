package com.seba.mobile.dao.interfaces;

import com.google.common.base.Optional;
import com.seba.mobile.entities.User;

/**
 * Created by sebastian on 31.10.15.
 */
public interface UserDAO {
    boolean persist(final User user);
    Optional<User> find (final String login);
}
