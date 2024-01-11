package com.board.anonymousbulletinboard.service;

import com.board.anonymousbulletinboard.dto.CommentDto;
import com.board.anonymousbulletinboard.entity.Comment;
import com.board.anonymousbulletinboard.entity.Article;
import com.board.anonymousbulletinboard.repository.ArticleRepository;
import com.board.anonymousbulletinboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public void create(Long boardId, CommentDto dto) {
        Article article = articleRepository.findById(boardId)
                .orElseThrow();
        Comment comment = new Comment(
                dto.getContent(), dto.getPassword(), article
        );

        CommentDto.fromEntity(commentRepository.save(comment));
    }

    public void delete(Long commentId) {
        commentRepository.delete(commentRepository.findById(commentId)
                .orElseThrow());
    }

    public void checkPassword(Long id, String password) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow();
        if (!comment.getPassword().equals(password)) {
            throw new IllegalStateException("비밀번호가 틀립니다.");
        }
    }
}
