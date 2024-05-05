package com.one_tree.idea_demo.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionDemo {
    //今日主题： Function接口的使用

//    public static void main(String[] args) {
//        //Part1： apply方法
//        //使用Integer作为输入类型，String作为输出类型的Function, 实现默认的apply方法
//        Function<Integer, String> soutFunction = i -> "The number is " + i;
////        Function<Integer, String> soutFunction2 = new Function<Integer, String>() {
////            @Override
////            public String apply(Integer integer) {
////                return i -> "The number is " + i;
////            }
////        };
//
//        System.out.println(soutFunction.apply(123)); // 输出: The number is 123
//
//        //Part2： compose 以及 andThen方法
//        //定义一个作用为数字+1的function
//        Function<Integer, Integer> addOneFunction = i-> i+1;
//
//        //定义一个 *3的function
//        Function<Integer, Integer> multiThreeFunction = i-> i*3;
//
//        //multiThreeFunction.compose(addOneFunction).apply(3)
//        //等价于multiThreeFunction.apply(addOneFunction.apply(3));
//        //先+1，然后再*3，(3+1)*3;
//        Integer composeResult = multiThreeFunction.compose(addOneFunction).apply(3);
//
//        //先*3，然后+1. (3*3)+1
//        //等价于addOneFunction.apply(multiThreeFunction.apply(3);
//        Integer andThenResult = multiThreeFunction.andThen(addOneFunction).apply(3);
//
//        System.out.println("compose调用3的结果为："+composeResult);
//        System.out.println("andThen调用3的结果为："+andThenResult);
//    }


    // 泛型方法，接受Function类型的参数
    public static <T, R> R genericApply(T input, Function<T, R> function) {
        return function.apply(input); // 应用Function接口
    }

    /**
     * 将List中对象的某个属性组成List
     * @param input 入参
     * @param function 函数
     * @return 执行函数后的结果集
     * @param <T> 入参列表中的元素类型
     * @param <R> 返回列表中的元素类型
     */
    public static <T,R> List<R> genParamList(List<T> input, Function<T, R> function) {
       return input.stream().map(function).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        List<People> peopleList = new ArrayList<>();
        People p1 = new People();
        p1.setName("yy");
        p1.setAge(22);
        People p2 = new People();
        p2.setName("tt");
        p2.setAge(33);
        peopleList.add(p1);
        peopleList.add(p2);

        List<String> names = genParamList(peopleList, People::getName);
        System.out.println("人名为：" + names);
    }

    /**
     *
     * @param input 入参
     * @param function1 函数1
     * @param function2 函数2
     * @return
     * @param <T> 函数1入参类型
     * @param <R> 返回类型为R
     * @param <V> 函数2入参类型
     */
    public static <T, R, V> R genericCompose(V input, Function<T, R> function1, Function<? super V, ? extends T> function2) {
        return function1.compose(function2).apply(input); // 应用Function接口
    }

    /**
     *
     * @param input
     * @param function1 函数1
     * @param function2 函数2
     * @return
     * @param <T> 入参类型
     * @param <R> 函数1返回类型
     * @param <V> 返回类型为V，即function2的返回值类型
     */
    public static <T, R, V> V genericAndThen(T input, Function<T, R> function1, Function<? super R, ? extends V> function2) {
        return  function1.andThen(function2).apply(input); // 应用Function接口
    }

}
