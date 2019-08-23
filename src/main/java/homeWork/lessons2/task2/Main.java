package homeWork.lessons2.task2;

import homeWork.lessons2.task2.parser.Parser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser.parsePerson("src\\main\\resources\\persons.json");
    }
}
