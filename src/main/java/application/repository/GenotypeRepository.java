package application.repository;

import application.animal.Genotype;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface GenotypeRepository extends JpaRepository<Genotype, Integer> {
    Optional<Genotype> findGenotypeById(Integer id);

}
