package homeWork.lessons1.task3;

import java.io.*;
import java.lang.reflect.Field;

//Реализуем интерфейс Externalizable для описания сериализации и десериализации вручную
public class HumanInformation implements Externalizable {
    @Save
    private String name = "Max";
    @Save
    private int age = 10;
    @Save
    private boolean sex = true;

    //Описания сериализации для этого класса
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        Class<?> humanInformationClass = HumanInformation.class;
        Field[] fields = humanInformationClass.getDeclaredFields();

        for (Field field : fields){
            //Проверка на анотацию
            if (field.isAnnotationPresent(Save.class)){
                //Установка разрешения на чтение private свойств
                field.setAccessible(true);
                try {
                    //Запись объекта в файл
                    out.writeObject(field.get(humanInformationClass.newInstance()));
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Описания десериализации для этого класса
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Class<?> humanInformationClass = HumanInformation.class;
        Field[] fields = humanInformationClass.getDeclaredFields();

        for (Field field : fields){
            //Проверка на анотацию
            if (field.isAnnotationPresent(Save.class)){
                //Установка разрешения на чтение private свойств
                field.setAccessible(true);

                try {
                    //Изменения значения полей на считаные значения объектов
                    field.set(humanInformationClass.newInstance(), in.readObject());
                    //Вывод для наглядности
                    System.out.println(field.getName() + " = " + field.get(humanInformationClass.newInstance()));
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
