package mk.finki.ukim.mk.lab.repository.InMemory;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryManufacturerRepository {
    public List<Manufacturer> findAll() {
        return DataHolder.manufacturerList;
    }
    public Manufacturer findById(Long id) {
        return DataHolder.manufacturerList.stream().filter(i -> i.getId().equals(id)).findFirst().get();
    }
}
