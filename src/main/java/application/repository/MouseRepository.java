package application.repository;

import application.animal.Mouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface MouseRepository extends JpaRepository<Mouse, Integer> {
    Mouse findMouseById(Integer id);

    Optional<Set<Mouse>> findMICEByIdGreaterThan(Integer id);

    Optional<Set<Mouse>> findMICEByCageId(Integer cageId);

    Optional<Set<Mouse>> findMICEByCoatColor(Integer coatColor);

    Optional<Set<Mouse>> findMICEByLeftEarPunches(Integer leftEarPunches);

    Optional<Set<Mouse>> findMICEByRightEarPunches(Integer rightEarPunches);
}
