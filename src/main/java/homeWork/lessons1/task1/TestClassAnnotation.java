package homeWork.lessons1.task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClassAnnotation {
    @Substitution(a = 2, b = 5)
    public void test(int a, int b){
        System.out.println("A = " + a + " B = " + b);
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        final Class<?> testClassAnnotation = TestClassAnnotation.class;

        //Массив методов
        Method[] methods = testClassAnnotation.getDeclaredMethods();

        for (Method method : methods){
            //Проверка, на обозначение аннотацией
            if (method.isAnnotationPresent(Substitution.class)){
                //Объект, на основе аннотации обозначивающей метод
                Substitution substitution = method.getAnnotation(Substitution.class);
                
                //Передаем в метод значения из аннотации
                method.invoke(testClassAnnotation.newInstance(),substitution.a(),substitution.b());
            }
        }

    }
}
