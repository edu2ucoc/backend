package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Board;
import com.example.demo.service.BoardService;

@RestController
public class BoardController {
	@Autowired
	private BoardService boardService;

	// 게시판 내용을 요청하는 api
	@GetMapping("/boards")
	public List<Board> boards() {
		return boardService.getAllBoardList();
	}
}
