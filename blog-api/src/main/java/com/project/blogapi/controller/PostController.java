package com.project.blogapi.controller;

import com.project.blogapi.dto.PostDto;
import com.project.blogapi.exception.ResourceNotFoundException;
import com.project.blogapi.model.Post;
import com.project.blogapi.model.User;
import com.project.blogapi.repository.PostRepository;
import com.project.blogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {
  private final PostRepository postRepository;
  private final UserRepository userRepository;

  @Autowired
  public PostController(PostRepository postRepository, UserRepository userRepository) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
  }

  @GetMapping
  public List<PostDto> getAllPosts() {
    return this.postRepository.findAll()
        .stream()
        .map(PostDto::new)
        .collect(Collectors.toList());
  }

  @GetMapping("{id}")
  public PostDto getPostById(@PathVariable Long id) {
    Post post = this.postRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Could not find post with id=" + id));

    return new PostDto(post);
  }

  @PostMapping
  public PostDto createNewPost(@Valid @RequestBody PostDto request) {
    User user = this.userRepository.findById(request.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("Could not find user."));

    Post post = new Post(null, request.getTitle(), request.getContent(), user);
    Post savedPost = this.postRepository.save(post);

    return new PostDto(savedPost);
  }

  @PutMapping
  public PostDto editPost(@Valid @RequestBody PostDto request) {
    User user = this.userRepository.findById(request.getUserId())
        .orElseThrow(() -> new ResourceNotFoundException("Could not find user."));

    Post post = new Post(request.getId(), request.getTitle(), request.getContent(), user);
    Post updatedPost = this.postRepository.save(post);

    return new PostDto(updatedPost);
  }

  @DeleteMapping("{id}")
  public void deletePost(@PathVariable Long id) {
    this.postRepository.deleteById(id);
  }
}
