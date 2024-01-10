package com.board.anonymousbulletinboard.repository;

import com.board.anonymousbulletinboard.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
