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



- ## [Lambda表达式](markdown/Lambda.md)

- ## [方法引用](markdown/MethodRef.md)

- ## [Stream API](markdown/StreamAPI.md)

- ## [Optional类](markdown/Optional.md)