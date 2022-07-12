package Demo;

public class children extends Father{

    @Override
    public synchronized void method() {
        super.method();
        System.out.println("children method()");
    }
}
