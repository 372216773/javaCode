package Unsafe;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * unsafe 的常见操作
 */
@Slf4j
public class unSafeDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        //获得Unsafe类中 theUnsafe 属性
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");

        //强制访问 theUnsafe 属性
        theUnsafe.setAccessible(true);

        //获得 Unsafe 实例
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        //获取指定对象的域(字段)的偏移量
        //偏移量是指这个字段在内存中相对于这个对象在内存中的起始地址的偏移量
        long idOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("id"));
        long nameOffset = unsafe.objectFieldOffset(Student.class.getDeclaredField("name"));

        Student student = new Student();

        //以 CAS 的方式来更新一个引用类型的字段值
        //需要被更新的域一般都是用 volatile 修饰的，不然多线程环境下无法保证正确性。
        unsafe.compareAndSwapInt(student,idOffset,0,1);
        unsafe.compareAndSwapObject(student,nameOffset,null,"张三");

        log.debug("{}",student);

    }
}

class Student {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
