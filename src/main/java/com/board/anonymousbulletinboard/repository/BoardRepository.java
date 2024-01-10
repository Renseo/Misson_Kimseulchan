package com.board.anonymousbulletinboard.repository;

import com.board.anonymousbulletinboard.entity.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAll(Sort sort);

    List<Board> findAllByType(Integer boardType, Sort sort);
}
