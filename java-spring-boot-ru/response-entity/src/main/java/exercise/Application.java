package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import exercise.model.Post;
import lombok.Setter;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    @Setter
    private static  List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    public List<Post> index(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit
    ) {
        int fromIndex = (page - 1) * limit;
        int toIndex = Math.min(fromIndex + limit, posts.size());

        List<Post> result = fromIndex >= posts.size()
                ? List.of()
                : posts.subList(fromIndex, toIndex);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(posts.size()));

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(result);
    }
    @PostMapping("/posts") // Создание страницы
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity
                .created(URI.create("/posts/" + post.getId()))
                .body(post);
    }

    @GetMapping("/posts/{id}") // Вывод страницы
    public Optional<Post> show(@PathVariable String id) {
        var post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        return post;
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        Optional<Post> maybePost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (maybePost.isPresent()) {
            Post post = maybePost.get();
            post.setId(data.getId());
            post.setTitle(data.getTitle());
            post.setBody(data.getBody());
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // END
    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
