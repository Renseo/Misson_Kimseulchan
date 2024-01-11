package com.board.anonymousbulletinboard.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer type;
    @Setter
    @NotBlank
    private String title;
    @Setter
    @NotBlank
    private String content;
    @NotBlank
    private String password;

    @OneToMany(mappedBy = "article")
    private final List<Comment> comments = new ArrayList<>();

    public Article(Integer type, String title, String content, String password) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.password = password;
    }
}