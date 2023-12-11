package mirante.api.exercise;

import org.springframework.data.jpa.repository.JpaRepository;

interface ExerciseRepository extends JpaRepository<Exercise, String> {}
