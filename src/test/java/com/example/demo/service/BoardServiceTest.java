package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.ProductDTO;

@SpringBootTest
public class BoardServiceTest {

	@Autowired
	BoardService service;

	@Test
	public void 상품등록() {

		ProductDTO dto = ProductDTO.builder().productName("로지텍키보드").price(96000).color("블랙").content("k855 무선 키보드입니다")
				.build();

		int no = service.register(dto);
		System.out.println(no);

	}

	@Test
	public void 상품목록조회() {
		List<ProductDTO> list = service.getList();

		for (ProductDTO dto : list) {
			System.out.println(dto);
		}

	}
}