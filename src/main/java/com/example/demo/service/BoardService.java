package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;

public interface BoardService {

	// 게시물 등록
	int register(ProductDTO dto);

	// 게시물 목록조회
	List<ProductDTO> getList();

	// dto -> entity 변환 메소드
	default Product dtoToEntity(ProductDTO dto) {
		Product entity = Product.builder().no(dto.getNo()).productName(dto.getProductName()).price(dto.getPrice())
				.color(dto.getColor()).content(dto.getContent()).build();
		return entity;
	}

	// entity -> dto 변환
	default ProductDTO entityToDto(Product entity) {
		ProductDTO dto = ProductDTO.builder().no(entity.getNo()).productName(entity.getProductName())
				.price(entity.getPrice()).color(entity.getColor()).content(entity.getContent()).build();

		return dto;
	}
}
