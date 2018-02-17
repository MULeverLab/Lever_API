package application.repositories.animal;

import application.entities.animal.Phenotype;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhenotypeRepository extends JpaRepository<Phenotype, Integer> {

}
