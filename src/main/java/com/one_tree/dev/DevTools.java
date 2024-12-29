package com.one_tree.dev;


import com.google.common.collect.Lists;
import com.one_tree.idea_demo.jdk.People;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;


import javax.annotation.Resource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class DevTools {

    public static void main(String[] args) {


        //1. ------ Collections 工具 -----------
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(6);
        list.add(3);
        list.add(5);
        //1.1 升序
        Collections.sort(list);
        System.out.println(list);
        //1.2 降序
        Collections.reverse(list);
        System.out.println(list);
        //1.3最大，最小值
        System.out.println(Collections.max(list));
        System.out.println(Collections.min(list));
        //1.4空list，空map
        System.out.println(Collections.emptyList());
        System.out.println(Collections.emptyMap());
        //1.5unmodifiableList不可修改的list，如修改会报错UnsupportedOperationException
        List<Integer> unmodifiableList = Collections.unmodifiableList(list);
        //1.6转换为线程安全的集合
        List<Integer> integers = Collections.synchronizedList(list);


        //2. ------------ CollectionUtils工具 -------------------
        //        org.apache.commons.collections.CollectionUtils
        //2.1判空 内部 collection == null || collection.isEmpty()
        CollectionUtils.isEmpty(list);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(8);
        //2.2合集
        Collection<Integer> unionList = CollectionUtils.union(list, list2);;
        //2.3获取交集
        Collection<Integer> intersectionList = CollectionUtils.intersection(list, list2);
        //2.4获取差集
        Collection<Integer> subtractList = CollectionUtils.subtract(list, list2);

        System.out.println(unionList);
        System.out.println(intersectionList);
        System.out.println(subtractList);

        //3.---------- com.google.guava下的Lists --------
        //3.1初始化list
        List<Integer> gList = Lists.newArrayList(1, 2, 3);
        //3.2分割list，2个一组
        List<List<Integer>> partitionList = Lists.partition(list, 2);
        //3.3反转list
        List<Integer> reverseList = Lists.reverse(list);

        System.out.println(gList);
        System.out.println(partitionList);
        System.out.println(reverseList);


        //4. ----------- Objects-------------------
        Integer i = new Integer(1);
        //4.1判空与判非空 obj == null
        boolean result1 = Objects.isNull(i);
        boolean result2 =Objects.nonNull(i);

        //4.2为空时抛异常
        Objects.requireNonNull(i, "Parameters cannot be null");

        //4.3判断是否相等 内部(a == b) || (a != null && a.equals(b))
        //注意：包装类（如 Integer 和 Long），它们的 equals 方法被设计为仅当两个对象不仅类型相同，
        // 而且值也相等时，才返回 true。
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);
        System.out.println(Objects.equals(i1, i2));
        //以下返回false
        Long a = new Long(1);
        System.out.println(a.equals(i1));
        //4.4 获取对象hash码
        System.out.println(Objects.hashCode(a));


        //5. ------------ StringUtils---------org.apache.commons.lang3-------------
        //5.1 判空，支持判空串 cs == null || cs.length() == 0
        String str1 = null;
        System.out.println(StringUtils.isEmpty(str1));
        System.out.println(StringUtils.isNotEmpty(str1));
        //支持判 纯空格 的case，如" "
        String str2 = " ";
        System.out.println(StringUtils.isBlank(str2));

        //5.2 分割字符串，StringUtils支持null，String自带的不支持
        String str3 = "abcd,dd";
        System.out.println(StringUtils.split(str3, ","));
        //String的不支持null，会NPE
        System.out.println(str1.split(","));

        //5.3 判断是否为纯数字（只有数字）
        String numStr = "11.11";
        System.out.println(StringUtils.isNumeric(numStr));
//        StringUtils.isNumeric(null)   = false
//        StringUtils.isNumeric("")     = false
//        StringUtils.isNumeric("  ")   = false
//        StringUtils.isNumeric("123")  = true
//        StringUtils.isNumeric("१२३")  = true
//        StringUtils.isNumeric("12 3") = false
//        StringUtils.isNumeric("ab2c") = false
//        StringUtils.isNumeric("12-3") = false
//        StringUtils.isNumeric("12.3") = false
//        StringUtils.isNumeric("-123") = false
//        StringUtils.isNumeric("+123") = false

        //5.4 合并列表为字符串
        List<String> strList1 = Lists.newArrayList("a", "b", "c");
        //"a,b,c"
        System.out.println(StringUtils.join(strList1, ","));

        //6 ---------- BeanUtils --------Spring下
        //6.1 拷贝
        People people1 = new People();
        people1.setAge(11);
        people1.setName("一棵树");
        People people2 = new People();
        BeanUtils.copyProperties(people1, people2);
        System.out.println(people2);
        //6.2 获取指定类的指定方法
        Method getAge = BeanUtils.findDeclaredMethod(People.class, "getAge");
        System.out.println(getAge.getName());
        //6.3 获取指定方法的参数 源码org.springframework.beans.CachedIntrospectionResults.propertyDescriptors
        PropertyDescriptor propertyForMethod = BeanUtils.findPropertyForMethod(getAge);
        System.out.println(propertyForMethod.getName());

        //7 ReflectionUtils
        //7.1 获取某个方法
        Method method = ReflectionUtils.findMethod(People.class, "getAge");

        //7.2 获取属性
        Field field = ReflectionUtils.findField(People.class, "name");

        //7.3 获取方法并传参 调用
        People people = new People();
        Method setMethod = ReflectionUtils.findMethod(People.class, "setAge");
        ReflectionUtils.invokeMethod(setMethod, people, "12");

        //7.4 判断方法是否为equals方法，如是否为method
        System.out.println(ReflectionUtils.isEqualsMethod(method));

        //8 DigestUtils org.apache.commons.codec.digest
        //8.1 MD5加密
        String md5Hex = DigestUtils.md5Hex("Dylan");
        //8.2 SHA256 加密
        String sha256Hex = DigestUtils.sha256Hex("Dylan");

        //9 HttpStatus org.springframework.http
        //9.1 503
        int value503 = HttpStatus.SERVICE_UNAVAILABLE.value();
        //9.2 200
        int success = HttpStatus.OK.value();
    }
}
