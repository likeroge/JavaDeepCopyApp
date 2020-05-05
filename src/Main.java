import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List books = Arrays.asList("Java Book", "Kotlin Book");
        Man man1 = new Man("Egor", 29, books);

        Man copyMan = (Man) CopyUtils.deepCopy(man1);

        System.out.println(copyMan.getName());
        System.out.println(copyMan.getAge());
        System.out.println(copyMan.getFavoriteBooks());

        //Objects are different
        System.out.println(man1==copyMan);

        //Let's check fields
        Field[] declaredFields1 = man1.getClass().getDeclaredFields();
        Field[] declaredFields2 = copyMan.getClass().getDeclaredFields();

        //Fields are equals:
        System.out.println(Arrays.asList(declaredFields1).equals(Arrays.asList(declaredFields2)));

        //But not the same
        System.out.println(declaredFields1==declaredFields2);


    }
}
