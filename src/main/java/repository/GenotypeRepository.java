package repository;

import animal.Genotype;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenotypeRepository extends JpaRepository<Genotype, Integer> {
}
