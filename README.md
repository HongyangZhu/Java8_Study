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

### 3.1.语法格式一：

无参，无返回值

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

### 3.2.语法格式二：

有参，无返回值

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

### 3.3.语法格式三：

数据类型可以省略，因为可以由编译器推断得出，称为“**类型推断**”

```java
        System.out.println("语法格式三：数据类型可以省略，因为可以由编译器推断得出，称为“类型推断”");
//        Consumer<String> con02 = (String s) -> System.out.println(s);
        Consumer<String> con02 = (s) -> System.out.println(s);
        con02.accept("Test");
```

### 3.4.语法格式四：

Lambda 若**只需要一个**参数时，参数的小括号可以省略

```java
        System.out.println("语法格式四：Lambda 若只需要一个参数时，参数的小括号可以省略");
//        Consumer<String> con02 = (s) -> System.out.println(s);
        Consumer<String> con02 = s -> System.out.println(s);
        con02.accept("Test");
```

### 3.5.语法格式五：

Lambda 需要**两个或者两个以上**的参数，多条执行语句，并且有返回值时

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

### 3.6.语法格式六：

当Lambda体只有一条语句时，return与大括号都可以省略

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

## [小结]：

|            |                                                              |
| ---------- | ------------------------------------------------------------ |
| `->`左侧： | Lambda形参列表的参数类型可以省略,当参数列表只有一个参数时，可以省略() |
| `->`右侧： | 当Lambda体只有一条执行语句（可能是return语句），可以省略{}和return关键字 |

## 5函数式接口

Lambda表达式需要“函数式接口”的支持

### 5.1.定义

当一个接口**有且只有一个**抽象方法，则此接口称为“函数式接口”

### 5.2.Runnable接口例子

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

### 5.3.自定义函数式接口

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

### 5.4.Java核心内置的函数式接口

为了免去用户每次使用Lambda表达式时，都自行创建函数式接口，Java提供了4大核心内置 函数式接口

| 函数式接口                | 参数类型 | 返回类型 | 用途                                                         |
| ------------------------- | -------- | -------- | ------------------------------------------------------------ |
| `Consumer<T>`消费型接口   | T        | void     | 对类型为T的对象应用操作，包含方法：`void accept(T t)`        |
| `Supplier<T>`供给型接口   | 无       | T        | 返回类型为T的对象，包含方法：`T  get()`                      |
| `Function<T,R>`函数型接口 | T        | R        | 对类型为T的对象应用操作，并返回结果。结果是R类型的对象。包含方法：`R apply(T t)` |
| `Predicate<T>`断定型接口  | T        | boolean  | 确定类型为T的对象是否满足某约束，并返回boolean值。包含方法：`boolean test(T t)` |

> 对于常见的泛型模式，推荐的泛型类型变量：
>
> -  E - Element (在集合中使用，因为集合中存放的是元素)
> -  T - Type（Java 类）
> -  K - Key（键）
> -  V - Value（值）
> -  N - Number（数值类型）
> -  R - Result （返回结果，多用于函数式编程）
> - ？ - 表示不确定的java类型

转载自：https://www.cnblogs.com/wuyx/p/9000312.html

