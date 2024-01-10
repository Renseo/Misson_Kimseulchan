package com.board.anonymousbulletinboard.dto;

import com.board.anonymousbulletinboard.entity.Comment;
import com.board.anonymousbulletinboard.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardDto {

    private Long id;
    @Setter
    private Integer type;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String password;
    private final List<CommentDto> comments = new ArrayList<CommentDto>();

    public static BoardDto fromEntity(Board entity) {
        BoardDto dto = new BoardDto();
        dto.id = entity.getId();
        dto.type = entity.getType();
        dto.title = entity.getTitle();
        dto.content = entity.getContent();
        dto.password = entity.getPassword();
        for (Comment comment: entity.getComments())
            dto.comments.add(CommentDto.fromEntity(comment));
        return dto;
    }

    public BoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", password='" + password + '\'' +
                ", comments=" + comments +
                '}';
    }
}
