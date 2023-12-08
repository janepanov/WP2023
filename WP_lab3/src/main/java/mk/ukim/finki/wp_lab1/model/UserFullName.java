package mk.ukim.finki.wp_lab1.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserFullName implements Serializable {
    private String name;
    private String surname;

    public UserFullName(String name, String surname){
        this.name = name;
        this.surname = surname;
    }
}
