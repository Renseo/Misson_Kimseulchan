package com.board.anonymousbulletinboard.dto;

import com.board.anonymousbulletinboard.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CommentDto {

    private Long id;
    @Setter
    private String content;
    @Setter
    private String password;

    public CommentDto(String content, String password) {
        this.content = content;
        this.password = password;
    }

    public static CommentDto fromEntity(Comment entity) {
        CommentDto dto = new CommentDto();
        dto.id = entity.getId();
        dto.content = entity.getContent();
        dto.password = entity.getPassword();
        return dto;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
