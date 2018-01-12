package application.repository;

import application.animal.Phenotype;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PhenotypeRepository extends JpaRepository<Phenotype, Integer> {



    Phenotype findPhenotypeById(Integer id);

    Optional<Set<Phenotype>> findPhenotypesByIdGreaterThan(Integer id);

}
