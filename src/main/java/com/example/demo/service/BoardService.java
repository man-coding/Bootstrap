package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;

public interface BoardService {

	// 게시물 등록
	int register(ProductDTO dto, MultipartFile file);

	// 게시물 목록조회
//	List<ProductDTO> getList();
	Page<ProductDTO> getList(int pageNumber);

	// 게시물 상세조회
	ProductDTO read(int no);

	// 게시물 수정
	void modify(ProductDTO dto, MultipartFile file);

	int remove(int no);

	// dto -> entity 변환 메소드
	default Product dtoToEntity(ProductDTO dto) {
		Product entity = Product.builder().no(dto.getNo()).productName(dto.getProductName()).price(dto.getPrice())
				.color(dto.getColor()).content(dto.getContent()).seller(dto.getSeller()).fileName(dto.getFileName())
				.filePath(dto.getFilePath()).build();
		return entity;
		// db에 파일패스랑 파일네임 저장되어야 하므로 받아와야 함.
	}

	// entity -> dto 변환
	default ProductDTO entityToDto(Product entity) {
		ProductDTO dto = ProductDTO.builder().no(entity.getNo()).productName(entity.getProductName())
				.price(entity.getPrice()).color(entity.getColor()).content(entity.getContent())
				.seller(entity.getSeller()).regDate(entity.getRegDate()).modDate(entity.getModDate())
				.fileName(entity.getFileName()).filePath(entity.getFilePath()).build();
		// Product 엔티티에서 ProductDTO로 변환할 때, 엔티티에 저장된 fileName과 filePath 정보를 dto로 전달해야 함.
		return dto;
	}

}
