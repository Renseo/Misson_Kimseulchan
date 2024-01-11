package com.board.anonymousbulletinboard.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String content;
    @NotBlank
    private String password;

    @ManyToOne
    private Article article;

    public Comment(String content, String password, Article article) {
        this.content = content;
        this.password = password;
        this.article = article;
    }
}