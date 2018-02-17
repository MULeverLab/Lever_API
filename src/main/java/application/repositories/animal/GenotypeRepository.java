package application.repositories.animal;

import application.entities.animal.Genotype;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenotypeRepository extends JpaRepository<Genotype, Integer> {



}
