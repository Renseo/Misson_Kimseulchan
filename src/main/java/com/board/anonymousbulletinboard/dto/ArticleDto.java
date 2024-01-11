package com.board.anonymousbulletinboard.dto;

import com.board.anonymousbulletinboard.entity.Article;
import com.board.anonymousbulletinboard.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ArticleDto {

    private Long id;
    @Setter
    private Integer type;
    @Setter
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @Setter
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    @Setter
    @NotBlank(message = "비밀번호을 입력해주세요.")
    private String password;
    private final List<CommentDto> comments = new ArrayList<>();

    public static ArticleDto fromEntity(Article entity) {
        ArticleDto dto = new ArticleDto();
        dto.id = entity.getId();
        dto.type = entity.getType();
        dto.title = entity.getTitle();
        dto.content = entity.getContent();
        dto.password = entity.getPassword();
        for (Comment comment: entity.getComments())
            dto.comments.add(CommentDto.fromEntity(comment));
        return dto;
    }

    public ArticleDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
