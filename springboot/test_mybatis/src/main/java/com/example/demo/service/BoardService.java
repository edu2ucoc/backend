package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.dto.Board;

@Service
public class BoardService {
	// DI, JPA의 레포지토리 역활과 유사한 DAO를 가져옴
	@Autowired
	private BoardMapper boardMapper;

	public List<Board> getAllBoardList () {
		return boardMapper.getAllBoardList();
	}	
}
