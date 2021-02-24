package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.enums.Type;

import java.util.List;
import java.util.Optional;

public interface BalloonService {
    List<Balloon> listAll();
    List<Balloon> findByNameOrDescriptionOrTypeOrManufacturer(String text);
    Optional<Balloon> findByName(String name);
    Optional<Balloon> save(String name, String description, Long id, Manufacturer manufacturer, Type type);
    void delete(Long id);
    Balloon searchById(Long id);
    List<Balloon> sort();
    List<Balloon> filterByType(Type type);
}
