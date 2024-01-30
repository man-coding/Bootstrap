package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service // 서비스 클래스로 지정해줘야 됨...
public class BoardServiceImpl implements BoardService {

	@Autowired
	ProductRepository repository;

	@Value("${webpath}")
	String webpath;

	@Override
	public int register(ProductDTO dto, MultipartFile file) {

		// UUID uuid = UUID.randomUUID();
//		String originalFilename = file.getOriginalFilename();

		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename(); // 실제 사용 시에는 파일명 중복을 고려해야 함
			
			File saveFile = new File(webpath, fileName); // File 객체를 생성하는 명령어. 아직 파일이 생성되진 않음.객체만 생성

			try {
				file.transferTo(saveFile); // 실제로 파일을 저장 -> uuid 식별자를 사용 안 하고 저장하면 기존 첨부된 파일과 이름이 같아 덮어쓰게 됨.
				// 웹에서 접근 가능한 파일 경로를 저장
				dto.setFilePath("/uploadfile/" + fileName);
				dto.setFileName(fileName);
				// 사용자가 파일을 업로드하면, 프론트엔드에서는 단순히 파일 데이터를 서버로 전송하기만 하고, 파일의 이름이나 저장 경로 등의 추가 정보는
				// 서버 측에서 설정해줘야 함.
				// 이렇게 설정해 준 다음 밑에서 entity로 변경해서 db에 이름과 패스를 저장하는 것임.
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				return -1; // 예외 발생 시 적절한 에러 코드 반환
			}
		} else {
			// 파일이 없는 경우, filePath와 fileName을 null 또는 공백으로 설정
			dto.setFilePath(null);
			dto.setFileName(null);
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
	public void modify(ProductDTO dto, @RequestParam("file") MultipartFile file) {
		Optional<Product> result = repository.findById(dto.getNo());
		if (result.isPresent()) {
			Product entity = result.get();

			entity.setProductName(dto.getProductName());
			entity.setPrice(dto.getPrice());
			entity.setColor(dto.getColor());
			entity.setContent(dto.getContent());
			entity.setFileName(dto.getFileName());

			String projectPath = System.getProperty("user.dir") + "\\src\\main\resources\\static\\files";

			UUID uuid = UUID.randomUUID();

			String fileName = uuid + "file.getOriginalFilename()";

			File saveFile = new File(projectPath, fileName);

			try {
				file.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

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
