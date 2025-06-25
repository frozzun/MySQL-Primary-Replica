package toy.db.master_slave_test.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
  private final PostService postService;

  @PostMapping
  public void createPost(@RequestBody Post post) {
    postService.save(post);
  }

  @GetMapping
  public List<Post> getAllPosts() {
    return postService.findAll();
  }
}
