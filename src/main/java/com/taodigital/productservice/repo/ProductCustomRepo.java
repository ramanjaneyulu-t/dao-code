package com.taodigital.productservice.repo;

import java.util.List;

import com.taodigital.productservice.entity.Product;

public interface ProductCustomRepo {
	List<Product> searchProducts(String query);
}
