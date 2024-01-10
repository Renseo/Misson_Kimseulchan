package com.board.anonymousbulletinboard.service;

import com.board.anonymousbulletinboard.dto.CommentDto;
import com.board.anonymousbulletinboard.entity.Comment;
import com.board.anonymousbulletinboard.entity.Board;
import com.board.anonymousbulletinboard.repository.BoardRepository;
import com.board.anonymousbulletinboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public CommentDto create(Long boardId, CommentDto dto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow();
        Comment comment = new Comment(
                dto.getContent(), dto.getPassword(), board
        );

        return CommentDto.fromEntity(commentRepository.save(comment));
    }

    public CommentDto read(Long commentId) {
        return CommentDto.fromEntity(commentRepository.findById(commentId)
                .orElseThrow());
    }

    public CommentDto update(Long commentId, CommentDto dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow();

        comment.setContent(dto.getContent());
        return CommentDto.fromEntity(commentRepository.save(comment));
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
