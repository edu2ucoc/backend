package org.example.demoex.controllers;

import jakarta.validation.Valid;
import org.example.demoex.dto.PostDto;
import org.example.demoex.form.PostForm;
import org.example.demoex.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/post")
@Controller
public class PostController {
    @Autowired
    private PostService postService;

    // TODO 페이징/검색 추가 1 : 컨트롤러 추가
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<PostDto> posts = postService.getPagePost(page, kw); // 모든 post의 내용 가져오기
        model.addAttribute("posts", posts); // 게시물 데이터 전달
        model.addAttribute("kw", kw);
        return "board/post_list"; // resources/templates/test/post_list.html 읽어서 랜더링
    }

    @GetMapping("/create")
    public String create() {
        return "board/post_form";
    }

    @GetMapping("/create2")
    public String create2(PostForm postForm) {
        return "board/post_form";
    }
    @PostMapping("/create")
    public String create(@RequestParam String subject,
                         @RequestParam String content) {
        System.out.println(subject + " " + content);
        this.postService.create(PostDto.builder()
                .subject(subject)
                .content(content)
                .createDate(LocalDateTime.now())
                .build());
        return "redirect:/post/list";
    }
    @PostMapping("/create2")
    public String create2(@Valid PostForm postForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "board/post_form";
        }
        System.out.println(postForm.getSubject() + " " + postForm.getContent());
        this.postService.create(PostDto.builder()
                .subject(postForm.getSubject())
                .content(postForm.getContent())
                .createDate(LocalDateTime.now())
                .build());
        return "redirect:/post/list";
    }
    @GetMapping("/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") Integer id) {
        System.out.println("상세보기 : " + id);
        PostDto post = this.postService.getOnePost(id);
        model.addAttribute("post", post);
        return "board/post_detail";
    }
    @GetMapping("/modify/{id}")
    public String modify(PostForm postForm, @PathVariable Integer id) {
        PostDto postDto = this.postService.getOnePost( id );
        postForm.setSubject(postDto.getSubject());
        postForm.setContent(postDto.getContent());
        return "board/post_form";
    }
    @PostMapping("/modify/{id}")
    public String modify(@Valid PostForm postForm,
                         BindingResult bindingResult,
                         @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            return "board/post_form";
        }
        System.out.println(postForm.getSubject() + " " + postForm.getContent());
        PostDto postDto = this.postService.getOnePost( id );
        postDto.setSubject(postForm.getSubject());
        postDto.setContent(postForm.getContent());
        this.postService.modify(postDto);
        return "redirect:/post/detail/" + id;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        PostDto postDto = this.postService.getOnePost( id );
        this.postService.delete( postDto );
        return "redirect:/post/list"; // 게시판 목록
    }
}
