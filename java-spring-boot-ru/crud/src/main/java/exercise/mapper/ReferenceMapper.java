package exercise.mapper;

import exercise.model.Category;
import exercise.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

// BEGIN
@Component
@RequiredArgsConstructor
public class ReferenceMapper {

    private final CategoryRepository categoryRepository;

    public Category resolveCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found"));
    }
}
// END
