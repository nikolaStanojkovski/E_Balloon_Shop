package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.InDatabase.ManufacturerRepository;
import mk.finki.ukim.mk.lab.repository.InMemory.InMemoryManufacturerRepository;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer findById(Long id) {
        return manufacturerRepository.findById(id).get();
    }

    @Override
    public Manufacturer save(String name, String address) {
        return this.manufacturerRepository.save(new Manufacturer(name, address));
    }
}
