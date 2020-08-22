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
