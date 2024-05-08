package com.taodigital.productservice.dto;

import com.taodigital.productservice.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	private String name;
	private Double price;
	private Status status;
}
