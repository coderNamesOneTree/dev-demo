package com.one_tree.dev;


import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
    }
}
