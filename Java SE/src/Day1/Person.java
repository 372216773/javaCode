package Day1;

public abstract class Person {
    public int stuId;
    public static String personStatic = "personStatic";
    public static final String personStaticFinal = "PERSONSTATICFINAL";

    public Person() {
        System.out.println("创建了一个 Person 对象");
    }

    //知道这个类有这个行为, 但不知道具体是怎样的
    public abstract void run() ;

    public static void personStaticMethod(){
        System.out.println("Person 的静态方法");
    };

    public void personCommonMethod(){
        System.out.println("Person 的普通方法");
    };
}
