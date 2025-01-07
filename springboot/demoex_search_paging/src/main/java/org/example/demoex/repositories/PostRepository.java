package org.example.demoex.repositories;

import org.example.demoex.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Integer> {
    // TODO 페이징/검색 추가 3 : 레퍼지토리 추가
    @Query("select "
            + "distinct q "
            + "from Post q "
            //+ "left outer join Review a on a.post=q "
            + "where "
            + "   q.subject like %:kw% "
            + "   or q.content like %:kw% "
            //+ "   or a.content like %:kw% "
            )
    Page<Post> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
}
