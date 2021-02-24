package mk.finki.ukim.mk.lab.repository.InMemory;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository {

    public boolean checkIfExists(String name, String password) {
        if(DataHolder.userList.stream().filter(i -> i.getUsername().equals(name) && i.getPassword().equals(password)).count() != 0)
            return true;

        return false;
    }

}
