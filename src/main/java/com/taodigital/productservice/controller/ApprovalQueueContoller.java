package com.taodigital.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taodigital.productservice.entity.Product;
import com.taodigital.productservice.service.ApprovalQueueService;

@RestController
@RequestMapping("/api/products/approval-queue")
public class ApprovalQueueContoller {
	
	@Autowired
	private ApprovalQueueService approvalQueueService;
	
	@GetMapping("/approval-queue")
	public ResponseEntity<?> getApprovalQueueProducts() {
		List<Product> products = approvalQueueService.getApprovalQueueProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PutMapping("/approval-queue/{approvalId}/approve")
	public ResponseEntity<?> approveProduct(@PathVariable Long approvalId) {
		Product product = approvalQueueService.approveProduct(approvalId);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@PutMapping("/approval-queue/{approvalId}/reject")
	public ResponseEntity<?> rejectProduct(@PathVariable Long approvalId) {
		approvalQueueService.rejectProduct(approvalId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
