package exercise.mapper;

import exercise.model.Category;
import exercise.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

// BEGIN
@Component
@RequiredArgsConstructor
public class ReferenceMapper {

    private final CategoryRepository categoryRepository;

    public Category toCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }
}
// END
