package exercise.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;
// BEGIN
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"title", "price"})
public class Product {
    private Long id;
    private String title;
    private int price;
}
// END
