package mk.finki.ukim.mk.lab.bootstrap;

import lombok.Getter;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.enums.Type;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Balloon> balloonList = new ArrayList<>();
    public static List<Manufacturer> manufacturerList = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();

//    @PostConstruct
//    public void init() {
//
//        manufacturerList.add(new Manufacturer("Creative Balloons", "Down street 25"));
//        manufacturerList.add(new Manufacturer("Qualatex", "4724 Dale Avenue"));
//        manufacturerList.add(new Manufacturer("Taiwan Rubber", "176 Emma Street"));
//        manufacturerList.add(new Manufacturer("Industrial Balloons", "2195 Hartland Avenue"));
//        manufacturerList.add(new Manufacturer("HKTDC", "1064 Ryder Avenue"));
//
//        balloonList.add(new Balloon("Red Balloon", "This is the first balloon", manufacturerList.get(0), Type.Arrow));
//        balloonList.add(new Balloon("Blue Balloon", "This is the second balloon", manufacturerList.get(0), Type.Star));
//        balloonList.add(new Balloon("Dark Balloon", "This is the third balloon", manufacturerList.get(0), Type.Cube));
//        balloonList.add(new Balloon("Black Balloon", "This is the fourth balloon", manufacturerList.get(1), Type.Arrow));
//        balloonList.add(new Balloon("White Balloon", "This is the fifth balloon", manufacturerList.get(1), Type.Cylinder));
//        balloonList.add(new Balloon("Brown Balloon", "This is the sixth balloon", manufacturerList.get(2), Type.Cylinder));
//        balloonList.add(new Balloon("Dark Balloon", "This is the seventh balloon", manufacturerList.get(3), Type.Cube));
//        balloonList.add(new Balloon("Aqua Balloon", "This is the eighth balloon", manufacturerList.get(3), Type.Heart));
//        balloonList.add(new Balloon("Peach Balloon", "This is the ninth balloon", manufacturerList.get(4), Type.Star));
//        balloonList.add(new Balloon("Orange Balloon", "This is the tenth balloon", manufacturerList.get(4), Type.Arrow));
//
//        userList.add(new User("nikola.stanojkovski", "ns"));
//        userList.add(new User("steven.stanojkovski", "ks"));
//        userList.add(new User("maven.stanojkovski", "cs"));
//        userList.add(new User("meliora.stanojkovski", "nh"));
//        userList.add(new User("janiora.stanojkovski", "kj"));
//
//    }
}
