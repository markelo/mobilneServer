package com.seba.mobile.controller;

/**
 * Created by sebastian on 31.10.15.
 */

import com.google.common.base.Optional;
import com.seba.mobile.dao.interfaces.UserDAO;
import com.seba.mobile.entities.Product;
import com.seba.mobile.entities.User;
import com.seba.mobile.repositories.ProductRepository;
import com.seba.mobile.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ServerController {
    Optional<String> logedUserName;
    @Inject
    private UserRepository userRepository;

    @Inject
    private ProductRepository productRepository;

    @Autowired
    private UserDAO userDAO;

    public ServerController() {
        logedUserName = Optional.absent();
    }

    @RequestMapping(value = "/test")
    public LoginRequest test() {
        return new LoginRequest("seba", "seba");
    }

    @RequestMapping(value = "/greeting")
    public void getGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        final User user = new User();
        user.setLogin(name);
        userRepository.save(user);
        Product product = new Product();
        product.setName("Se");
        product.setQuantity(12);
        user.getProducts().add(product);
        productRepository.save(product);
    }

    @RequestMapping(value = "/getUser", consumes = "application/json")
    public User getUser(@RequestParam(value = "login", defaultValue = "") String login) {
        Optional<User> optional = userDAO.find(login);
        return optional.orNull();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean register(
            @RequestBody(required = true) LoginRequest loginRequest) {
        User user = new User();
        user.setLogin(loginRequest.getUserName());
        user.setPassword(loginRequest.getPassword());
        return userDAO.persist(user);
    }

    @RequestMapping(value = "/login")
    public LoginRespone login(
            @RequestBody(required = true) LoginRequest loginRequest) {
        Optional<User> optional = userDAO.find(loginRequest.getUserName());
        if (!optional.isPresent())
            return new LoginRespone(false, "There is no user with user name: " + loginRequest.getUserName());
        User user = optional.get();
        if(!user.getPassword().equals(loginRequest.getPassword())) {
            return new LoginRespone(false, "Wrong password or user name");
        }
        logedUserName = Optional.of(user.getLogin());
        return new LoginRespone(true, "");
    }

    @RequestMapping(value = "/getProducts")
    public ActionResponse getProducts() {
        if(!logedUserName.isPresent()) {
            return new ActionResponse(false, "User is not logged in", Collections.emptyList());
        }

        Optional<User> optional = userDAO.find(logedUserName.get());
        if (!optional.isPresent())
            return new ActionResponse(false, "User with given name must have been deleted", Collections.emptyList());
        return new ActionResponse(true, "", optional.get().getProducts());
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST, consumes = "application/*")
    public ActionResponse addNewProduct(@RequestBody Product product) {
        if(!logedUserName.isPresent()) {
            return new ActionResponse(false, "User is not logged in", null);
        }
        Optional<User> optional = userDAO.find(logedUserName.get());
        if(!optional.isPresent())
            return new ActionResponse(false, "User with given name must have been deleted", Collections.emptyList());
        User user = optional.get();
        user.getProducts().add(product);
        return new ActionResponse(true, "", user.getProducts());
    }

    @RequestMapping(value = "/increaseProduct", method = RequestMethod.GET)
    public ActionResponse increaseProduct(@RequestBody(required = true) IncreaseProductRequest request) {
        if(!logedUserName.isPresent()) {
            return new ActionResponse(false, "User is not logged in", null);
        }
        Optional<User> optional = userDAO.find(logedUserName.get());
        if (!optional.isPresent())
            return new ActionResponse(false, "User with given name must have been deleted", null);
        User user = optional.get();
        user.getProducts().stream().filter(product -> product.getName().equals(request.getName()))
                .forEach(product -> product.increase(request.getQuantity()));
        user.setProducts(user.getProducts().stream().filter(pr->pr.getQuantity() > 0).collect(Collectors.toList()));
        return new ActionResponse(true, "", user.getProducts());
    }

    @RequestMapping(value = "/deleteProduct", method = RequestMethod.DELETE)
    public ActionResponse deleteProduct(@RequestParam(value = "name", required = true) String name) {
        if(!logedUserName.isPresent()) {
            return new ActionResponse(false, "User is not logged in", null);
        }
        Optional<User> optional = userDAO.find(logedUserName.get());
        if (!optional.isPresent())
            return new ActionResponse(false, "User with given name must have been deleted", null);

        User user = optional.get();
        for(Product product : user.getProducts()) {
            if(product.getName().equals(name)) {
                user.getProducts().remove(product);
                break;
            }
        }
        return new ActionResponse(true, "", user.getProducts());
    }

    @RequestMapping(value = "/api/user")
    public String authorized() {
        return "Hello User";
    }
}
