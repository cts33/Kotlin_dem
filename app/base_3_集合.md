# 1.集合
**List 是一个有序集合**，可通过索引（反映元素位置的整数）访问元素。元素可以在 list 中出现多次。列表的一个示例是一句话：有一组字、这些字的顺序很重要并且字可以重复。
**Set 是唯一元素的集合**。它反映了集合（set）的数学抽象：一组无重复的对象。一般来说 set 中元素的顺序并不重要。例如，字母表是字母的集合（set）。
**Map（或者字典）是一组键值对。**键是唯一的，每个键都刚好映射到一个值。值可以重复。map 对于存储对象之间的逻辑连接非常有用，例如，员工的 ID 与员工的位置。

## 1.1构造集合
创建集合的最常用方法是使用标准库函数 listOf<T>()、setOf<T>()、mutableListOf<T>()、mutableSetOf<T>()。

Map 也有这样的函数 mapOf() 与 mutableMapOf()
`val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)`



## 1.2 集合类型

- 一个 *只读* 接口，提供访问集合元素的操作。
- 一个 *可变* 接口，通过写操作扩展相应的只读接口：添加、删除和更新其元素。

![Collection interfaces hierarchy](https://www.kotlincn.net/assets/images/reference/collections-overview/collections-diagram.png)

## 1.3 空集、初始化集合
`emptyList()、emptySet() 与 emptyMap()。`

list**初始化**

```kotlin
val doubled = List(3, { it * 2 })  // 如果你想操作这个集合，应使用 MutableList
println(doubled)
```

```kotlin
//具体类型的构造函数
val linkedList = LinkedList<String>(listOf("one", "two", "three"))
val presizedSet = HashSet<Int>(32)
```

**复制  **
创建与现有集合具有相同元素的集合，可以使用复制操作

在特定时刻通过集合复制函数，例如[`toList()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/to-list.html)、[`toMutableList()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/to-mutable-list.html)、[`toSet()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/to-set.html) 等等。创建了集合的快照



```
val sourceList = mutableListOf(1, 2, 3)
val copyList = sourceList.toMutableList()
val readOnlyCopyList = sourceList.toList()
sourceList.add(4)
```

还可用于将集合转换为其他类型

```
val sourceList = mutableListOf(1, 2, 3)    
val copySet = sourceList.toMutableSet()
copySet.add(3)
copySet.add(4)    
println(copySet)
```

**更多**：

```kotlin
val numbers = listOf("one", "two", "three", "four")  
val longerThan3 = numbers.filter { it.length > 3 }
println(longerThan3)


//映射map
val numbers = setOf(1, 2, 3)
println(numbers.map { it * 3 })
println(numbers.mapIndexed { idx, value -> value * idx })

[3, 6, 9]
[0, 2, 6]


//关联生成map
val numbers = listOf("one", "two", "three", "four")
println(numbers.associateWith { it.length })

{one=3, two=3, three=5, four=4}
```



## 1.4 迭代器
```kotlin
val numbers = listOf("one", "two", "three", "four")
val numbersIterator = numbers.iterator()
while (numbersIterator.hasNext()) {
    println(numbersIterator.next())
}

List 迭代器

val numbers = listOf("one", "two", "three", "four")
val listIterator = numbers.listIterator()
while (listIterator.hasNext()) listIterator.next()

//为了迭代可变集合，于是有了 MutableIterator 来扩展 Iterator 使其具有元素删除函数 remove() 。因此，可以在迭代时从集合中删除元素。插入和替换元素。

val numbers = mutableListOf("one", "two", "three", "four") 
val mutableIterator = numbers.iterator()

mutableIterator.next()
mutableIterator.remove()  
mutableListIterator.add("two")
mutableListIterator.next()
mutableListIterator.set("three") 

```

# 2.序列
除集合外 还有一种容器 **Sequenec**<T>

## 1.1 元素
```
val n = sequenecof("one","two")
```

## 1.2 Iterable
如果已经有一个iterable对象，可以调用asSequence()

```kotlin
val num = listof("one","two")
val numsequ = num.asSequnce(); 
```

## 1.3 函数????
```kotlin
//首次1执行，返回的结果+2再次执行
val oddNumbers = generateSequence(1) { it + 2 } // `it` 是上⼀个元素
println(oddNumbers.take(5).toList())//执行5次

val oddNumbersLessThan10 = generateSequence(1) { if (it + 2 < 10) it + 2 else null }
println(oddNumbersLessThan10.count())
```
## 1.4 组块、
```kotlin
    val oddNumbers = sequence {
        yield(1)
        yieldAll(listOf(3, 5))
        yieldAll(generateSequence(7) { it + 2 })
    }
    println(oddNumbers.take(5).toList())
```
    每一个sequence 内部都是一个元素，虽然是函数

##  1.5 序列操作
可以独立处理每个元素 map()  filter()
take() drop()
如果序列操作返回延迟⽣成的另⼀个序列，则称为 中间序列

## 1.6 Iterable 与 Sequence 之间的区别。

Iterable特点：每个条件独立执行
```kotlin
val words = "The quick brown fox jumps over the lazy dog".split(" ")
val lengthsList = words.filter { println("filter: $it"); it.length > 3 }
.map { println("length: ${it.length}"); it.length }
.take(4)
println("Lengths of first 4 words longer than 3 chars:")
println(lengthsList)

```


  ```kotlin
//Sequence 执行同时满足多种条件的逻辑

    val words = "The quick brown fox jumps over the lazy dog".split(" ")
// 将列表转换为序列
    val wordsSequence = words.asSequence()
    val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
        .map { println("length: ${it.length}"); it.length }
        .take(4)
    println("Lengths of first 4 words longer than 3 chars")
// 末端操作：以列表形式获取结果。
    println(lengthsSequence.toList())
   
结果：

    Lengths of first 4 words longer than 3 chars
    filter: The
    filter: quick
    length: 5
    filter: brown
    length: 5
    filter: fox
    filter: jumps
    length: 5
    filter: over
    length: 4
    [5, 5, 5, 4]
  ```

# 3 集合转换

### 2.1 映射map  映射 转换从另⼀个集合的元素上的函数结果创建⼀个集合

```kotlin
val numbers = setOf(1, 2, 3)
println(numbers.map { it * 3 })

```

### 2.2合拢 zip  合拢 转换是根据两个集合中具有相同位置的元素构建配对，不是map形式
```kotlin
//zip() 返回 Pair 对象的列表（ List ）

val colors = listOf("red", "brown", "grey")
val animals = listOf("fox", "bear", "wolf")
println(colors zip animals)
```



### 2.3 关联 associateWith  关联 转换允许从集合元素和与其关联的某些值构建 Map

```kotlin
val numbers = listOf("one", "two", "three", "four")
println(numbers.associateWith { it.length })

结果：
{one=3, two=3, three=5, four=4}
 
```

### 2.4 打平 flatten  去除多层集合内部的层级

```kotlin
val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
println(numberSets.flatten())
```



### 2.5 序列转换成字符串  joinToString()

```kotlin
val numbers = listOf("one", "two", "three", "four")
println(numbers)
println(numbers.joinToString())

```


