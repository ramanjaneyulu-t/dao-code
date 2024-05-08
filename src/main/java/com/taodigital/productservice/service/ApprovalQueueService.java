package com.taodigital.productservice.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taodigital.productservice.entity.ApprovalQueue;
import com.taodigital.productservice.entity.Product;
import com.taodigital.productservice.enums.Status;
import com.taodigital.productservice.repo.ApprovalQueueRepo;

@Service
@Transactional
public class ApprovalQueueService {
	
	@Autowired
	private ApprovalQueueRepo approivalQueueRepo;
	
	@Autowired
	@Lazy
	private ProductService productService;

	public List<Product> getApprovalQueueProducts() {
		List<ApprovalQueue> list = approivalQueueRepo.findAll();
		return list.stream()
				.sorted(Comparator.comparing(ApprovalQueue::getRequestDate))
				.map(obj -> obj.getProduct())
		.toList();
	}

	public Product approveProduct(Long approvalId) {
		
		Product product = approivalQueueRepo.findById(approvalId)
				.get().getProduct();
		product.setStatus(Status.ACTIVE);
		product = productService.updateProduct(product);
		approivalQueueRepo.deleteById(approvalId);
		return product;
	}

	public void rejectProduct(Long approvalId) {
		approivalQueueRepo.deleteById(approvalId);
	}

	public void addApprovalQueue(Product product) {
		ApprovalQueue queue = new ApprovalQueue();
		queue.setProduct(product);
		queue.setRequestDate(LocalDateTime.now());
	}
	
	
}
