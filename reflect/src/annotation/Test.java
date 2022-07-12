package annotation;

//@MyAnnotation(value = "Jack",array = {"element1","element2"})
public class Test {

    @MyAnnotation(value = "Field1",value1 = "name")
    public String name;

    @MyAnnotation(value = "Field2",value1 = "StuId")
    public String StuId;

    public Integer age;

    @MyAnnotation(value = "method1",value1 = "TestMethod",array = {"public","void"})
    public void TestMethod(int a,String b) {
        System.out.println(a+b);
    }

    public void TestMethod1(@MyAnnotation(value = "param1",value1 = "a",array = "int") int a,@MyAnnotation(value = "param2",value1 = "b",array = "int") int b) {}

}
