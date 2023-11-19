package com.one_tree.idea_demo;

/**
 * debug小技巧：
 * 1.给断点加上条件
 * 2.断点不暂停并打印所需日志
 * 3.断点到光标所在行
 * 4.返回上一层调用的地方
 * 5.手动执行自定义语句
 */
public class ShowDebug {

    public static void main(String[] args) {
        int a =0;
        for(int i=0;i<=10;i++){
            //1. 给断点加上条件，比如循环到7的时候才暂停，不用一次次点
            if(i<= 5){
                //2. 断点不暂停，同时打印所需信息，也可以打印调用对战
                a++;
            }
        }
        //3. 断点到光标所在行
        System.out.println("---断点到光标所在行---");

        doSomeThing("调用doSomeThing方法");
    }

    private static void doSomeThing(String word){
        System.out.println("进入doSomeThing方法 ");
        //4. 返回上一层调用的地方
        //5. 手动执行自定义语句
        System.out.println("输入的word是： "+ word);
    }
}
