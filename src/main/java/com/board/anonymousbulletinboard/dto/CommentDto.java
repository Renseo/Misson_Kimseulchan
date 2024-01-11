package com.board.anonymousbulletinboard.dto;

import com.board.anonymousbulletinboard.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CommentDto {

    private Long id;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    @NotBlank(message = "비밀번호을 입력해주세요.")
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
}
