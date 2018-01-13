package application;

import application.entities.animal.*;
import application.repositories.animal.*;
import application.repositories.project.AccountRepository;
import application.repositories.project.CompetencyRepository;
import application.repositories.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class DataInitializer {

    private final AnimalRepository animalRepository;

    private final BreedingEventRepository breedingEventRepository;

    private final ColonyRepository colonyRepository;

    private final GenotypeRepository genotypeRepository;

    private final PhenotypeRepository phenotypeRepository;

//    @Autowired
//    private PhenotypeBridgeRepository phenotypeBridgeRepository;

    private final MouseRepository mouseRepository;

    private final ProjectRepository projectRepository;

    private final AccountRepository accountRepository;

    private final CompetencyRepository competencyRepository;

    @Autowired
    public DataInitializer(AnimalRepository animalRepository, BreedingEventRepository breedingEventRepository, ColonyRepository colonyRepository, GenotypeRepository genotypeRepository, PhenotypeRepository phenotypeRepository, MouseRepository mouseRepository, ProjectRepository projectRepository, AccountRepository accountRepository, CompetencyRepository competencyRepository) {
        this.animalRepository = animalRepository;
        this.breedingEventRepository = breedingEventRepository;
        this.colonyRepository = colonyRepository;
        this.genotypeRepository = genotypeRepository;
        this.phenotypeRepository = phenotypeRepository;
        this.mouseRepository = mouseRepository;
        this.projectRepository = projectRepository;
        this.accountRepository = accountRepository;
        this.competencyRepository = competencyRepository;
    }

    @GetMapping("/init")
    ResponseEntity<String> initialize(){
        // create 2 genotypes
        Genotype genotype1 = new Genotype("Gene A", "NA");
        Genotype genotype2 = new Genotype("Gene B", "NA");

        // create 2 phenotypes
        Phenotype phenotype1 = new Phenotype("Disphagia", "NA");
        Phenotype phenotype2 = new Phenotype("No disphagia", "NA");

        // create 5 animals (mice)
        Mouse mouse1 = new Mouse(23, 1, 1, 2);
        mouseRepository.save(mouse1);
        Animal animal1 = new Animal(genotype1, new HashSet<PhenotypeBridge>(), "NA", 0, 0L, 0L, 0, 0, mouse1.getId());
        animal1.getPhenotypeBridgeSet().add(new PhenotypeBridge(phenotype1, 0L));

        Mouse mouse2 = new Mouse(41, 0, 0, 2);
        mouseRepository.save(mouse2);
        Animal animal2 = new Animal(genotype2, new HashSet<PhenotypeBridge>(), "NA", 1, 0L, 0L, 1, 0, mouse2.getId());
        animal2.getPhenotypeBridgeSet().add(new PhenotypeBridge(phenotype1, 0L));

        Mouse mouse3 = new Mouse(2, 1, 2, 1);
        mouseRepository.save(mouse3);
        Animal animal3 = new Animal(genotype1, new HashSet<PhenotypeBridge>(), "NA", 1, 0L, 0L, 0, 0, mouse3.getId());
        animal3.getPhenotypeBridgeSet().add(new PhenotypeBridge(phenotype1, 0L));

        Mouse mouse4 = new Mouse(12, 0, 0, 0);
        mouseRepository.save(mouse4);
        Animal animal4 = new Animal(genotype1, new HashSet<PhenotypeBridge>(), "NA", 0, 0L, 0L, 1, 0, mouse4.getId());
        animal4.getPhenotypeBridgeSet().add(new PhenotypeBridge(phenotype2, 0L));

        Mouse mouse5 = new Mouse(21, 1, 2, 2);
        mouseRepository.save(mouse5);
        Animal animal5 = new Animal(genotype2, new HashSet<PhenotypeBridge>(), "NA", 0, 0L, 0L, 0, 0, mouse5.getId());
        animal5.getPhenotypeBridgeSet().add(new PhenotypeBridge(phenotype2, 0L));

        // create 2 colonies
        Colony colony1 = new Colony("Colony1", "NA", new HashSet<>());
        colony1.getAnimalSet().add(animal1);
        colony1.getAnimalSet().add(animal2);

        Colony colony2 = new Colony("Colony2", "NA", new HashSet<>());
        colony2.getAnimalSet().add(animal3);
        colony2.getAnimalSet().add(animal4);
        colony2.getAnimalSet().add(animal5);


        // store animal data
        // all saved as a result of cascade
        colonyRepository.save(colony1);
        colonyRepository.save(colony2);

        return new ResponseEntity<String>("initialized", HttpStatus.OK);
    }


}
