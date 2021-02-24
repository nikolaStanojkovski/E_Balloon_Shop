package mk.finki.ukim.mk.lab.repository.InMemory;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.enums.Type;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryBalloonRepository {

    public List<Balloon> findAllBalloons() {
        return DataHolder.balloonList;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return DataHolder.balloonList.stream().filter(r->r.getName().contains(text) ||
                r.getDescription().contains(text)).collect(Collectors.toList());
    }

    public int deleteBalloon(Long id) {
        Balloon toDelete = findById(id);
        int atId = DataHolder.balloonList.indexOf(toDelete);
        DataHolder.balloonList.removeIf(i -> i.getId().equals(id));
        return atId;
    }

    public void saveBalloonEdit(String name, String description, int index, Manufacturer manufacturer, Type type) {
        DataHolder.balloonList.add(index, new Balloon(name, description, manufacturer, type));
    }

    public void saveBalloon(String name, String description, Manufacturer manufacturer, Type type) {
        DataHolder.balloonList.add(new Balloon(name, description, manufacturer, type));
    }

    public Balloon findById(Long id) {
        try {
            return DataHolder.balloonList.stream().filter(i -> i.getId().equals(id)).findFirst().get();
        } catch (RuntimeException ex) {
            return null;
        }
    }

    public List<Balloon> sort() {
        List<Balloon> tempList = DataHolder.balloonList;

        for(int i=0;i<tempList.size()-1;i++) {
            for(int j=0;j<tempList.size()-i-1;j++) {
                if(tempList.get(j).getName().compareTo(tempList.get(j+1).getName()) > 0) {
                    Balloon temp = tempList.get(j);
                    tempList.set(j, tempList.get(j+1));
                    tempList.set(j+1, temp);
                }
            }
        }
        return tempList;
    }

    public boolean checkAdding(String name) {
        if(DataHolder.balloonList.stream().filter(i -> i.getName().equals(name)).count() != 0)
            return true;

        return false;
    }

    public List<String> filterByType(Type type) {
        return DataHolder.balloonList.stream().filter(i -> i.getType().
                toString().equals(type.toString())).
                map(i -> i.getName()).collect(Collectors.toList());
    }
}
