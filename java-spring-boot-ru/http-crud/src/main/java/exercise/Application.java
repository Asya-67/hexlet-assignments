package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

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

        if (fromIndex >= posts.size()) {
            return List.of(); // пустая страница
        }

        return posts.subList(fromIndex, toIndex);
    }

    @PostMapping("/posts") // Создание страницы
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @GetMapping("/posts/{id}") // Вывод страницы
    public Optional<Post> show(@PathVariable String id) {
        var post = posts.stream()
                .filter(p -> p.getSlug().equals(id))
                .findFirst();
        return post;
    }

    @PutMapping("/posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Post data) {
        var maybePost = posts.stream()
                .filter(p -> p.getSlug().equals(id))
                .findFirst();

        if (maybePost.isPresent()) {
            var post = maybePost.get();
            post.setSlug(data.getSlug());
            post.setName(data.getName());
            post.setBody(data.getBody());
            return post; // возвращаем обновлённый пост
        }

        return null;
    }
    
    @DeleteMapping("/posts/{id}") // Удаление страницы
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getSlug().equals(id));
    }
    // END
}
