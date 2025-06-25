package toy.db.master_slave_test.domain.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;

  public void save(Post post) {
    postRepository.save(post);
  }

  @Transactional(readOnly = true)
  public List<Post> findAll() {
    return postRepository.findAll();
  }
}
