package com.project.blogapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorDetails {
  private Date timestamp;
  private String message;
  private List<String> details;
}
