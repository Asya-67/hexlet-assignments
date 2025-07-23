package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;;
import exercise.dto.CategoryDTO;
import exercise.dto.CategoryCreateDTO;

// BEGIN
@Mapper(componentModel = "spring", uses = {ReferenceMapper.class})
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductDTO toDto(Product product);

    @Mapping(source = "categoryId", target = "category")
    Product toEntity(ProductCreateDTO dto);

    @Mapping(source = "categoryId", target = "category")
    void update(ProductCreateDTO dto, @MappingTarget Product entity);
}
// END
