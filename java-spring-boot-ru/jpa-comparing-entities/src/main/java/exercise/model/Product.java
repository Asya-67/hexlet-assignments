package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import static jakarta.persistence.GenerationType.IDENTITY;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
// BEGIN
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"title", "price"})
class Product {
    private String id;
    private String title;
    private int price;
}
// END
