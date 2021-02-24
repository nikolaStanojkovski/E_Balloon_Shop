package mk.finki.ukim.mk.lab.model.exceptions;

import mk.finki.ukim.mk.lab.model.Manufacturer;

public class ManufacturerNotFoundException extends RuntimeException{
    public ManufacturerNotFoundException() {
        super("Manufacturer is not found");
    }
}
