# 1.集合
List 是一个有序集合，可通过索引（反映元素位置的整数）访问元素。元素可以在 list 中出现多次。列表的一个示例是一句话：有一组字、这些字的顺序很重要并且字可以重复。
Set 是唯一元素的集合。它反映了集合（set）的数学抽象：一组无重复的对象。一般来说 set 中元素的顺序并不重要。例如，字母表是字母的集合（set）。
Map（或者字典）是一组键值对。键是唯一的，每个键都刚好映射到一个值。值可以重复。map 对于存储对象之间的逻辑连接非常有用，例如，员工的 ID 与员工的位置。

## 构造集合
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

# 4.集合操作

## 1.扩展与成员函数

集合操作在标准库中以两种方式声明：集合接口的[成员函数](https://www.kotlincn.net/docs/reference/classes.html#类成员)和[扩展函数](https://www.kotlincn.net/docs/reference/extensions.html#扩展函数)

创建自己的集合接口实现时，必须实现其成员函数。 为了使新实现的创建更加容易，请使用标准库中集合接口的框架实现：[`AbstractCollection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-abstract-collection/index.html)、[`AbstractList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-abstract-list/index.html)、[`AbstractSet`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-abstract-set/index.html)、[`AbstractMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-abstract-map/index.html) 及其相应可变抽象类。



- [集合过滤](https://www.kotlincn.net/docs/reference/collection-filtering.html)

  ​	按谓词过滤 filter

  ```
  val numbers = listOf("one", "two", "three", "four")  
  val longerThan3 = numbers.filter { it.length > 3 }
  println(longerThan3)
  
  val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
  val filteredMap = numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10}
  println(filteredMap)
  ```

  filterIndexed()：使用元素在集合中的位置过滤

  filterNot() 反向过滤

  [`filterIsInstance()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/filter-is-instance.html) 返回给定类型的集合元素

  [`filterNotNull()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/filter-not-null.html) 返回所有的非空元素

   [`partition()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/partition.html) – 通过一个谓词过滤集合并且将不匹配的元素存放在一个单独的列表中

  ```
  val numbers = listOf("one", "two", "three", "four")
  val (match, rest) = numbers.partition { it.length > 3 }
  
  println(match)
  println(rest)
  
  result:
  [three, four]
  [one, two]
  ```

  - 如果至少有一个元素匹配给定谓词，那么 [`any()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/any.html) 返回 `true`。
  - 如果没有元素与给定谓词匹配，那么 [`none()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/none.html) 返回 `true`。
  - 如果所有元素都匹配给定谓词，那么 [`all()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/all.html) 返回 `true`
  - **注意，在一个空集合上使用任何有效的谓词去调用 `all()` 都会返回 `true` 。这种行为在逻辑上被称为 [*vacuous truth*](https://en.wikipedia.org/wiki/Vacuous_truth)**。

- [`plus` 与 `minus` 操作符](https://www.kotlincn.net/docs/reference/collection-plus-minus.html)

  ```kotlin
  val numbers = listOf("one", "two", "three", "four")
  //Plus 相当于累加
  val plusList = numbers + "five"
  //minus 去除
  val minusList = numbers - listOf("three", "four")
  println(plusList)
  println(minusList)
  
  [one, two, three, four, five]
  [one, two]
  ```

  

- [分组](https://www.kotlincn.net/docs/reference/collection-grouping.html)、

  ​	Kotlin 标准库提供用于对集合元素进行分组的扩展函数。 基本函数 [`groupBy()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/group-by.html) 使用一个 lambda 函数并返回一个 **`Map`。**

  ```
  val numbers = listOf("one", "two", "three", "four", "five")
  
  println(numbers.groupBy { it.first().toUpperCase() })
  println(numbers.groupBy(keySelector = { it.first() }, valueTransform = { it.toUpperCase() }))
  
  {O=[one], T=[two, three], F=[four, five]}
  {o=[ONE], t=[TWO, THREE], f=[FOUR, FIVE]}
  ```

  

