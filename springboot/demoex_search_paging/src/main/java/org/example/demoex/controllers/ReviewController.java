package org.example.demoex.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.demoex.dto.PostDto;
import org.example.demoex.dto.ReviewDto;
import org.example.demoex.form.ReviewForm;
import org.example.demoex.services.PostService;
import org.example.demoex.services.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/review")
@RequiredArgsConstructor
@Controller
public class ReviewController {
    private final PostService postService;
    private final ReviewService reviewService;

    @GetMapping("/list")
    public String list() {
        return "list";
    }

    @PostMapping("/create/{id}")
    public String create(@PathVariable Integer id,
                         @RequestParam String content) {
        PostDto postDto = this.postService.getOnePost( id );
        this.reviewService.create( postDto, content );
        return "redirect:/post/detail/" + id;
    }
    @GetMapping("/modify/{id}")
    public String modify(ReviewForm reviewForm,
                         @PathVariable Integer id) {
        ReviewDto reviewDto = this.reviewService.getOneReview( id );
        reviewForm.setContent( reviewDto.getContent() );
        return "board/review_form";
    }
    @PostMapping("/modify/{id}")
    public String modify(@Valid  ReviewForm reviewForm,
                         BindingResult bindingResult,
                         @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            return "board/review_form";
        }
        ReviewDto reviewDto = this.reviewService.getOneReview( id );
        reviewDto.setContent( reviewForm.getContent() );
        this.reviewService.modify( reviewDto );
        return "redirect:/post/detail/" + reviewDto.getPost().getId();
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        ReviewDto reviewDto = this.reviewService.getOneReview( id );
        this.reviewService.delete( reviewDto );
        return "redirect:/post/detail/" + reviewDto.getPost().getId();
    }
}
