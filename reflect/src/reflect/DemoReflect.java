package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DemoReflect {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class<?> aClass = Class.forName("reflect.Demo");

        Demo demo = (Demo) aClass.newInstance();

        Method nameMethod = aClass.getDeclaredMethod("setName", String.class);

        nameMethod.invoke(demo,"Jack");

        Method getName = aClass.getDeclaredMethod("getName");

        String name = (String) getName.invoke(demo);

        System.out.println(name);

    }
}
