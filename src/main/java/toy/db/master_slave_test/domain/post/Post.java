package toy.db.master_slave_test.domain.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
  @Id @GeneratedValue
  Long id;
  String title;
  String content;

  @Builder
  protected Post(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
