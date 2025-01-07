package org.example.demoex.services;

import org.example.demoex.dto.PostDto;
import org.example.demoex.entities.Post;
import org.example.demoex.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 서비스, 비즈니스 로직 처리, SQL 주로 실행 명령을 내리는곳
 */
@Service
public class PostService {
    // DI (의존성 주입, 유일한 객체 1개를 가져와서 사용하도록 조치)
    @Autowired
    private PostRepository postRepository;

    // TODO 페이징/검색 추가 2 : 서비스 추가
    public Page<PostDto> getPagePost(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));   // 작성일 최신기준
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));   // 페이지 10개 단위

        // 게시판-페이징만 처리
        //Page<Post> posts = postRepository.findAll(pageable);

        // 게시판-페이징 + 검색결과-페이징 처리
        Page<Post> posts = postRepository.findAllByKeyword(kw, pageable);
        List<PostDto> postDtos = posts.getContent().stream()
                .map(post -> PostDto.builder()
                        .id(post.getId())
                        .subject(post.getSubject())
                        .content(post.getContent())
                        .createDate(post.getCreateDate())
                        .reviews(post.getReviews())
                        .build())  // PostDto의 생성자나 메서드를 사용해 변환
                .collect(Collectors.toList());
        // Page<Post> -> List<PostDto> -> Page<PostDto> 변환 처리
        return new PageImpl<>(postDtos, posts.getPageable(), posts.getTotalElements());
    }


    // 레퍼지토리(SQL을 가지고 있음)를 이용하여 모든 게시물을 가져온다
    // Post 객체(엔티티, 데이터 1개)를 PostDto로 변환하여 List에 담아서 반환 -> 보안
    public List<PostDto> getAllPost() {
        // 1. 레퍼지토리를 통해서, 모든 게시물을 가져온다
        //    1-1. 레포지토리(PostRepository)? -> DI 처리 필요
        //    1-2. findAll() : 사전에 준비된 메소드, 모든 데이터를 가져온다!!
        //         내부적 : select * from post; 수행된것으로 판단
        List<Post> posts = postRepository.findAll();
        // 2. 결과물을 담을 그릇(자료구조) 준비한다 -> List<PostDto>
        List<PostDto> postDtos = new ArrayList<>();
        // 3. 순환하면서 Post->PostDto로 데이터를 교환하여 그릇(List)에 담는다
        //  코드 정렬 단축키 : ctrl(cmd) + shift + alt + l(L 키보드) -> 팝업->run->정렬됨
        for (Post post : posts) {
            postDtos.add(PostDto.builder()
                    .id(post.getId())
                    .subject(post.getSubject())
                    .content(post.getContent())
                    .createDate(post.getCreateDate())
                    .reviews(post.getReviews())
                    .build());
        }
        // 4. 반환
        return postDtos;
    }
    // 레포지토리를 이용하여 1개의 포스트 객체를 획득 -> DTO 교환 후 반환
    // select * from post where id='x'; // 무조건 0~1개만 결과셋
    public PostDto getOnePost(Integer id) {
        // Optional는 옵션 => 결과셋 있을수도 없을수도 있다!!
        // 레포지토리는 이미 준비된 메소드 사용했음!!
        Optional<Post> oPost = this.postRepository.findById( id );
        if( oPost.isPresent() ){ // 데이터가 존재하면
            // Post 엔티티 -> PostDto로 변환 반환
            Post post = oPost.get(); // Post 획득
            return PostDto.builder()
                    .id(post.getId())
                    .subject(post.getSubject())
                    .content(post.getContent())
                    .createDate(post.getCreateDate())
                    .reviews(post.getReviews())
                    .build();
        }
        // 없을경우 -> 예외처리를 던지는것으로 정리
        //throw new Exception();
        return null; // 향후 커스텀 예외처리 변경
    }
    // 글등록 처리(비즈니스로직) -> 레퍼지토리에게 지시 :SQL 처리 요청
    public void create(PostDto postDto) {
        // insert ~  작업 지시!!
        this.postRepository.save( postDto.toEntity() );
    }
    // 글수정 처리 -> 데이터를 보고 자동 판단함( insert, update)
    public void modify(PostDto postDto) {
        this.postRepository.save( postDto.toEntity() );
    }
    // 글삭제 처리
    public void delete(PostDto postDto) {
        this.postRepository.delete( postDto.toEntity() );
    }
}
