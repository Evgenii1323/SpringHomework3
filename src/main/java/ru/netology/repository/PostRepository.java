package ru.netology.repository;

import ru.netology.model.Post;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {
  private final AtomicLong counting = new AtomicLong();
  private final List<Post> posts = Collections.synchronizedList(new ArrayList<>());

  public List<Post> all() {
    return posts;
  }

  public Optional<Post> getById(long id) {
    for (int i = 0 ; i < posts.size(); i++) {
      if (id == posts.get(i).getId()) {
        return Optional.of(posts.get(i));
      }
    }
    return Optional.empty();
  }

  public Optional<Post> save(Post post) {
    if (post.getId() == 0 ) {
      long id = counting.longValue();
      post.setId(id);
      counting.addAndGet(1);
      posts.add(post);
      return Optional.of(post);
    } else {
      for (int i = 0; i < posts.size(); i++) {
        if (post.getId() == posts.get(i).getId()) {
          posts.set(i, post);
          return Optional.of(post);
        }
      }
    }
    return Optional.empty();
  }

  public void removeById(long id) {
    for (int i = 0; i < posts.size(); i++) {
      if (id == posts.get(i).getId()) {
        posts.remove(i);
        break;
      }
    }
  }
}