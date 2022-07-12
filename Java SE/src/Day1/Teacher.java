package Day1;

public class Teacher extends Person implements MyInterface {
    @Override
    public void run() {
        System.out.println("Teacher 从 Person 中继承过来的需要实现的抽象方法");
    }

    public static void teacherStaticMethod() {
        System.out.println("Teacher 的静态方法");
    }

    public void teacherCommonMethod() {
        System.out.println("Teacher 的普通方法");
    }
}
