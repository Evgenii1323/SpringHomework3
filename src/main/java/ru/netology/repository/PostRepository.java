package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {

  private final AtomicLong counting = new AtomicLong();
  private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();

  public List<Post> all() {
    if (!posts.isEmpty()) {
      return new ArrayList<>(posts.values());
    }
    return new ArrayList<>();
  }

  public Optional<Post> getById(long id) {
    if (posts.containsKey(id)) {
        return Optional.of(posts.get(id));
      }
    return Optional.empty();
  }

  public Optional<Post> save(Post post) {
    if (post.getId() == 0 ) {
      long id = counting.longValue();
      post.setId(id);
      counting.addAndGet(1);
      posts.put(id, post);
      return Optional.of(post);
    } else {
        if (posts.containsKey(post.getId())) {
          posts.replace(post.getId(), post);
          return Optional.of(post);
        }
      }
    return Optional.empty();
  }

  public void removeById(long id) {
    posts.remove(id);
  }
}