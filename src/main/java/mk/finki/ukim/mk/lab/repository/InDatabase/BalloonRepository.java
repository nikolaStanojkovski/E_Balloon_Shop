package mk.finki.ukim.mk.lab.repository.InDatabase;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalloonRepository extends JpaRepository<Balloon, Long> {
    List<Balloon> findByNameContainingOrDescriptionContainingOrManufacturerNameContainingOrType(String text, String text1, String text2, Type type);
    List<Balloon> findByNameContainingOrDescriptionContainingOrManufacturerNameContaining(String text, String text1, String text2);
    Optional<Balloon> findById(Long id);
    Optional<Balloon> findByName(String name);
    List<Balloon> findByType(Type type);
    void deleteById(Long id);
}
