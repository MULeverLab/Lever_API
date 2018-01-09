package application.repository;

import application.animal.Phenotype;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhenotypeRepository extends JpaRepository<Phenotype, Integer> {
}
