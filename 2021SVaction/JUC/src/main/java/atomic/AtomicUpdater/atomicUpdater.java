package atomic.AtomicUpdater;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 原子更新器: 针对于对象中的某个字段进行操作
 */
@Slf4j
public class atomicUpdater {
    public static void main(String[] args) {

        AtomicReferenceFieldUpdater<Student, Integer> updater = AtomicReferenceFieldUpdater.newUpdater(Student.class, Integer.class, "id");

        Student student = new Student();

        log.debug("{}", updater.compareAndSet(student, 1, 2));

        log.debug("{}", student.getId());
    }
}

class Student {
    private String name;//字段

    volatile Integer id = 1;//属性 (有get/set方法就为属性)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}