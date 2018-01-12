package application.repository;

import application.animal.Genotype;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

public interface GenotypeRepository extends JpaRepository<Genotype, Integer> {


    Genotype findGenotypeById(Integer id);

    Optional<Set<Genotype>> findGenotypesByIdGreaterThan(Integer id);


}
