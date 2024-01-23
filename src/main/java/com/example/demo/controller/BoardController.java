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

	@GetMapping("/main")
	public void main() {

	}

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

	@GetMapping("/read")
	public void read(@RequestParam("productName") String productName, Model model) {
		ProductDTO dto = service.read(productName);
		model.addAttribute("dto", dto);
	}

	@GetMapping("/modify")
	public void modify(@RequestParam("productName") String productName, Model model) {
		ProductDTO dto = service.read(productName);
		model.addAttribute("dto", dto);
	}

	@PostMapping("/modify")
	public String modifyPost(ProductDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("productName", dto.getProductName());
		return "redirect:/board/read";

	}
}