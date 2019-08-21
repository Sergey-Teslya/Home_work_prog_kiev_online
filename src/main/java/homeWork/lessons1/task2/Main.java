package homeWork.lessons1.task2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> textContainerClass = TextContainer.class;
        SaveTo saveTo = textContainerClass.getAnnotation(SaveTo.class);
        String urlFile = saveTo.path();

        Method[] methods = textContainerClass.getDeclaredMethods();

        for (Method method : methods){

            if (method.isAnnotationPresent(Saver.class)){
                method.invoke(textContainerClass.newInstance(), urlFile);
            }
        }
    }
}
