package com.taodigital.productservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taodigital.productservice.entity.ApprovalQueue;

public interface ApprovalQueueRepo extends JpaRepository<ApprovalQueue, Long>{

}
