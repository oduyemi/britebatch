package dev.yemi.britebatch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private ProductCategory category;

    @ManyToOne
    private Batch batch;


    private Float price;
    private String img;

    public Product(String name, String description, ProductCategory category, Float price, String img) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.img = img;
    }

    public static void main(String[] args) {
        ProductCategory category = new ProductCategory();
        category.setName("Bone straight");
        category.setDescription("Hair that is straight, iron straight, and untangled");

        Product shortie = new Product("Shortie", "8 inches Bone Straight hair", category, 55000.0f, "/assets/images/shortie.jpg");
    }
}
