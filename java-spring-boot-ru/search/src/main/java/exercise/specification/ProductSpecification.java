package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withPriceGt(convertToDouble(params.getPriceGt())))
                .and(withPriceLt(convertToDouble(params.getPriceLt())))
                .and(withRatingGt(convertToDouble(params.getRatingGt())))
                .and(withTitleContains(params.getTitleCont()));
    }

    private Double convertToDouble(Integer value) {
        return value == null ? null : value.doubleValue();
    }

    private Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, cb) -> categoryId == null
                ? cb.conjunction()
                : cb.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> withPriceGt(Double priceGt) {
        return (root, query, cb) -> priceGt == null
                ? cb.conjunction()
                : cb.greaterThan(root.get("price"), priceGt);
    }

    private Specification<Product> withPriceLt(Double priceLt) {
        return (root, query, cb) -> priceLt == null
                ? cb.conjunction()
                : cb.lessThan(root.get("price"), priceLt);
    }

    private Specification<Product> withRatingGt(Double ratingGt) {
        return (root, query, cb) -> ratingGt == null
                ? cb.conjunction()
                : cb.greaterThan(root.get("rating"), ratingGt);
    }

    private Specification<Product> withTitleContains(String titleCont) {
        return (root, query, cb) -> titleCont == null
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("title")), "%" + titleCont.toLowerCase() + "%");
    }
}
// END
