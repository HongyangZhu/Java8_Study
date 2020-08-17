# Java8_Study
关于Java8新特性的学习

2014年3月发布

- 速度更快
  - 修改底层数据结构：如HashMap（数组-链表-红黑树），HashSet，ConcurrentHashMap（CAS算法）
  - 修改垃圾回收机制：取消堆中的永久区（PremGen）->回收条件苛刻，使用元空间（MetaSpace）->直接使用物理内存->加载类文件
- 代码更少（增加了新的语法Lambda表达式）
- 强大的Stream API
- 便于并行
- 最大化减少空指针异常 Optional容器类

# Lambda表达式

Lambda是一个匿名函数，可以把Lambda表达式理解为是一段可以传递的代码（将代码像数据一样进行传递）。可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到了提升。

## 1.举例：

`(o1, o2) -> Integer.compare(o1, o2)`

## 2.格式：

| `->`     | 称为Lambda操作符 或 箭头操作符                     |
| -------- | -------------------------------------------------- |
| `->`左侧 | Lambda形参列表(其实就是接口中的抽象方法的形参列表) |
| `->`右侧 | Lambda体(其实就是重写的抽象方法的方法体)           |

## 3.Lambda表达式的使用：6种场合

语法格式一：无参，无返回值

```java
Runnable r1 = new Runnable() {
    @Override
    public void run() {
        System.out.println("This is general ");
    }
};
r1.run();
System.out.println("语法格式一：无参，无返回值");
Runnable r2 = () -> System.out.println("This is lambda");
r2.run();
```

语法格式二：有参，无返回值

```JAVA
Consumer<String> con = new Consumer<String>() {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
};
con.accept("Test");
System.out.println("语法格式二：有参，无返回值");
Consumer<String> con02 = (String s) -> System.out.println(s);
con02.accept("Test");
```

语法格式三：数据类型可以省略，因为可以由编译器推断得出，称为“类型推断”

```java
        System.out.println("语法格式三：数据类型可以省略，因为可以由编译器推断得出，称为“类型推断”");
//        Consumer<String> con02 = (String s) -> System.out.println(s);
        Consumer<String> con02 = (s) -> System.out.println(s);
        con02.accept("Test");
```

语法格式四：Lambda 若只需要一个参数时，参数的小括号可以省略

```java
        System.out.println("语法格式四：Lambda 若只需要一个参数时，参数的小括号可以省略");
//        Consumer<String> con02 = (s) -> System.out.println(s);
        Consumer<String> con02 = s -> System.out.println(s);
        con02.accept("Test");
```

语法格式五：Lambda 需要两个或者两个以上的参数，多条执行语句，并且有返回值时

```java
Comparator<Integer> com1 = new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        System.out.println(o1);
        System.out.println(o2);
        return o1.compareTo(o2);
    }
};
System.out.println(com1.compare(12, 21));
System.out.println("语法格式五：Lambda 需要两个或者两个以上的参数，多条执行语句，并且有返回值时");
Comparator<Integer> com2 = (o1, o2) -> {
    System.out.println(o1);
    System.out.println(o2);
    return o1.compareTo(o2);
};
System.out.println(com2.compare(12, 21));
```

语法格式六：当Lambda体只有一条语句时，return与大括号都可以省略

```java
Comparator<Integer> com1 = new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
};
System.out.println("语法格式六：当Lambda体只有一条语句时，return与大括号都可以省略");
Comparator<Integer> com2 = (o1, o2) -> o1.compareTo(o2);
```

## 4.Lambda本质：

作为接口（函数式接口）的实例

## 总结：

|            |                                                              |
| ---------- | ------------------------------------------------------------ |
| `->`左侧： | Lambda形参列表的参数类型可以省略,当参数列表只有一个参数时，可以省略() |
| `->`右侧： | 当Lambda体只有一条执行语句（可能是return语句），可以省略{}和return关键字 |

## 函数式接口

Lambda表达式需要“函数式接口”的支持

### 1.定义

当一个接口**有且只有一个**抽象方法，则此接口称为“函数式接口”

### 2.Runnable接口例子

java源码中Runnable接口就是一个函数式接口，**有且只有一个**抽象方法。并且**函数式接口**都用`@FunctionalInterface`注解进行标注了，当一个接口打上`@FunctionalInterface`注解之后就声明为一个**函数式接口**，这个接口中就只能有一个抽象方法，大于一个抽象方法就会报错。

```java
@FunctionalInterface
public interface Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see     java.lang.Thread#run()
     */
    public abstract void run();
}
```

> 注意
> 我们自己定义 函数式接口的时候，@Functionallnterface是可选的， 就算我不写这个注解，只要保证满足函数式接口定
> 义的条件，也照样是函数式接口。但是,建议加上该注解

### 3.自定义函数式接口

```java
@FunctionalInterface
public interface MyFun {
    public Integer getValue(Integer num);
}
```

```java
public class MyFunTest {
    @Test
    public void test() {
        Integer num = operation(100, (x) -> x * x);
        System.out.println(num);
        System.out.println(operation(200, (y) -> y + 200));
    }

    public Integer operation(Integer num, MyFun mf) {
        return mf.getValue(num);
    }
}
```

练习：调用Collections.sort()方法，通过定制排序比较两个Employee(先按年龄比，年龄相同按姓名比)，使用Lambda作为参数传递。

```java
    List<Employee> employees=Arrays.asList(
            new Employee("张三",18,9496.2),
            new Employee("李四",52,2396.2),
            new Employee("王五",56,996.2),
            new Employee("赵六",8,94.2)
    );

    @Test
    public void test1(){
         Collections.sort(employees, (e1,e2)->{
             if(e1.getAge()==e2.getAge()){
                 return e1.getName().compareTo(e2.getName());
             }else{
                 return Integer.compare(e1.getAge(), e2.getAge());
             }
         });

         for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
```

