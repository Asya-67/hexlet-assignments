package exercise.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;

import exercise.model.BaseEntity;
import jakarta.persistence.EntityManager;
import org.mapstruct.Named;

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
