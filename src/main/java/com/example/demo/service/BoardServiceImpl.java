package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service // 서비스 클래스로 지정해줘야 됨...
public class BoardServiceImpl implements BoardService {

	@Autowired
	ProductRepository repository;

	@Override
	public int register(ProductDTO dto) {

		Product entity = dtoToEntity(dto);

		repository.save(entity);

		int newNo = entity.getNo();
		return newNo;

	}

	@Override
	public List<ProductDTO> getList() {

		// db에서 게시물 가져오기
		List<Product> result = repository.findAll();

		// 리스트 생성
		List<ProductDTO> list = new ArrayList<>();

		for (int i = 0; i < result.size(); i++) {
			ProductDTO dto = entityToDto(result.get(i));
			list.add(dto);
		}

		return list;
	}

	@Override
	public ProductDTO read(String productName) {
		Optional<Product> result = repository.findByProductName(productName);

		if (result.isPresent()) {
			Product product = result.get();

			ProductDTO dto = entityToDto(product);
			return dto;
		}
		return null;
	}

	@Override
	public void modify(ProductDTO dto) {
		Optional<Product> result = repository.findByProductName(dto.getProductName());
		if (result.isPresent()) {
			Product entity = result.get();

			entity.setProductName(dto.getProductName());
			entity.setPrice(dto.getPrice());
			entity.setColor(dto.getColor());
			entity.setContent(dto.getContent());
			entity.setSeller(dto.getSeller());

			repository.save(entity);
		}
	}

}
