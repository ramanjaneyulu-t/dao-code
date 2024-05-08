package com.taodigital.productservice.repo.impl;

import java.util.List;

import com.taodigital.productservice.entity.Product;
import com.taodigital.productservice.repo.ProductCustomRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class ProductCustomRepoImpl implements ProductCustomRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Product> searchProducts(String query) {

		return entityManager.createQuery(query, Product.class).getResultList();
	}

}
