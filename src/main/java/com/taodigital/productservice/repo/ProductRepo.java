package com.taodigital.productservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taodigital.productservice.entity.Product;
import com.taodigital.productservice.enums.Status;

public interface ProductRepo extends JpaRepository<Product, Long>, ProductCustomRepo {
	@Query(value = "select p from Product p where p.status=:status")
	List<Product> getApprovalQueueProducts(Status status);
}
