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
import org.mapstruct.ReportingPolicy;
import exercise.model.Category;

// BEGIN
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = ReferenceMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductDTO map(Product product);

    @Mapping(source = "categoryId", target = "category")
    Product map(ProductCreateDTO dto);

    default void update(@MappingTarget Product product, ProductUpdateDTO dto) {
        if (dto.getTitle() != null && dto.getTitle().isPresent()) {
            product.setTitle(dto.getTitle().get());
        }

        if (dto.getPrice() != null && dto.getPrice().isPresent()) {
            product.setPrice(dto.getPrice().get());
        }

        if (dto.getCategoryId() != null && dto.getCategoryId().isPresent()) {
            Long categoryId = dto.getCategoryId().get();
            Category category = resolveCategory(categoryId);
            product.setCategory(category);
        }
    }

    default Category resolveCategory(Long id) {
        return referenceMapper().resolve(id, Category.class);
    }

    ReferenceMapper referenceMapper();
}
// END
