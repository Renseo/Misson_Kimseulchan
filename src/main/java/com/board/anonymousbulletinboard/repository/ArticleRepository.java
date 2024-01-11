package com.board.anonymousbulletinboard.repository;

import com.board.anonymousbulletinboard.entity.Article;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAll(Sort sort);

    List<Article> findAllByType(Integer boardType, Sort sort);
}
