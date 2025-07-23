package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

//BEGIN
@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ReferenceMapper referenceMapper;

    // Преобразование Product в ProductDTO
    public ProductDTO map(Product product) {
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
        product.setCategory(referenceMapper.resolveCategory(dto.getCategoryId()));
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
            product.setCategory(referenceMapper.resolveCategory(dto.getCategoryId().get()));
        }
    }
}
// END