![img](https://img2018.cnblogs.com/blog/873028/201907/873028-20190708152246594-1218267559.png)

 其他补充接口：

![img](https://img2018.cnblogs.com/blog/873028/201907/873028-20190708153608472-1533962470.png)

### 5.5.函数式接口例子

#### **一、Consumer<T>：消费型接口（void accept(T t)）**

来看一个简单得例子：

```java
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
```

以上为消费型接口，有参数，无返回值类型的接口。

#### **二、Supplier<T>：供给型接口（T get（））**

　　来看一个简单得例子：

```java
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
```

上面就是一个供给类型得接口，只有产出，没人输入，就是只有返回值，没有入参

#### **三、Function<T, R>：函数型接口（R apply（T t））**

　　下面看一个简单的例子：

```java
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
```

上面就是一个函数型接口，输入一个类型得参数，输出一个类型得参数，当然两种类型可以一致。

#### **四、Predicate<T>：断言型接口（boolean test（T t））**

　　下面看一个简单得例子：

```java
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
```

上面就是一个断言型接口，输入一个参数，输出一个boolean类型得返回值。

# 方法引用

在学习lambda表达式之后，我们通常使用lambda表达式来创建匿名方法。然而，有时候我们仅仅是调用了一个已存在的方法。如下：

```java
Arrays.sort(stringsArray,(s1,s2)->s1.compareToIgnoreCase(s2));
```

 在Java8中，我们可以直接通过方法引用来**简写**lambda表达式中已经存在的方法。

```java
Arrays.sort(stringsArray, String::compareToIgnoreCase);
```

这种特性就叫做方法引用(Method Reference)。 

**方法引用**是用来直接访问**类**或者**实例**的**已经存在的方法或者构造方法**。方法引用提供了一种引用而不执行方法的方式，它需要由兼容的函数式接口构成的目标类型上下文。计算时，方法引用会创建函数式接口的一个实例。


- 当要传递给Lambda体的操作，已经有实现的方法时，可以使用方法引用。

- 方法引用可以看做是Lambda表达式深层次的表达。换句话说，方法引用就是Lambda表达式，也就是函数式接口的一个实例，通过方法的名字来指向一个方法，可以认为是Lambda表达式的一个语法糖。

  > 语法糖（Syntactic sugar），也译为糖衣语法，是由英国计算机科学家彼得·约翰·兰达（Peter J. Landin）发明的一个术语，指计算机语言中添加的某种语法，这种语法对语言的功能并没有影响，但是更方便程序员使用。通常来说使用语法糖能够增加程序的可读性，从而减少程序代码出错的机会。

- **要求**：实现接口的抽象方法的**参数列表**和**返回值类型**，必须与方法引用的方法参数列表和返回值类型保持一致。
- 格式：使用操作符`::`将类（或对象）与方法名分隔开来。

## 1、方法引用的使用场景

当要传递给Lambda体的操作，已经有实现的方法时，可以使用方法引用。

```java
@Test
public void test01() {
    // 方法引用
    Consumer<String> consumer02 = System.out::println;
    consumer02.accept("Test");
}
```

## 2、方法引用的分类

| 类型                             | 语法                            | 对应的Lambda表达式                        |
| -------------------------------- | ------------------------------- | ----------------------------------------- |
| 静态方法引用                     | ClassName :: staticMethodName   | (args) -> ClassName.staticMethod(args)    |
| 某个对象的实例方法引用           | object :: instanceMethodName    | (args) -> inst.instMethod(args)           |
| 某个类型的任意对象的实例方法引用 | ClassName :: instanceMethodName | (inst,args) -> ClassName.instMethod(args) |
| 构建方法引用                     | ClassName :: new                | (args) -> new ClassName1(args)            |

## 3、方法引用举例

### 3.1 对象 :: 非静态方法

 特定对象的实例方法引用的语法格式为： `object :: instanceMethodName` ，如
System.out::println 等价于lambda表达式 s -> System.out.println(s) 

代码示例：

```java
/**
 * 情况一：
 * 对象 :: 非静态方法
 * Consumer中的 void accept(T t)
 * PrintStream中的 public void println(String x) 
 */
@Test
public void test01() {
    // Lambda表达式
    Consumer<String> consumer01 = str -> System.out.println(str);
    consumer01.accept("Test");
    // 方法引用
    Consumer<String> consumer02 = System.out::println;
    consumer02.accept("Test");
}
```

```java
/**
 * Supplier 中的 T get()
 * Employee 中的 public String getName()
 */
@Test
public void test02() {
    Employee emp = new Employee("Tom", 12, 1000);
    // Lambda表达式
    Supplier<String> supplier01 = () -> emp.getName();
    System.out.println(supplier01.get());
    // 方法引用
    Supplier<String> supplier02= emp::getName;
    System.out.println(supplier02.get());
}
```

### 3.2 类 :: 静态方法

**静态方法引用**,**组成语法格式：**`ClassName :: staticMethodName`

代码示例：

```java
/**
 * 情况二：
 * 类 :: 静态方法
 * Comparator 中的 int compare(T o1, T o2)
 * Integer 中的 public static int compare(int x, int y)
 */
@Test
public void test03() {
    // Lambda表达式
    Comparator<Integer> comparator01 = (o1, o2) -> Integer.compare(o1, o2);
    System.out.println(comparator01.compare(12, 21));
    // 方法引用
    Comparator<Integer> comparator02 = Integer::compareTo;
    System.out.println(comparator02.compare(12, 21));
}
```

```java
/**
 * Function 中的 R apply(T t)
 * Math 中的 public static long round(double a)
 */
@Test
public void test04() {
    // Lambda表达式
    Function<Double, Long> function01 = d -> Math.round(d);
    System.out.println(function01.apply(12.3));
    // 方法引用
    Function<Double, Long> function02 =Math::round;
    System.out.println(function02.appl(12.3));
}
```

### 3.3 类 :: 非静态方法

代码示例：

```java
/**
 * 情况三：
 * 类 :: 非静态方法
 * Comparator 中的 int compare(T o1, T o2)
 * String 中的 public int compareTo(String anotherString)
 */
@Test
public void test05() {
    // Lambda表达式
    Comparator<String> comparator01 = (s1, s2) -> s1.compareTo(s2);
    System.out.println(comparator01.compare("abc", "abd"));
    // 方法引用
    Comparator<String> comparator02 = String::compareTo;
    System.out.println(comparator02.compare("abc", "abd"));
}
```

### 3.4 构造器引用

和方法引用类似，**函数式接口的抽象方法的形参列表跟构造器的形参列表一致**。
抽象方法的返回值类即为构造器所属的类的类型

代码示例：

```java
/**
 * 构造器引用
 * Supplier 中的 T get()
 * Employee 中的 public Employee()
 */
@Test
public void test01() {
    // Lambda表达式
    Supplier<Employee> sup01 = () -> new Employee();
    sup01.get();
    // 方法引用
    Supplier<Employee> sup02 = Employee::new;
    sup02.get();
}
```

```java
/**
 * 构造器引用
 * Function 中的 R apply(T t);
 * Employee 中的 public Employee(String name)
 */
@Test
public void test02() {
    // Lambda表达式
    Function<String, Employee> sup01 = (s) -> new Employee(s);
    System.out.println(sup01.apply("Tom"));
    // 方法引用
    Function<String, Employee> sup02 = Employee::new;
    System.out.println(sup01.apply("Tom"));
}
```

```java
/**
 * 构造器引用
 * BiFunction 中的 R apply(T t, U u);
 * Employee 中的 public Employee(String name, int age)
 */
@Test
public void test03() {
    // Lambda表达式
    BiFunction<String, Integer, Employee> biFunction01 = (name, age) -> new Employee(name, age);
    System.out.println(biFunction01.apply("Tom", 18));
    // 方法引用
    BiFunction<String, Integer, Employee> biFunction02 = Employee::new;
    System.out.println(biFunction02.apply("Tom", 18));
}
```

### 3.5 数组引用

```java
/**
 * 数组引用
 * Function 中的 R apply(T t);
 */
@Test
public void test04() {
    // Lambda表达式
    Function<Integer, String[]> function01 = (length) -> new String[length];
    System.out.println(function01.apply(10).length);
    // 方法引用
    Function<Integer, String[]> function02 = String[]::new;
    System.out.println(function02.apply(10).length);
}
```

# Stream API