package mk.ukim.finki.wp_lab1.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserFullNameConverter implements AttributeConverter<UserFullName, String> {

    @Override
    public String convertToDatabaseColumn(UserFullName fullName) {
        if(fullName == null){
            return null;
        }

        return fullName.getName() + " " + fullName.getSurname();
    }

    @Override
    public UserFullName convertToEntityAttribute(String s) {
       if(s == null){
           return null;
       }

       String [] splittedFullName = s.split(" ");
       return new UserFullName(splittedFullName[0], splittedFullName[1]);
    }
}
