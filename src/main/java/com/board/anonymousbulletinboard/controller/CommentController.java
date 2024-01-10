package com.board.anonymousbulletinboard.controller;

import com.board.anonymousbulletinboard.dto.CommentDto;
import com.board.anonymousbulletinboard.service.BoardService;
import com.board.anonymousbulletinboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping("article/{articleId}/comment")
    public String create(
            @PathVariable("articleId")
            Long boardId,
            @RequestParam
            String content,
            @RequestParam
            String password
    ) {
        commentService.create(boardId, new CommentDto(content, password));
        return "redirect:/article/%d".formatted(boardId);
    }

    @PostMapping("/article/{articleId}/comment/{commentId}/delete/")
    public String delete(
            @PathVariable("articleId")
            Long boardId,
            @PathVariable("commentId")
            Long commentId,
            @RequestParam("cPassword")
            String password
    ) {
        commentService.checkPassword(commentId, password);
        commentService.delete(commentId);
        return "redirect:/article/%d".formatted(boardId);
    }
}
