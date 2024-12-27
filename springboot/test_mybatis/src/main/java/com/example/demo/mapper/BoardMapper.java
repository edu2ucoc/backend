package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.Board;

/**
 * repository 역활
 */
@Mapper
public interface BoardMapper {
	List<Board> getAllBoardList();
}
