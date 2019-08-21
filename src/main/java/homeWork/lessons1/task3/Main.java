package homeWork.lessons1.task3;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HumanInformation human = new HumanInformation();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("d:\\file.txt"));

        objectOutputStream.writeObject(human);
        //Закрываем поток
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d:\\file.txt"));

        objectInputStream.readObject();
        //Закрываем поток
        objectInputStream.close();
    }
}
