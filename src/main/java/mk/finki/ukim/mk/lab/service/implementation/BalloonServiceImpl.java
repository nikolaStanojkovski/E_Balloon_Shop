package mk.finki.ukim.mk.lab.service.implementation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.enums.Type;
import mk.finki.ukim.mk.lab.model.exceptions.*;
import mk.finki.ukim.mk.lab.repository.InDatabase.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.InDatabase.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository) {
        this.balloonRepository = balloonRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> findByNameOrDescriptionOrTypeOrManufacturer(String text) {
        if(Arrays.stream(Type.values()).anyMatch(i -> i.toString().equals(text)))
            return balloonRepository.findByNameContainingOrDescriptionContainingOrManufacturerNameContainingOrType(text, text, text, Type.valueOf(text));
        return balloonRepository.findByNameContainingOrDescriptionContainingOrManufacturerNameContaining(text, text, text);
    }

    @Override
    public Optional<Balloon> findByName(String name) {
        return balloonRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Balloon> save(String name, String description, Long id, Manufacturer manufacturer, Type type) {

        if(manufacturer == null)
            throw new ManufacturerNotFoundException();
        if(name == null)
            throw new BalloonNullNameException();
        if(description == null)
            throw new BalloonNullDescriptionException();
        if(type == null)
            throw new BalloonTypeNullException();
        if(name.isEmpty())
            throw new BalloonEmptyNameException();
        if(description.isEmpty())
            throw new BalloonEmptyDescriptionException();

        if(id!=null && balloonRepository.findById(id).isPresent())
            this.balloonRepository.deleteById(id);

        return Optional.of(this.balloonRepository.save(new Balloon(name, description, manufacturer, type)));
    }

    @Override
    public void delete(Long id) {
        if(balloonRepository.findById(id) != null && balloonRepository.findById(id).isPresent()) {
            Balloon toDelete = searchById(id);
            this.balloonRepository.deleteById(toDelete.getId());
        }
    }

    @Override
    public Balloon searchById(Long id) {
        return this.balloonRepository.findById(id).get();
    }

    @Override
    public List<Balloon> sort() {
        List<Balloon> balloonList = this.balloonRepository.findAll();
        balloonList.sort(Comparator.comparing(Balloon::getName));
        return balloonList;
    }

    @Override
    public List<Balloon> filterByType(Type type) {
        return this.balloonRepository.findByType(type);
    }


}
