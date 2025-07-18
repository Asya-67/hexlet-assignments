package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findByPriceGreaterThanEqualOrderByPriceAsc(int min);
    List<Product> findByPriceLessThanEqualOrderByPriceAsc(int max);
    List<Product> findByPriceBetweenOrderByPriceAsc(int min, int max);
    // END
}
