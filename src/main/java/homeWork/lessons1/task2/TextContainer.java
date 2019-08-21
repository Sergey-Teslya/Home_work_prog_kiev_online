package homeWork.lessons1.task2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SaveTo(path = "d:\\file.txt")
public class TextContainer {
    private String text = "Test text string";

    @Saver
    public void save(String path) {
        File file = new File(path);

        //Проверка на наличие файла
        if (file.isFile()) {
            writeFile(file);
        } else {

            //Создание нового файла и запись в файл
            try {
                boolean createFile = file.createNewFile();
                if (createFile) {
                    System.out.println("Create new File");
                    writeFile(file);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //Метод для записи в файл
    private void writeFile(File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
