package Day1;

public interface MyInterface {
    public String name = null;
    public static String age = "3";
    public final String PHONE = "";

    public default void myInterfaceDefaultMethod(){
        System.out.println("MyInterface default method");
    }

    public static void MyInterfaceStaticMethod() {
        System.out.println("MyInterface static method");
    }

}