- [取集合的一部分](https://www.kotlincn.net/docs/reference/collection-parts.html)

  [`slice()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/slice.html) 返回具有给定索引的集合元素列表

  ```
  val numbers = listOf("one", "two", "three", "four", "five", "six")    
  println(numbers.slice(1..3))
  println(numbers.slice(0..4 step 2))
  println(numbers.slice(setOf(3, 5, 0)))    
  
  
  
  ```
  
  **Take 与 drop**
  
  从头开始**获取**指定数量的元素，请使用 [`take()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/take.html) 函数。 从尾开始获取指定数量的元素，请使用 [`takeLast()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/take-last.html)。
  
  从头或从尾**去除**给定数量的元素，请调用 [`drop()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/drop.html) 或 [`dropLast()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/drop-last.html) 函数。

```kotlin
val numbers = listOf("one", "two", "three", "four", "five", "six")
//从头获取三个
println(numbers.take(3))
println(numbers.takeLast(3))
//从头去除一个
println(numbers.drop(1))
println(numbers.dropLast(5))

[one, two, three]
[four, five, six]
[two, three, four, five, six]
[one]
```

**Chunked** 分块

要将集合分解为给定大小的“块”

```
fun main() {
    val numbers = (0..10).toList()
    //每3个元素一块数据
    println(numbers.chunked(3))
}

[[0, 1, 2], [3, 4, 5], [6, 7, 8], [9, 10]]
```

**windowed**????不好理解

可以检索给定大小的集合元素中所有可能区间



- [取单个元素](https://www.kotlincn.net/docs/reference/collection-elements.html)

  

  ```
  // elementAt(index) 按下标取
  val numbers = linkedSetOf("one", "two", "three", "four", "five")
  println(numbers.elementAt(3))    
  
  //函数 first() 和 last() 
  val numbers = listOf("one", "two", "three", "four", "five", "six")
  println(numbers.first { it.length > 3 })
  println(numbers.last { it.startsWith("f") })
  
   random() 函数 随机取
    contains() 函数 是否存在
    
  ```

  

- [集合排序](https://www.kotlincn.net/docs/reference/collection-ordering.html)

  类似Java里对象之间可以比较，比较就可以排序。可以继承compareable，重写方法compareTo

  ```kotlin
  class Version(val major: Int, val minor: Int): Comparable<Version> {
      override fun compareTo(other: Version): Int {
          if (this.major != other.major) {
              return this.major - other.major
          } else if (this.minor != other.minor) {
              return this.minor - other.minor
          } else return 0
      }
  }
  
  fun main() {    
      println(Version(1, 2) > Version(1, 3))
      println(Version(2, 0) > Version(1, 5))
  }
  
  //如需为类型定义自定义顺序，可以为其创建一个 Comparator。 Comparator 包含 compare() 函数：它接受一个类的两个实例并返回它们之间比较的整数结果。
  
  val lengthComparator = Comparator { str1: String, str2: String -> str1.length - str2.length }
  println(listOf("aaa", "bb", "c").sortedWith(lengthComparator))
  ```

  `Comparator` 的一种比较简短的方式是标准库中的 [`compareBy()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.comparisons/compare-by.html) 函数

  ```
  println(listOf("aaa", "bb", "c").sortedWith(compareBy { it.length }))
  ```

  基本的函数

   [`sorted()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted.html)  升序

   [`sortedDescending()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-descending.html)   降序

   [`reversed()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/reversed.html) 函数以相反的顺序检索集合。

  [`asReversed()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/as-reversed.html) 反向函数

  [`shuffled()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/shuffled.html) 函数  随机产生一个新的list

- [集合聚合操作](https://www.kotlincn.net/docs/reference/collection-aggregate.html)
  - [`min()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/min.html) 与 [`max()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/max.html) 分别返回最小和最大的元素；
  - [`average()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/average.html) 返回数字集合中元素的平均值；
  - [`sum()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sum.html) 返回数字集合中元素的总和；
  - [`count()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/count.html) 返回集合中元素的数量；

## 2.list

- [`getOrElse()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/get-or-else.html) 提供用于计算默认值的函数，如果集合中不存在索引，则返回默认值。

- [`getOrNull()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/get-or-null.html) 返回 `null` 作为默认值。

  这两个方法能避免找不到元素异常

  **subList**  取一部分

   [`indexOf()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/index-of.html) 获取下标

   [`lastIndexOf()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/last-index-of.html) 

   [`binarySearch()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/binary-search.html) 函数  二分查找

  

## 3. set

  查找交集、并集或差集。

要将两个集合合并为一个（并集），可使用 [`union()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/union.html) 函数。

要查找两个集合中都存在的元素（交集），请使用 [`intersect()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/intersect.html) 。

要查找另一个集合中不存在的集合元素（差集），请使用 [`subtract()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/subtract.html) 

```kotlin
val numbers = setOf("one", "two", "three")

println(numbers union setOf("four", "five"))
println(setOf("four", "five") union numbers)

println(numbers intersect setOf("two", "one"))
println(numbers subtract setOf("three", "four"))
println(numbers subtract setOf("four", "three")) // 相同的输出

[one, two, three, four, five]
[four, five, one, two, three]
[one, two]
[one, two]
[one, two]
```

## 4.map

```kotlin
val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)
//根据key 去value
println(numbersMap.get("one"))
//另一种方式
println(numbersMap["one"])

println(numbersMap.getOrDefault("four", 10))
println(numbersMap["five"])               // null
//numbersMap.getValue("six")  
```

- [`getOrElse()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/get-or-else.html) 与 list 的工作方式相同：对于不存在的键，其值由给定的 lambda 表达式返回。
- [`getOrDefault()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/get-or-default.html) 如果找不到键，则返回指定的默认值。

`keys` 是 Map 中所有键的集合， `values` 是 Map 中所有值的集合。

```
val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)
println(numbersMap.keys)
println(numbersMap.values)
```

### 4.1过滤

```kotlin
val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
//根据filter过滤符合条件的数据
val filteredMap = numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10}
println(filteredMap)

// filterKeys() 和 filterValues() 也可以通过两种形式过滤
```

### 4.2`plus` 与 `minus` 操作

```kotlin
val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)
//累加map 集合数据，以pair形式添加
println(numbersMap + Pair("four", 4))
//如果存在key,会进行value的覆盖
println(numbersMap + Pair("one", 10))
//可以接收map形式的数据
println(numbersMap + mapOf("five" to 5, "one" to 11))


//minus 将根据左侧 Map 条目创建一个新 Map ，右侧操作数带有键的条目将被剔除。 因此，右侧操作数可以是单个键或键的集合： list 、 set 等。
val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)
println(numbersMap - "one")
println(numbersMap - listOf("two", "four"))
```

**关于在可变 Map 中使用 [`plusAssign`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/plus-assign.html)（`+=`）与 [`minusAssign`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/minus-assign.html)（`-=`）运算符的详细信息**

### 4.3 增删改查

put 

putall()  它的参数可以是 `Map` 或一组 `Pair` ： `Iterable` 、 `Sequence` 或 `Array` 。

+= 追加map数据

-= 移除map 数据

[]  设置新的数据

```kotlin
val numbersMap = mutableMapOf("one" to 1, "two" to 2)
numbersMap["three"] = 3     // 调用 numbersMap.set("three", 3)
numbersMap += mapOf("four" to 4, "five" to 5)
numbersMap -= "four" //根据key移除数据
println(numbersMap)
```

Remove()

```kotlin
val numbersMap = mutableMapOf("one" to 1, "two" to 2, "three" to 3, "threeAgain" to 3)
numbersMap.keys.remove("one")//根据key 移除
println(numbersMap)
numbersMap.values.remove(3) //根据value 移除
println(numbersMap)
```

