package mirante.api;

import org.springframework.data.jpa.repository.JpaRepository;

interface OptionRepository extends JpaRepository<Option, String> {}
