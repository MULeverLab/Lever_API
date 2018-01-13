package application.repositories.animal;

import application.entities.animal.Mouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MouseRepository extends JpaRepository<Mouse, Integer> {
    Mouse findMouseById(Integer id);

    Optional<List<Mouse>> findMICEByIdGreaterThan(Integer id);

    Optional<List<Mouse>> findMICEByCageId(Integer cageId);

    Optional<List<Mouse>> findMICEByCoatColor(Integer coatColor);

    Optional<List<Mouse>> findMICEByLeftEarPunches(Integer leftEarPunches);

    Optional<List<Mouse>> findMICEByRightEarPunches(Integer rightEarPunches);
}
