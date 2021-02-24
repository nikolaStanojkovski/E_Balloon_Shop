package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import mk.finki.ukim.mk.lab.model.enums.Type;

import javax.persistence.*;

@Data
@Entity
public class Balloon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Manufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Balloon(String name, String description) {
        this.name = name;
        this.description = description;
        this.manufacturer = null;
    }
    public Balloon(String name, String description, Manufacturer manufacturer, Type type) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.type = type;
    }
    public Balloon() {

    }
}
