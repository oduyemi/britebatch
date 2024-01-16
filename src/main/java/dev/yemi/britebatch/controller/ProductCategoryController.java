import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products/categories")
@PreAuthorize("hasRole('ADMIN')")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAllProductCategories() {
        List<ProductCategory> categories = productCategoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoriyId}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Long productCategoryId) {
        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findById(productCategoryId);
        return optionalProductCategory.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<String> createProductCategory(@RequestBody ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product category successfully created");
    }

    @PutMapping("/{productCategoryId}")
    public ResponseEntity<String> updateProductCategory(@PathVariable Long productCategoryId, @RequestBody ProductCategory updatedProductCategory) {
        if (productCategoryRepository.existsById(productCategoryId)) {
            updatedProductCategory.setId(productCategoryId);
            productCategoryRepository.save(updatedProductCategory);
            return ResponseEntity.ok("Product category successfully updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product category not found");
        }
    }

    @DeleteMapping("/{productCategoryId}")
    public ResponseEntity<String> deleteProductCategory(@PathVariable Long productCategoryId) {
        if (productCategoryRepository.existsById(productCategoryId)) {
            productCategoryRepository.deleteById(productCategoryId);
            return ResponseEntity.ok("Product category successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product category not found");
        }
    }
}
