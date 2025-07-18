package exercise.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.AllArgsConstructor;
// BEGIN
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"title", "price"})
public class Product {
    private String id;
    private String title;
    private int price;
}
// END
