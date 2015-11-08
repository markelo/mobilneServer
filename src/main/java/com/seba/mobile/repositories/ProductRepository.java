package com.seba.mobile.repositories;

import com.seba.mobile.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sebastian on 31.10.15.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
