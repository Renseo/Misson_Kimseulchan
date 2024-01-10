package com.board.anonymousbulletinboard.service;

import com.board.anonymousbulletinboard.dto.BoardDto;
import com.board.anonymousbulletinboard.entity.Board;
import com.board.anonymousbulletinboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository repository;

    public void create(BoardDto dto) {
        Board board = new Board(
                dto.getType(), dto.getTitle(), dto.getContent(), dto.getPassword());
        //repository.save(board);
        BoardDto.fromEntity(repository.save(board));
    }

    public List<BoardDto> readAll() {
        List<BoardDto> boardList = new ArrayList<>();
        for (Board board : repository.findAll(Sort.by("id").descending())) {
            boardList.add(BoardDto.fromEntity(board));
        }
        return boardList;
    }

    public List<BoardDto> readBoardType(Integer boardType) {
        List<BoardDto> boardList = new ArrayList<>();
        for (Board board : repository.findAllByType(boardType, Sort.by("id").descending())) {
            boardList.add(BoardDto.fromEntity(board));
        }
        return boardList;
    }

    public BoardDto read(Long id) {
        Board board = repository.findById(id).orElseThrow();
        return BoardDto.fromEntity(board);
    }

    public BoardDto update(Long id, BoardDto dto) {

        Board board = repository.findById(id).orElseThrow();
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        return BoardDto.fromEntity(repository.save(board));
    }

    public void delete(Long id) {

        repository.delete(repository.findById(id).orElseThrow());
    }

    public void checkPassword(Long id, String password) {
        Board board = repository.findById(id)
                .orElseThrow();
        if (!board.getPassword().equals(password)) {
            throw new IllegalStateException("비밀번호가 틀립니다.");
        }
    }
}
