// ProductCategory.java
package dev.yemi.britebatch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> productsInCategory = new ArrayList<>();

    public static void main(String[] args) {
        List<ProductCategory> categories = new ArrayList<>();

        ProductCategory category1 = new ProductCategory();
        category1.setName("Bone straight");
        category1.setDescription("Hair that is straight, iron straight, and untangled");
        categories.add(category1);

        ProductCategory category2 = new ProductCategory();
        category2.setName("Wavy");
        category2.setDescription("Hair with waves");
        categories.add(category2);

        ProductCategory category3 = new ProductCategory();
        category3.setName("Curly");
        category3.setDescription("Hair with curls");
        categories.add(category3);

        ProductCategory category4 = new ProductCategory();
        category4.setName("Coily");
        category4.setDescription("Hair with coils");
        categories.add(category4);

        for (ProductCategory category : categories) {
            System.out.println("Category Name: " + category.getName());
            System.out.println("Category Description: " + category.getDescription());
        }
    }
}
