package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.stream.Collectors;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api/users/{id}/posts")
public class PostsController {

    private final List<Post> posts = Data.getPosts();

    @GetMapping
    public List<Post> getUserPosts(@PathVariable Long id) {
        return posts.stream()
                .filter(post -> post.getUserId() == id.intValue())
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createUserPost(@PathVariable Long id, @RequestBody Post post) {
        post.setUserId(id.intValue());
        posts.add(post);
        return post;
    }
}
// END
