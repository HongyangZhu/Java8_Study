package day01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 消费型接口 Consumer<T>    void accept(T t)
 * 供给型接口 Supplier<T>    T  get()
 * 函数型接口 Function<T,R>  R apply(T t)
 * 断定型接口 Predicate<T>   boolean test(T t)
 */
public class LambdaTest02 {
    @Test
    public void test_Consumer() {
        happyTime(100, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("Money:" + aDouble);
            }
        });
        System.out.println("消费型接口 Consumer<T>");
        happyTime(100, money -> System.out.println("Money:" + money));
    }

    public void happyTime(double money, Consumer<Double> con) {
        con.accept(money);
    }

    @Test
    public void test_Predicate() {
        List<String> list = Arrays.asList("北京", "南京", "天津");
        ArrayList<String> filterString01 = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(filterString01);
        System.out.println("断定型接口 Predicate<T>");
        ArrayList<String> filterString02 = filterString(list, s -> s.contains("京"));
        System.out.println(filterString02);
    }

    /**
     * 根据给定的规则，过滤集合中的字符串
     * 此规则由Predicate的方法决定
     *
     * @param list 待过滤字符串集合
     * @param pre  规则
     * @return 过滤后的字符串
     */
    public ArrayList<String> filterString(List<String> list, Predicate<String> pre) {
        ArrayList<String> filterList = new ArrayList<>();
        for (String s : list) {
            if (pre.test(s)) {
                filterList.add(s);
            }
        }
        return filterList;
    }

    //Supplier<T> 供给型接口:
    //需求：产生指定个数的整数，并放入集合中
    @Test
    public void test_Supplier() {
        List<Integer> numList01 = getNumList(10, new Supplier<Integer>() {
            @Override
            public Integer get() {
                return (int) (Math.random() * 100);
            }
        });
        System.out.println(numList01);
        System.out.println("供给型接口 Supplier<T>");
        List<Integer> numList02 = getNumList(10, () -> (int) (Math.random() * 100));
        System.out.println(numList02);
    }

    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }

    //Function<T,R> 函数型接口:
    //需求：处理字符串
    @Test
    public void test3() {
        String newStr01 = strHandler("\t\t\t 啦啦啦德玛西亚  ", new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.trim();
            }
        });
        System.out.println(newStr01);
        System.out.println("函数型接口 Function<T,R>");
        String newStr = strHandler("\t\t\t 啦啦啦德玛西亚  ", (str) -> str.trim());
        System.out.println(newStr);

        String subStr = strHandler("无与伦比，为杰沉沦", (str) -> str.substring(5, 9));
        System.out.println(subStr);
    }

    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }
}
