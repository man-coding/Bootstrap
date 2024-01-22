package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Product;

@SpringBootTest
public class ProdRepositoryTest {

	@Autowired
	ProductRepository repository;

	@Test
	public void 상품등록() {

		Product product = Product.builder().productName("키보드").price(120000).color("블루").build();

		repository.save(product);
	}

	@Test
	public void 상품목록조회() {

		List<Product> list = repository.findAll();

		for (Product product : list) {
			System.out.println(product);
		}

	}

	@Test
	public void 상품단건조회() {
		Optional<Product> result = repository.findById(1);

		Product product = result.get();
		System.out.println(product);
	}

	@Test
	public void 상품수정() {
		Optional<Product> result = repository.findById(1);
		Product product = result.get();

		product.setColor("그레이");
		repository.save(product);
	}

	@Test
	public void 상품삭제() {
		repository.deleteById(1);
	}
}
