package com.project.blogapi.dto;

import com.project.blogapi.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
  private Long id;

  @NotEmpty(message = "Title cannot be blank")
  @Size(message = "Title length must be between 1 -45 characters", min = 1, max = 45)
  private String title;

  @NotEmpty(message = "Content cannot be empty")
  @Size(message = "Content length must be between 1 - 500 characters", min = 1, max = 500)
  private String content;

  @NotNull(message = "User id is required")
  private Long userId;

  public PostDto(Post post) {
    this.id = post.getId();
    this.title = post.getTitle();
    this.content = post.getContent();
    this.userId = post.getUser().getId();
  }
}
