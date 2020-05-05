import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CopyUtils {
    public static Object deepCopy(Object object) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //Constructors Array
        Constructor[] constructors = object.getClass().getDeclaredConstructors();

        //Get constructor
        Constructor constructor = constructors[0];

        //If constructor == default constructor
        if (constructor.getParameters().length==0){
            //Create new object
            Object newObject = object.getClass().newInstance();
            //Get all fields
            Field[] declaredFields = object.getClass().getDeclaredFields();
            for (Field f : declaredFields){
                f.setAccessible(true);
                f.set(newObject, f.get(object));
            }
            return newObject;
        }

        //ParamTypes Array
        Class[] parameters = constructor.getParameterTypes();

        //ParamValues List
        List paramsList = new ArrayList();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            paramsList.add(f.get(object));
        };

        //Create new object and copy all fields
        Object newObject = object.getClass().getDeclaredConstructor(parameters).newInstance(paramsList.toArray());
        newObject.getClass().cast(object);
        for (Field f : fields){
            f.setAccessible(true);
            f.set(newObject, f.get(object));
        }

        return newObject;
    }
}
