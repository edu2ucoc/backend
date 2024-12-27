package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * tb_board 테이블을 조회하여 
 * 나온 결과를 담는(들고있는, 연결X) 그릇(데이터 1개를 표방함) -> DTO
 *
 * 롬복의 게더, 세터, toString, has.. -> @Data
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	private long board_id;
    private String title;
    private String content;
    private String writer;    
    private long views;
    private LocalDateTime created_at;
    private LocalDateTime modified_at;
}
