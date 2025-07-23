package exercise.mapper;

import exercise.dto.CategoryCreateDTO;
import exercise.dto.CategoryDTO;
import exercise.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

// BEGIN
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryMapper {

    CategoryDTO map(Category category);

    Category map(CategoryCreateDTO categoryCreateDTO);

    void updateCategoryFromDto(CategoryCreateDTO dto, @MappingTarget Category category);
}
// END
