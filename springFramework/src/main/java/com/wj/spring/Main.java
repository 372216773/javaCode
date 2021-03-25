package com.wj.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //对象工厂,管理对象(beanFactory)
        //不传xml文件时,beanFactory初始化错误,所以bean工厂的配置文件不是写死的(固定的),所以要动态配置文件
        //获取beanFactory管理对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        //这是在xml文件中配置过的名称,要能对应xml文件中配置的bean
        //多例和单例时体现在getBean()
        //当scope设置为单例时,多个引用获取同一个bean时,则多个引用指向同一个对象
        //当scope设置为多例时,多个引用获取同一个bean时,则多个引用指向的对象不是同一个(内容相同,地址不同)
        Object bean = applicationContext.getBean("user");
        Object bean0 = applicationContext.getBean("user");
        Object bean1 = applicationContext.getBean("user2");
        Object bean2 = applicationContext.getBean("user2");
        System.out.println(bean);
        System.out.println(bean0);
        System.out.println(bean1);
        System.out.println(bean2);

    }
}
