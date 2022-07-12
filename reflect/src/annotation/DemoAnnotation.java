package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class DemoAnnotation {

    public static void main(String[] args) {
        Class<Test> testClass = Test.class;
        Method[] testClassMethods = testClass.getMethods();

        for (Method method : testClassMethods) {

            System.out.println(method.getName());
            Parameter[] parameters = method.getParameters();

            //通过反射获取方法的参数不能获取到参数名称的, 是按照参数的顺序来命名的: arg0  arg1  arg2
            for (Parameter parameter : parameters) {

                //如果不加这个判断, 有的属性没有加注解就会报错
                if (parameter.isAnnotationPresent(MyAnnotation.class)) {
                    MyAnnotation annotation = parameter.getAnnotation(MyAnnotation.class);
                    System.out.println(annotation.value() + " " + annotation.value1() + " " + Arrays.toString(annotation.array()));
                }
            }
            System.out.println();
        }
    }
}
