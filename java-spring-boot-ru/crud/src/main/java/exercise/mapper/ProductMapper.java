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
import exercise.dto.CategoryDTO;
import exercise.dto.CategoryCreateDTO;

// BEGIN
@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ReferenceMapper referenceMapper;

    // Преобразование Product в ProductDTO
    public ProductDTO toDto(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setCategoryId(product.getCategory().getId());
        dto.setCategoryName(product.getCategory().getName());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }

    // Преобразование ProductCreateDTO в Product
    public Product toEntity(ProductCreateDTO dto) {
        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setCategory(referenceMapper.toCategory(dto.getCategoryId()));
        return product;
    }

    // Обновление Product из ProductUpdateDTO
    public void updateEntity(ProductUpdateDTO dto, Product product) {
        if (dto.getTitle() != null && dto.getTitle().isPresent()) {
            product.setTitle(dto.getTitle().get());
        }
        if (dto.getPrice() != null && dto.getPrice().isPresent()) {
            product.setPrice(dto.getPrice().get());
        }
        if (dto.getCategoryId() != null && dto.getCategoryId().isPresent()) {
            product.setCategory(referenceMapper.toCategory(dto.getCategoryId().get()));
        }
    }
}
// END
