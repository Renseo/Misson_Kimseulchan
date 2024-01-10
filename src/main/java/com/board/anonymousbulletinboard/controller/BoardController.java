package com.board.anonymousbulletinboard.controller;

import com.board.anonymousbulletinboard.dto.BoardDto;
import com.board.anonymousbulletinboard.dto.CommentDto;
import com.board.anonymousbulletinboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public String boards(Model model) {
        model.addAttribute("boards", boardService.readAll());
        return "index";
    }

    @GetMapping("/boards/{boardId}")
    public String board(
            @PathVariable("boardId")
            Integer boardId,
            Model model) {
        model.addAttribute("boards", boardService.readBoardType(boardId));
        return "index";
    }

    @GetMapping("/boards/article")
    public String boardWrite(Model model) {
        model.addAttribute("dto", new BoardDto());
        return "article";
    }

    @PostMapping("/boards/article")
    public String create(BoardDto dto) {
        System.out.println(dto);
        boardService.create(dto);
        return "redirect:/boards";
    }

    @GetMapping("/article/{articleId}")
    public String boardOne(
            @PathVariable("articleId")
            Long id,
            Model model
    ) {
        model.addAttribute("board", boardService.read(id));
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
        boardService.checkPassword(id, password);
        model.addAttribute("board", boardService.read(id));
        return "edit";
    }

    @PostMapping("/article/{articleId}/update")
    public String boardUpdate(
            @PathVariable("articleId")
            Long id,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content
    ) {
        boardService.update(id, new BoardDto(title, content));
        return "redirect:/article/%s".formatted(id);
    }

    @PostMapping("/article/{articleId}/delete")
    public String boardDelete(
            @PathVariable("articleId")
            Long id,
            @RequestParam("password")
            String password
    ) {
        boardService.checkPassword(id, password);
        boardService.delete(id);
        return "redirect:/boards";
    }
}
