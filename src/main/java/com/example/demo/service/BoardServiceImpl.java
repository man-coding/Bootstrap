package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

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

}