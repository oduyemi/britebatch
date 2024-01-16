package dev.yemi.britebatch.repository;

import dev.yemi.britebatch.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch, Long> {

}
