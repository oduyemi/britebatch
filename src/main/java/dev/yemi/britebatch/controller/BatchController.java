import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/batches")
@PreAuthorize("hasRole('ADMIN')")
@PreAuthorize("hasRole('SALES')")
public class BatchController {

    @Autowired
    private BatchRepository batchRepository;

    @GetMapping
    public ResponseEntity<List<Batch>> getAllBatches() {
        List<Batch> batches = batchRepository.findAll();
        return ResponseEntity.ok(batches);
    }

    @GetMapping("/{batchId}")
    public ResponseEntity<Batch> getBatchById(@PathVariable Long batchId) {
        Optional<Batch> optionalBatch = batchRepository.findById(batchId);
        return optionalBatch.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<String> createBatch(@RequestBody Batch batch) {
        batchRepository.save(batch);
        return ResponseEntity.status(HttpStatus.CREATED).body("Batch successfully created");
    }

    @Transactional
    @PostMapping("/{batchId}/products")
    public ResponseEntity<String> addProductToBatch(@PathVariable Long batchId, @RequestBody Product product) {
        Optional<Batch> optionalBatch = batchRepository.findById(batchId);

        if (optionalBatch.isPresent()) {
            Batch batch = optionalBatch.get();
            product.setBatch(batch);
            batch.getProducts().add(product);
            batchRepository.save(batch);
            return ResponseEntity.ok("Product added to batch successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Batch not found");
        }
    }


    @PutMapping("/{batchId}")
    public ResponseEntity<String> updateBatch(@PathVariable Long batchId, @RequestBody Batch updatedBatch) {
        if (batchRepository.existsById(batchId)) {
            updatedBatch.setId(batchId);
            batchRepository.save(updatedBatch);
            return ResponseEntity.ok("Batch successfully updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Batch not found");
        }
    }

    @Transactional
    @DeleteMapping("/{batchId}/removeProduct/{productId}")
    public ResponseEntity<String> removeProductFromBatch(@PathVariable Long batchId, @PathVariable Long productId) {
        Optional<Batch> optionalBatch = batchRepository.findById(batchId);

        if (optionalBatch.isPresent()) {
            Batch batch = optionalBatch.get();
            batch.getProducts().removeIf(product -> product.getId().equals(productId));
            batchRepository.save(batch);
            return ResponseEntity.ok("Product removed from batch successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Batch not found");
        }
    }

    @DeleteMapping("/{batchId}")
    public ResponseEntity<String> deleteBatch(@PathVariable Long batchId) {
        if (batchRepository.existsById(batchId)) {
            batchRepository.deleteById(batchId);
            return ResponseEntity.ok("Batch deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Batch not found");
        }
    }

}
