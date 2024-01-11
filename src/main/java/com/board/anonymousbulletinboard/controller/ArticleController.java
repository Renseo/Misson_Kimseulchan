package com.board.anonymousbulletinboard.controller;

import com.board.anonymousbulletinboard.dto.ArticleDto;
import com.board.anonymousbulletinboard.dto.CommentDto;
import com.board.anonymousbulletinboard.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/boards")
    public String boardList(Model model) {
        model.addAttribute("articleList", articleService.readAll());
        return "board";
    }

    @GetMapping("/boards/{boardId}")
    public String board(
            @PathVariable("boardId")
            Integer boardId,
            Model model) {
        model.addAttribute("articleList", articleService.readBoardType(boardId));
        return "board";
    }

    @GetMapping("/boards/article")
    public String boardWrite(Model model) {
        model.addAttribute("articleDto",new ArticleDto());
        return "article";
    }

    @PostMapping("/boards/article")
    public String create(@Valid ArticleDto articleDto, BindingResult result) {

        if(result.hasErrors()) {
            return "article";
        }
        Long articleId = articleService.create(articleDto).getId();
        return "redirect:/article/%s".formatted(articleId);
    }

    @GetMapping("/article/{articleId}")
    public String boardOne(
            @PathVariable("articleId")
            Long id,
            Model model
    ) {
        model.addAttribute("article", articleService.read(id));
        model.addAttribute("comment", new CommentDto());
        return "read";
    }

    @GetMapping("/article/{articleId}/update")
    public String boardEdit(
            @PathVariable("articleId")
            Long id,
            @RequestParam("password")
            String password,
            Model model
    ) {
        articleService.checkPassword(id, password);
        model.addAttribute("article", articleService.read(id));
        return "edit";
    }

    @PostMapping("/article/{articleId}/update")
    public String boardUpdate(
            @PathVariable("articleId")
            Long id,
            @RequestParam("title")
            @Valid
            String title,
            @RequestParam("content")
            @Valid
            String content
    ) {
        articleService.update(id, new ArticleDto(title, content));
        return "redirect:/article/%s".formatted(id);
    }

    @PostMapping("/article/{articleId}/delete")
    public String boardDelete(
            @PathVariable("articleId")
            Long id,
            @RequestParam("password")
            String password
    ) {
        articleService.checkPassword(id, password);
        articleService.delete(id);
        return "redirect:/boards";
    }
}
