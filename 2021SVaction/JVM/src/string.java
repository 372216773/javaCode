
public class string {

    public static void main(String[] args) {
        
    }

    /**
     * 1.调用 StringBuilder 的无参构造方法,创建了一个 StringBuilder 的对象 new StringBuilder();
     * <p>
     * 2.从局部变量表中拿到 s1 再调用 StringBuilder 的 append() 方法 new StringBuilder().append("a");
     * <p>
     * 3.从局部变量表中拿到 s2 再调用 StringBuilder 的 append() 方法 new StringBuilder().append("a").append("b");
     * <p>
     * 4.调用 toString() 方法 new StringBuilder().append("a").append("b").toString();
     * <p>
     * StringBuilder 的 toString() 方法会 new 一个新的对象返回回来,存在堆中.将 toString() 得到的结果,存到局部变量表中的4号局部变量,并不在常量池中
     */
    public static void Demo1() {
        String s1 = "a";
        String s2 = "b";
        String s5 = "ab";
        String s4 = s1 + s2;
    }

    /**
     * javac 在编译期的优化, 结果在编译期就已经可以确定
     */
    public static void Demo2() {
        String s3 = "a" + "b";
    }

    /**
     * intern()
     */
    public static void Demo3(){
        String s1 = "a";
        String s2 = "b";
        String s5 = "ab";
        String s3 = "a" + "b";  //编译器在编译阶段进行优化为 s3=“ab”
        String s4 = s1 + s2;    //因为s1，s2是变量，底层会调用 StringBuilder.append("a").append("b").toString();返回一个新对象，
                                // 存在局部变量表中
        String s6 = s4.intern();
        System.out.println(s3 == s4);
        //不相等,s3会被编译器优化,即s3="ab",s4=s1+s2,因为是变量,底层就调用 StringBuilder,产生新的对象

        System.out.println(s3 == s5);
        //s3会被编译器优化,即s3="ab",指向"ab",s5也指向"ab",所以相等

        System.out.println(s3 == s6);
        //s4手动入池,指向常量池中的"ab",即s6指向常量池中的"ab",所以相等
    }

    public static void Demo4(){

    }
}
