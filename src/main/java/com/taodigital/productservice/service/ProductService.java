package com.taodigital.productservice.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.taodigital.productservice.dto.ProductDto;
import com.taodigital.productservice.entity.Product;
import com.taodigital.productservice.enums.Status;
import com.taodigital.productservice.repo.ProductRepo;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ApprovalQueueService approvalQueueService;

	public List<Product> getActiveProducts() {
		return productRepo.findAll().stream().filter(product -> product.getStatus() == Status.ACTIVE)
				.sorted(Comparator.comparing(Product::getId).reversed()).toList();
	}

	public List<Product> searchProducts(String productName, Double minPrice, Double maxPrice, LocalDate minPostedDate,
			LocalDate maxPostedDate) {
		StringBuilder query = new StringBuilder("select p from Product p ");
		boolean isInputPresent = false;
		if (!ObjectUtils.isEmpty(productName.trim())) {
			if (!isInputPresent) {
				isInputPresent = true;
				query.append("where ");
			}
			query.append("p.name like '"+productName+"' ");
			
		}
		if (minPrice != null && maxPrice != null) {
			if (!isInputPresent) {
				isInputPresent = true;
				query.append("where ");
			}
			query.append("p.price between "+minPrice+" AND "+maxPrice);
		}
		
		if (minPostedDate != null && maxPostedDate != null) {
			if (!isInputPresent) {
				isInputPresent = true;
				query.append("where ");
			}
			query.append("p.postedDate between "+minPostedDate+" AND "+maxPostedDate);
		}
		
		return productRepo.searchProducts(query.toString());
	}

	public Product createProduct(ProductDto productDto) throws Exception {
		Product product = new Product();
		if (productDto.getPrice() > 10000) {
			throw new Exception("Product price should not higher than 10000");
		}
		
		
		product.setName(productDto.getName());
		product.setStatus(productDto.getStatus());
		product.setPrice(productDto.getPrice());
		product.setPostedDate(LocalDate.now());
		product = productRepo.save(product);
		if (productDto.getPrice() > 5000) {
			product.setStatus(Status.APPROVAL_QUEUE);
			approvalQueueService.addApprovalQueue(product);
		}
		return product;
	}

	public Product updateProduct(ProductDto productDto, Long productId) {
		Product product = productRepo.findById(productId).get();
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setStatus(productDto.getStatus());
		product = productRepo.save(product);
		if (productDto.getPrice() > (product.getPrice() / 2) ) {
			product.setStatus(Status.APPROVAL_QUEUE);
			approvalQueueService.addApprovalQueue(product);
		}
		return product;
	}

	public String deleteProduct(Long productId) {
		Product product = productRepo.findById(productId).get();
		product.setStatus(Status.DELETE);
		approvalQueueService.addApprovalQueue(product);
		return "DELETED";
	}

	public Product updateProduct(Product product) {
		return productRepo.save(product);
	}

	

}
