package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ProductDTO;

@SpringBootTest
public class BoardServiceTest {

	@Autowired
	BoardService service;

	@Test
	public void 상품30개등록() {
		for (int i = 1; i <= 30; i++) {
			ProductDTO dto = ProductDTO.builder().productName("로지텍키보드").price(96000).color("블랙")
					.content("k855 무선 키보드입니다").build();
			MultipartFile file = null;
			service.register(dto, file);

		}
	}

	@Test
	public void 상품등록() {

		ProductDTO dto = ProductDTO.builder().productName("로지텍키보드").price(96000).color("블랙").content("k855 무선 키보드입니다")
				.build();

		MultipartFile file = null;
		int no = service.register(dto, file);
		System.out.println(no);

	}

	@Test
	public void 상품목록조회() {
		Page<ProductDTO> page = service.getList(0);
		List<ProductDTO> list = page.getContent();

		for (ProductDTO dto : list) {
			System.out.println(dto);
		}
	}

	@Test
	public void 상품단건조회() {
		ProductDTO dto = service.read(1);
		System.out.println(dto);
	}

	@Test
	public void 상품수정() {
		ProductDTO dto = service.read(1);
		dto.setContent("내용이수정되었습니다~");
		MultipartFile file = null;
		service.modify(dto, file);
	}

	@Test
	public void 상품삭제() {
		service.remove(1);
	}

}