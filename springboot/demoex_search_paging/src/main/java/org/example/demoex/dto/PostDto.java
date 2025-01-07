package org.example.demoex.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.demoex.entities.Post;
import org.example.demoex.entities.Review;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
@Builder
public class PostDto {
    private int id;
    private String subject;
    private String content;
    private LocalDateTime createDate;
    private List<Review> reviews;

    public Post toEntity() {
        return Post.builder()
                .id(this.id)
                .subject(this.subject)
                .content(this.content)
                .createDate(this.createDate)
                .reviews(this.reviews)
                .build();
    }
}
