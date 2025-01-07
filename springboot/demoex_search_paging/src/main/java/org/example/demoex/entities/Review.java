package org.example.demoex.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Review {
    // 컬럼 나열
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    // 본글 : 리뷰 = 1 : N 관계
    // 리뷰 : 본글  = N : 1 관계
    @ManyToOne
    private Post post;

    // 빌더 패턴 적용
    @Builder
    public Review(int id, String content, LocalDateTime createDate, Post post) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.post = post;
    }
}
