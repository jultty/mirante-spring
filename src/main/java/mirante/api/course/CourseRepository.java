package mirante.api.course;

import org.springframework.data.jpa.repository.JpaRepository;

interface CourseRepository extends JpaRepository<Course, String> {}
