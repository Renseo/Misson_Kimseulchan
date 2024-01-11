package com.board.anonymousbulletinboard.service;

import com.board.anonymousbulletinboard.dto.ArticleDto;
import com.board.anonymousbulletinboard.entity.Article;
import com.board.anonymousbulletinboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleDto create(ArticleDto articleDto) {
        Article article = new Article(
                 articleDto.getType(), articleDto.getTitle(), articleDto.getContent(), articleDto.getPassword()
        );
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public List<ArticleDto> readAll() {
        List<ArticleDto> articleDtoList = new ArrayList<>();
        for (Article article : articleRepository.findAll(Sort.by("id").descending())) {
            articleDtoList.add(ArticleDto.fromEntity(article));
        }
        return articleDtoList;
    }

    public List<ArticleDto> readBoardType(Integer boardType) {
        List<ArticleDto> articleDtoList = new ArrayList<>();
        for (Article article : articleRepository.findAllByType(boardType, Sort.by("id").descending())) {
            articleDtoList.add(ArticleDto.fromEntity(article));
        }
        return articleDtoList;
    }

    public ArticleDto read(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        return ArticleDto.fromEntity(article);
    }

    public ArticleDto update(Long id, ArticleDto dto) {

        Article article = articleRepository.findById(id).orElseThrow();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    public void delete(Long id) {
        articleRepository.delete(articleRepository.findById(id).orElseThrow());
    }

    public void checkPassword(Long id, String password) {
        Article article = articleRepository.findById(id)
                .orElseThrow();
        if (!article.getPassword().equals(password)) {
            throw new IllegalStateException("비밀번호가 틀립니다.");
        }
    }

}
