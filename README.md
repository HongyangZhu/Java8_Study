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

## 🔗[Lambda表达式](markdown/Lambda.md)

Lambda是一个匿名函数，可以把Lambda表达式理解为是一段可以传递的代码（将代码像数据一样进行传递）。可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到了提升。

## 🔗[函数式接口](markdown/Functional.md)

函数式接口(Functional Interface)就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。

## 🔗[方法引用](markdown/MethodRef.md)

**方法引用**是用来直接访问**类**或者**实例**的**已经存在的方法或者构造方法**。方法引用提供了一种引用而不执行方法的方式，它需要由兼容的函数式接口构成的目标类型上下文。计算时，方法引用会创建函数式接口的一个实例。

## 🔗[Stream API](markdown/StreamAPI.md)

Stream 是Java8中**处理集合的关键抽象概念**，它可以对**集合**进行非常复杂的**查找**、**过滤**、**筛选**等操作。使用**Stream API**对集合数据操作，就类似于使用SQL执行的数据库查询。也可以使用**Stream API**来**并行**执行操作。

## 🔗[Optional类](markdown/Optional.md)

Optional< T>类(**java.util.Optional**) 是一个容器类，代表一个值存在或不存在。
原来用null表示一个值不存在，现在 Optional可以更好的表达这个概念。并且可以避免空指针异常。