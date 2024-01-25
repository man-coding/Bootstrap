package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_product")

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int no; // 상품번호

	@Column(length = 30, nullable = false)
	String productName;

	@Column(nullable = false)
	int price;

	@Column(length = 10, nullable = true)
	String color;

	@Column(length = 255, nullable = false)
	String content;

	@Column(length = 10, nullable = false)
	String seller;

	String fileName;
	String filePath;
}
