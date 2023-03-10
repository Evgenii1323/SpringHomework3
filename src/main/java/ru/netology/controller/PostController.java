package ru.netology.controller;

import com.google.gson.Gson;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class PostController {
  private static final String APPLICATION_JSON = "application/json";
  private final PostService service;
  public PostController(PostService service) {
    this.service = service;
  }

  public void all(HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final List<Post> data = service.all();
    final Gson gson = new Gson();
    response.getWriter().print(gson.toJson(data));
  }

  public void getById(long id, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final Post data = service.getById(id);
    final Gson gson = new Gson();
    response.getWriter().print(gson.toJson(data));
  }

  public void save(Reader body, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    final Gson gson = new Gson();
    final Post post = gson.fromJson(body, Post.class);
    final Post data = service.save(post);
    response.getWriter().print(gson.toJson(data));
  }

  public void removeById(long id, HttpServletResponse response) throws IOException {
    response.setContentType(APPLICATION_JSON);
    service.removeById(id);
    final List<Post> data = service.all();
    final Gson gson = new Gson();
    response.getWriter().print(gson.toJson(data));
  }
}