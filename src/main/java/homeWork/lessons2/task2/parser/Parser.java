package homeWork.lessons2.task2.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import homeWork.lessons2.task2.entity.Person;

import java.io.File;
import java.io.IOException;

public class Parser {
    public static void parsePerson(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(new File(path), Person.class);
        System.out.println(person);
    }
}
