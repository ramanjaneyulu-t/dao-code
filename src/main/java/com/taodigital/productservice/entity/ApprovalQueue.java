package com.taodigital.productservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "approval_queue")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalQueue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "request_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime requestDate;
	
	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	
}
