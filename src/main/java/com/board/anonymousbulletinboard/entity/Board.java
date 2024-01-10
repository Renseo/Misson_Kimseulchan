package com.board.anonymousbulletinboard.entity;

import com.board.anonymousbulletinboard.entity.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private Integer type;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String password;

    @OneToMany(mappedBy = "board")
    private final List<Comment> comments = new ArrayList<>();

    public Board(Integer type, String title, String content, String password) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.password = password;
    }
}