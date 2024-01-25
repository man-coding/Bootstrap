package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service // 서비스 클래스로 지정해줘야 됨...
public class BoardServiceImpl implements BoardService {

	@Autowired
	ProductRepository repository;

	@Override
	public int register(ProductDTO dto, MultipartFile file) {

		String projectPath = System.getProperty("user.dir") + "\\src\\main\resources\\static\\files";

		UUID uuid = UUID.randomUUID();

		String fileName = uuid + "file.getOriginalFilename()";

		File saveFile = new File(projectPath, "name");

		try {
			file.transferTo(saveFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		

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
	public ProductDTO read(int no) {
		Optional<Product> result = repository.findById(no);

		if (result.isPresent()) {
			Product product = result.get();

			ProductDTO dto = entityToDto(product);
			return dto;
		}
		return null;
	}

	@Override
	public void modify(ProductDTO dto) {
		Optional<Product> result = repository.findById(dto.getNo());
		if (result.isPresent()) {
			Product entity = result.get();

			entity.setProductName(dto.getProductName());
			entity.setPrice(dto.getPrice());
			entity.setColor(dto.getColor());
			entity.setContent(dto.getContent());

			repository.save(entity);
		}
	}

	@Override
	public int remove(int no) {
		Optional<Product> result = repository.findById(no);
		if (result.isPresent()) {
			repository.deleteById(no);

			return 1;// 성공
		} else {
			return 0; // 실패
		}
	}

}
