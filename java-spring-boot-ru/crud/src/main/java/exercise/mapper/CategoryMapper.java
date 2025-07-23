package exercise.mapper;

import exercise.dto.CategoryCreateDTO;
import exercise.dto.CategoryDTO;
import exercise.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

// BEGIN
@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO map(Category category);
    Category map(CategoryCreateDTO categoryCreateDTO);

}
// END
