package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService service;

	// 메인화면
	@GetMapping("/main")
	public void main() {

	}

	// 목록화면
	@GetMapping("/list")
	public void list(Model model) {

		List<ProductDTO> list = service.getList();
		model.addAttribute("list", list);
	}

	// 등록화면
	@GetMapping("/register")
	public void register() {

	}

	// 등록처리
	@PostMapping("/register")
	public String registerPost(ProductDTO dto, RedirectAttributes redirectAttributes) {

		int no = service.register(dto);

		redirectAttributes.addFlashAttribute("msg", no);

		return "redirect:/board/list";
	}

	// 상세화면
	@GetMapping("/read")
	public void read(@RequestParam(name = "no") int no, Model model) {
		ProductDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}

	// 수정화면
	@GetMapping("/modify")
	public void modify(@RequestParam(name = "no") int no, Model model) {
		ProductDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}

	// 수정처리
	@PostMapping("/modify")
	public String modifyPost(ProductDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("no", dto.getNo());
		return "redirect:/board/read";

	}

	@PostMapping("/remove")
	public String removePost(@RequestParam(name = "no") int no) {
		service.remove(no);
		return "redirect:/board/list";
	}
}