package com.wj.goods;

import com.wj.goods.controller.GoodsController;
import com.wj.goods.util.Dom4jUtil;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
1.接收客户端的请求
2.分发请求
 */
public class DispatcherServlet {

    /**
     * 解析自定义的xml配置文件,把解析的所有的<name,Object>全部放入一个Map中
     *
     * @return 返回一个Map<name, Object>
     */
    public Map<String, Object> initBeanMap() throws DocumentException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //存放className和bean对象(实例化的对象)
        Map<String, Object> beanMap = new HashMap<>();
        /*
        获得根节点
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(DispatcherServlet.class.getClassLoader().getResourceAsStream("application.xml"));
        //获得根节点名称
        //System.out.println(read.getRootElement().getName());
        //获得根节点 beans
        Element rootElement = document.getRootElement();*/
        Element rootElement = Dom4jUtil.getRootElement("application.xml");
        //System.out.println(rootElement.getName());
        //获得beans节点下的所有的bean元素,beans的下一级
        List<Element> elements = rootElement.elements("bean");
        for (Element element : elements) {
            String beanName = element.attributeValue("name");
            String className = element.attributeValue("class");
            //根据类的全路径进行类加载,返回该类的Class对象
            //<?>表示可以指向任意类型
            Class<?> clazz = Class.forName(className);
            //产生这个Class类对象的一个实例，
            //调用该类无参的构造方法，作用等同于new一个对象
            Object o = clazz.newInstance();
            beanMap.put(beanName, o);
        }
        return beanMap;
    }

    /**
     * 负责请求的调度
     * 初始化容器,容器中存放的是我们要进行管理的对象(在application.xml中配置的对象)
     */
    @Test
    public void dispatcher() throws ClassNotFoundException, InstantiationException, DocumentException, IllegalAccessException {

        //1.解析xml,得到一个存放className和Object对象的Map<beanName,Object>的容器
        Map<String, Object> beanMap = initBeanMap();
        //2.给我们容器中的对象注入属性
        diProp(beanMap);

        GoodsController goodsController = (GoodsController) beanMap.get("goodsController");

        goodsController.remove();

    }

    /**
     * 属性的注入
     *
     * @param beanMap 对象容器
     */
    private void diProp(Map<String, Object> beanMap) throws DocumentException {

        //拿到根节点
        Element rootElement = Dom4jUtil.getRootElement("application.xml");

        beanMap.forEach((beanName, obj) -> {
            //获得class对象
            //返回引用obj运行时真正所指的对象所属的类的Class的对象
            //(因为:子对象的引用可能会赋给父对象的引用变量中)
            Class clazz = obj.getClass();
            System.out.println("====================" + beanName + "===================");
            //获取本类中定义的所有属性(公有和私有)
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                //获取对象中属性名称
                String declaredFieldName = declaredField.getName();
                //解析xml中配置的属性
                //beans中的bean元素
                List<Element> beanNodes = rootElement.elements("bean");
                for (Element beanNode : beanNodes) {

                    //bean元素中的properties元素
                    Element propertiesElement = beanNode.element("properties");
                    //如果bean元素中含有properties元素,即含有要为对象赋的值
                    if (propertiesElement != null) {

                        //property元素
                        List<Element> propertyNodeList = propertiesElement.elements("property");
                        for (Element propertyNode : propertyNodeList) {

                            //获取xml中的property标签中的name属性的值
                            String name = propertyNode.attributeValue("name");
                            //名称对应
                            if (declaredFieldName.equals(name)) {

                                try {
                                    declaredField.setAccessible(true);//强吻
                                    //如果是数值类型
                                    if (propertyNode.attribute("value") != null) {
                                        //获取xml中的property标签中的value属性的值
                                        String value = propertyNode.attributeValue("value");
                                        //给属性赋值
                                        declaredField.set(obj, value);
                                    }

                                    //如果是引用类型
                                    if (propertyNode.attribute("bean") != null) {
                                        //得到的是字符串
                                        //String bean = propertyNode.attributeValue("bean");

                                        //获取xml中的property标签中的bean引用的值
                                        Object bean = beanMap.get(propertyNode.attributeValue("bean"));
                                        //给属性赋值
                                        declaredField.set(obj, bean);
                                    }
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }

                        }

                    }

                }


            }
        });

    }

}
