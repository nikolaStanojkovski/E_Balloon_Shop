package mk.finki.ukim.mk.lab.repository.InDatabase;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    List<Manufacturer> findAll();
    Optional<Manufacturer> findById(Long id);
}
