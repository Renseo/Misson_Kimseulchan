package com.board.anonymousbulletinboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String content;
    @Setter
    private String password;

    @ManyToOne
    private Board board;

    public Comment(String content, String password, Board board) {
        this.content = content;
        this.password = password;
        this.board = board;
    }
}