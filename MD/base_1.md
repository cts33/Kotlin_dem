# 1.基础语法

## 1.函数
函数传参  
返回值：Unit代表无返回，可以忽略

```kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}
```

## 2.变量
定义只读局部变量使用关键字 val 定义。只能为其赋值一次
可重新赋值的变量使用 var 关键字：
顶层变量：val PI = 3.14

val 类似常量，引用不可变

var 变量

## 3.字符串模板
```kotlin
var a = 1
// 模板中的简单名称：
val s1 = "a is $a" 

a = 2
// 模板中的任意表达式：
val s2 = "${s1.replace("is", "was")}, but now is $a"
```

## 4.空值与 null 检测

当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空

```
var str:String? = null
```

## 5.类型检测与自动类型转换
is 运算符检测一个表达式是否某类型的一个实例
```kotlin
if (obj is String) {
        // `obj` 在该条件分支内自动转换成 `String`
        return obj.length
}
```
## 6.for while when
```kotlin

val items = listOf("apple", "banana", "kiwifruit")
for (item in items) {
    println(item)
}


val items = listOf("apple", "banana", "kiwifruit")
var index = 0
while (index < items.size) {
    println("item at $index is ${items[index]}")
    index++
}
//类似于switch
when (obj) {
    1          -> "One"
    "Hello"    -> "Greeting"
    is Long    -> "Long"
    !is String -> "Not a string"
    else       -> "Unknown"
}
```


## 7.使用区间（range） in

```kotlin

if (x in 1..y+1) {
    println("fits in range")
}


```
# 2.基本类型

## 1.数字
Byte 

Short

Int 

Long
如果初始值超过了其最大值，那么推断为 Long 类型
如需显式指定 Long 型值，请在该值**后追加 L 后缀**

Float	32
Double	64

## 2.字面常量
十进制: 123
Long 类型用大写 L 标记: 123L
十六进制: 0x0F
二进制: 0b00001011

## 3.位运算
shl(bits) – 有符号左移
shr(bits) – 有符号右移
ushr(bits) – 无符号右移
and(bits) – 位与
or(bits) – 位或
xor(bits) – 位异或  ???
inv() – 位非   ?

## 4.字符

字符用 Char 类型表示
## 5.数组

数组在 Kotlin 中使用 Array 类来表示，它定义了 get 与 set 函数（按照运算符重载约定这会转变为 []）以及 
size 属性,Kotlin 中数组是**不型变的**（invariant）。这意味着 Kotlin 不让我们把 Array<String> 赋值给 Array<Any>
val array = arrayOf(1, 2, 3)
val array = arrayOfNulls()

## 6.原生类型数组
Kotlin 也有无装箱开销的专门的类来表示原生类型数组: ByteArray、 ShortArray、IntArray 等等。这些类与 Array 并没有继承关系
// 大小为 5、值为 [0, 0, 0, 0, 0] 的整型数组
val arr = IntArray(5)

## 7.字符串
字符串用 String 类型表示。字符串是不可变的。 字符串的元素——字符可以使用索引运算符访问: s[i]

```
for (c in str) {
	println(c)
}
```



# 3.类与继承
Kotlin 中使用关键字 class 声明类

```kotlin
class Person constructor(firstName: String) { /*……*/ }
```

在 Kotlin 中的一个类可以有一个主构造函数以及一个或多个次构造函数
如果**主构造函数没有任何注解或者可见性修饰符**，可以省略这个 constructor 关键字。

```kotlin
class Person(firstName: String) { /*……*/ }
```


## 1.次构造函数
类也可以声明前缀有 constructor的次构造函数：
如果类**有一个主构造函数，每个次构造函数需要委托给主构造函数**， 可以直接委托或者通过
别的次构造函数间接委托。委托到同一个类的另一个构造函数用 this 关键字即可：

```kotlin
class Person(val name: String) {
    var children: MutableList<Person> = mutableListOf()
     
    constructor(name: String, parent: Person) : this(name) {
        parent.children.add(this)
    }
}
```
## 2 继承
在 Kotlin 中所有类都有一个**共同的超类 Any**，这对于没有超类型声明的类是默认超类

**Any 有三个方法：equals()、 hashCode() 与 toString()**
默认情况下，Kotlin 类是最终（final）的：它们不能被继承。 要使一个类可继承，请用 **open** 关键字标记它

open class Base // 该类开放继承
```kotlin
open class Base(p: Int)

class Derived(p: Int) : Base(p)
```
### 1.覆盖方法  覆盖属性

Circle.draw() 函数上必须加上 **override 修饰**符
你也可以用**一个 var 属性覆盖一个 val 属性**

```kotlin
open class Shape {
    open val vertexCount: Int = 0
    open fun draw() { /*……*/ }
    fun fill() { /*……*/ }
}

class Circle() : Shape() {
    override val vertexCount = 4
    override fun draw() { /*……*/ }
}
```
### 2.覆盖规则
如果一个类从它的直接超类继承**相同成员的多个实现**， 它必须**覆盖这个成员并提供其自己的实现**（也许用继承来的其中之一）
```kotlin
open class Rectangle {
    open fun draw() { /* …… */ }
}

interface Polygon {
    fun draw() { /* …… */ } // 接口成员默认就是“open”的
}

class Square() : Rectangle(), Polygon {
    // 编译器要求覆盖 draw()：
    override fun draw() {
        super<Rectangle>.draw() // 调用 Rectangle.draw()
        super<Polygon>.draw() // 调用 Polygon.draw()
    }
}
```
## 3.抽象类
类以及其中的某些成员可以声明为 **abstract**。 抽象成员在本类中**可以不用实现**。 需要注意的是，我们并**不需要用 open 标注一个抽象类或者函数**。
```kotlin
open class Polygon {
    open fun draw() {}
}
abstract class Rectangle : Polygon() {
    abstract override fun draw()
}
```
### 1.属性
Kotlin 类中的属性既可以用关键字 **var 声明为可变的**，也可以用关键字 **val 声明为只读**的。
```kotlin
class Address {
    var name: String = "Holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"
}

val result = Address() // Kotlin 中没有“new”关键字
result.name = address.name // 将调用访问器
result.street = address.street
```
### 2.Getters 与 Setters

属性声明涉及三个点：**初始器（initializer）、getter 和 setter** 。属性默认情况下都有set和get方法。

```kotlin
var allByDefault: Int? // 错误：需要显式初始化器，隐含默认 getter 和 setter
var initialized = 1 // 类型 Int、默认 getter 和 setter

val simple: Int? // 类型 Int、默认 getter、必须在构造函数中初始化
val inferredType = 1 // 类型 Int 、默认 getter
```

val 因为不可变，所以**没有set方法**，必须在构造函数初始化

var 必须显式初始化

**自定义get方法set方法**

```kotlin
var stringRepresentation: String
    get() = this.toString() //get方法
    set(value) {			//set方法
        setDataFromString(value) // 解析字符串并赋值给其他属性
    }
```
### 3.延迟初始化属性与变量lateinit

属性声明为非空类型必须在构造函数中初始化。 然而，这经常不方便.

该修饰符只能**用于在类体中的属性**

```kotlin
public class MyTest {
    lateinit var subject: TestSubject

    @SetUp fun setup() {
        subject = TestSubject()
    }

    @Test fun test() {
        subject.method()  // 直接解引用
    }
}
```
检测一个 lateinit var 是否已初始化

```kotlin
if (foo::bar.isInitialized) {
    println(foo.bar)
}
```

## 4.接口
Kotlin 的接口可以既包含**抽象方法的声明也包含实现**
```kotlin
interface MyInterface {
    fun bar()
    fun foo() {
        // 可选的方法体
    }
}
//实现接口
class Child : MyInterface {
    override fun bar() {
        // 方法体
    }
}
```
### 1 函数式（SAM）接口
**只有一个抽象方法的接口称为函数式接口**或 SAM（单一抽象方法）接口。函数式接口可以有多个非抽象成员，但只能有一个抽象成员。

```kotlin
fun interface KRunnable {
   fun invoke()
}
```

**使用lambda表达式**

```kotlin
// 创建一个类的实例
val isEven = object : IntPredicate {
   override fun accept(i: Int): Boolean {
       return i % 2 == 0
   }
}
```

```
val isEven = IntPredicate{ i%2==0 }
```

### 2.接口中的属性？？？

在接口中声明的属性要么是抽象的，要么提供访问器的实现



### 3.可见性修饰符

private、它只会在声明**它的文件内可**见；
protected、 不适用于顶层声明。子类可见
internal 它会在**相同模块内随处可见**
public 随处可见,默认可见



### 4.扩展函数

Kotlin 能够扩展一个类的新功能而无需继承该类或者使用像装饰者这样的设计模式.

**扩展函数**

```kotlin
//扩展声明
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // “this”对应该列表
    this[index1] = this[index2]
    this[index2] = tmp
}

//调用
val list = mutableListOf(1, 2, 3)
list.swap(0, 2) // “swap()”内部的“this”会保存“list”的值
```

也可以通过泛型兼容不同类型

**扩展是静态解析的**

扩展不能真正的**修改他们所扩展的类**。通过定义一个扩展，并没有在一个类中插入新成员， 仅仅是可以通过该类型的变量用点表达式去调用这个新函数。

**扩展属性**

```
val <T> List<T>.lastIndex: Int
    get() = size - 1
```

**伴生对象的扩展**



### 5.数据类

只保存数据的类data

```
data class User(val name: String, val age: Int)
```

- `equals()`/`hashCode()` 对；
- `toString()` 格式是 `"User(name=John, age=42)"`；
- [`componentN()` 函数](https://www.kotlincn.net/docs/reference/multi-declarations.html) 按声明顺序对应于所有属性；
- `copy()` 函数
- 主构造函数需要至少有一个参数；
- 主构造函数的所有参数需要标记为 `val` 或 `var`；
- 数据类不能是抽象、开放、密封或者内部的；
- （在1.1之前）数据类只能实现接口。

#### copy
在很多情况下，我们需要复制一个对象改变它的一些属性，但其余部分保持不变.

```
val jack = User(name = "Jack", age = 1)
val olderJack = jack.copy(age = 2)

```

#### 数据类与解构声明

```
val jane = User("Jane", 35)
val (name, age) = jane
println("$name, $age years of age") // 输出 "Jane, 35 years of age
```

### 6.密封类？？？？

密封类用来表示受限的类继承结构：当一个值为有限几种的类型、而不能有任何其他类型时。

在某种意义上，他们是**枚举类的扩展**：**枚举类型的值集合也是受限的**，但每个**枚举常量只存在一个实例**，而密封类的一个子类可以有可包**含状态的多个实例**。

### 7.泛型

```kotlin
class Box<T>(t: T) {
    var value = t
}
```

**型变**

声明处型变（declaration-site variance）与类型投影（type projections）



#### 声明处型变



#### 类型投影





### 8 嵌套类与内部类

You can also use interfaces with nesting. All combinations of classes and interfaces are possible: You can nest interfaces in classes, classes in interfaces, and interfaces in interfaces.

你可以在类中嵌套接口，在接口中嵌套类，在接口里嵌套接口。

#### 内部类

标记为 *inner* 的嵌套类能够访问其外部类的成员。内部类会带有一个对外部类的对象的引用：

```
class Outer {
    private val bar: Int = 1
    inner class Inner {
        fun foo() = bar
    }
}

val demo = Outer().Inner().foo() // == 1
```



#### 匿名内部类

```
window.addMouseListener(object : MouseAdapter() {

    override fun mouseClicked(e: MouseEvent) { …… }

    override fun mouseEntered(e: MouseEvent) { …… }
})
```

### 9.枚举类

每个枚举常量都是一个对象。枚举常量用逗号分隔。

```
enum class Direction {
    NORTH, SOUTH, WEST, EAST
}
```

**初始化**

```
enum class Color(val rgb: Int) {
        RED(0xFF0000),
        GREEN(0x00FF00),
        BLUE(0x0000FF)
}
```
**枚举类中实现接口**

一个枚举类可以实现接口（但不能从类继承），可以为所有条目提供统一的接口成员实现，也可以在相应匿名类中为每个条目提供各自的实现

```kotlin
enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t * u
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}
```



### 10.对象表达式与对象声明

**对象表达式**

```
window.addMouseListener(object : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) { /*……*/ }

    override fun mouseEntered(e: MouseEvent) { /*……*/ }
})
```

如果超类有构造，里面有参数

```
open class A(x: Int) {
    public open val y: Int = x
}

interface B { /*……*/ }

val ab: A = object : A(1), B {
    override val y = 15
}
```

如果我们只需要“一个对象而已”，并不需要特殊超类型。

```
fun foo() {
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }
    print(adHoc.x + adHoc.y)
}
```

匿名对象可以用作只在本地和私有作用域中声明的类型。

**对象声明**

使用object声明单例模式，

对象声明的**初始化过程是线程安全的**并且在首次访问时进行。

```
object DataProviderManager {
    fun registerDataProvider(provider: DataProvider) {
        // ……
    }

    val allDataProviders: Collection<DataProvider>
        get() = // ……
}
```

对象声明也可以有超类型。

**注意**：对象声明不能在**局部作用域**（即直接嵌套在函数内部），但是它们可以嵌套到**其他对象声明或非内部类**中

**伴生对象**

类内部的对象声明可以用 *companion* 关键字标记：

```
class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}
```

可通过只**使用类名作为限定符**来调用

```
var instance = MyClass.create()
```

也可以这样

```
var instance = MyClass.companion
```

即使伴生对象的成员看起来像其他语言的静态成员，在运行时他们仍然是**真实对象的实例成员**

**实现接口：**

```kotlin
interface Factory<T> {
    fun create(): T
}

class MyClass {
    companion object : Factory<MyClass> {
        override fun create(): MyClass = MyClass()
    }
}

val f: Factory<MyClass> = MyClass
```

当然，在 JVM 平台，如果使用 `@JvmStatic` 注解，你可以将伴生对象的成员生成为真正的静态方法和字段

### 11 类型别名typealias

类型别名为现有类型提供替代名称

```
typealias NodeSet = Set<Network.Node>

typealias FileTable<K> = MutableMap<K, MutableList<File>>


```

### 12 内联类 inline

### 13 委托

类似于Java的代理

```
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

class Derived(b: Base) : Base by b

fun main() {
    val b = BaseImpl(10)
    Derived(b).print()
}
```

`Derived` 的超类型列表中的 *by*-子句表示 `b` 将会在 `Derived` 中内部存储， 并且编译器将生成转发给 `b` 的所有 `Base` 的方法。

如果委托类复写了被代理类的方法，会调用复写的方法.

#### 1.委托属性

有一些常见的属性类型，虽然我们可以在每次需要的时候手动实现它们， 但是如果能够为大家把他们只实现一次并放入一个库会更好.

- 延迟属性（lazy properties）: 其值只在首次访问时计算；

- 可观察属性（observable properties）: 监听器会收到有关此属性变更的通知；

- 把多个属性储存在一个映射（map）中，而不是每个存在单独的字段中。

```kotlin
class Example {
    var p: String by Delegate()
}
```

属性委托需要提供set和get方法，Delegate就是一个对象，内部提供了p的getset方法。

#### 2 **延迟属性 Lazy**

[`lazy()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/lazy.html) 是接受一个 lambda 并返回一个 `Lazy <T>` 实例的函数，返回的实例可以作为实现延迟属性的委托

 第一次调用 `get()` 会执行已传递给 `lazy()` 的 lambda 表达式并记录结果， 后续调用 `get()` 只是返回记录的结果。

```
val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}

fun main() {
    println(lazyValue)
    println(lazyValue)
}
```

默认情况下，lazy是由同步锁的，某一刻只能一个线程在使用。

如果不必须同步锁，将`LazyThreadSafetyMode.PUBLICATION` 作为参数传递给 `lazy()` 函数

如果你确定初始化和使用该属性在同一个线程，可以使用 `LazyThreadSafetyMode.NONE` 模式，他不会有任何线程安全的保证。

#### 3.**可观察属性 Observable**

```kotlin
//Delegates.observable() 接受两个参数：初始值与修改时处理程序（handler）。 每当我们给属性赋值时会调用该处理程序（在赋值后执行）。它有三个参数：被赋值的属性、旧值与新值：

import kotlin.properties.Delegates

class User {
    var name: String by Delegates.observable("<no name>") {
        prop, old, new ->
        println("$old -> $new")
    }
}

fun main() {
    val user = User()
    user.name = "first"
    user.name = "second"
}
```

#### 4.委托给另一个属性???

#### 5.将属性储存在映射中

```kotlin
//一个常见的用例是在一个映射（map）里存储属性的值
class User(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}
```

```
val user = User(mapOf(
    "name" to "John Doe",
    "age"  to 25
))
```

```
println(user.name)
```

也适用于 *var* 属性，如果把只读的 `Map` 换成 `MutableMap`

#### 6. 局部委托属性

```
你可以将局部变量声明为委托属性
fun example(computeFoo: () -> Foo) {
    val memoizedFoo by lazy(computeFoo)

    if (someCondition && memoizedFoo.isValid()) {
        memoizedFoo.doSomething()
    }
}
```

####  7.属性委托要求

对于一个**只读**属性（即 *val* 声明的），委托必须提供一个操作符函数 `getValue()`，

- `thisRef` —— 必须与 *属性所有者* 类型（对于扩展属性——指被扩展的类型）相同或者是其超类型。
- `property` —— 必须是类型 `KProperty<*>` 或其超类型。

`getValue()` 必须返回与属性相同的类型（或其子类型）。

```kotlin
class Resource

class Owner {
    val valResource: Resource by ResourceDelegate()
}

class ResourceDelegate {
    operator fun getValue(thisRef: Owner, property: KProperty<*>): Resource {
        return Resource()
    }
}
```

对于一个**可变**属性（即 *var* 声明的），委托必须额外提供一个操作符函数 `setValue()`， 该函数具有以下参数：

- `thisRef` —— 必须与 *属性所有者* 类型（对于扩展属性——指被扩展的类型）相同或者是其超类型。

- `property` —— 必须是类型 `KProperty<*>` 或其超类型。

- `value` — 必须与属性类型相同（或者是其超类型）。

  ```kotlin
  class Resource
  
  class Owner {
      var varResource: Resource by ResourceDelegate()
  }
  
  class ResourceDelegate(private var resource: Resource = Resource()) {
      operator fun getValue(thisRef: Owner, property: KProperty<*>): Resource {
          return resource
      }
      operator fun setValue(thisRef: Owner, property: KProperty<*>, value: Any?) {
          if (value is Resource) {
              resource = value
          }
      }
  }
  ```

  

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

  	按谓词过滤 filter

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

  	Kotlin 标准库提供用于对集合元素进行分组的扩展函数。 基本函数 [`groupBy()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/group-by.html) 使用一个 lambda 函数并返回一个 **`Map`。**

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

# 4 协程

轻量级线程、
delay 挂起函数，不造成阻塞，会挂起协程，只能在协程里用
runBlocking 阻塞
join 等待执行完毕
suspend
repeat 

**runBlocking 与 coroutineScope** 可能看起来很类似，因为它们都会等待其协程体以及所有⼦协程结束。主要
区别在于，runBlocking ⽅法会阻塞当前线程来等待，⽽ coroutineScope 只是挂起，会释放底层线程⽤于其他
⽤途。

## 1.全局协程(GlobalScope)像守护线程

```kotlin
GlobalScope.launch {
    repeat(1000) { i ->
        println("I'm sleeping $i ...")
        delay(500L)
    }
}
delay(1300L) // 在延迟后退出
```

## 取消与超时

cancel()

join()

cancelAndJoin()



```kotlin
val job = launch {
    repeat(1000) { i ->
        println("job: I'm sleeping $i ...")
        delay(500L)
    }
}
delay(1300L) // 延迟一段时间
println("main: I'm tired of waiting!")
job.cancel() // 取消该作业
job.join() // 等待作业执行结束
```

协程的取消是 *协作* 的。一段协程代码必须协作才能被取消。 所有 `kotlinx.coroutines` 中的挂起函数都是 *可被取消的* 。它们检查协程的取消， 并在取消时抛出 [CancellationException](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-cancellation-exception/index.html)。 然而，如果协程正在执行计算任务，并且没有检查取消的话，那么它是不能被取消的



## 使计算代码可取消

第一种方法是定期调用挂起函数来检查取消。对于这种目的 [yield](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/yield.html)是一个好的选择。

 另一种方法是显式的检查取消状态。

将前一个示例中的 `while (i < 5)` 替换为 `while (isActive)` 并重新运行它

```kotlin
val startTime = System.currentTimeMillis()
val job = launch(Dispatchers.Default) {
    var nextPrintTime = startTime
    var i = 0
    while (isActive) { // 可以被取消的计算循环
        // 每秒打印消息两次
        if (System.currentTimeMillis() >= nextPrintTime) {
            println("job: I'm sleeping ${i++} ...")
            nextPrintTime += 500L
        }
    }
}
delay(1300L) // 等待一段时间
println("main: I'm tired of waiting!")
job.cancelAndJoin() // 取消该作业并等待它结束
println("main: Now I can quit.")
```

## 超时 timeout

```kotlin
withTimeout(1300L) {
    repeat(1000) { i ->
        println("I'm sleeping $i ...")
        delay(500L)
    }
}

```

## 组合挂起函数

### 顺序执行

```kotlin
val time = measureTimeMillis {
    val one = doSomethingUsefulOne()
    val two = doSomethingUsefulTwo()
    println("The answer is ${one + two}")
}
println("Completed in $time ms")
```

### 使用 async 并发

[async](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/async.html) 就类似于 [launch](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/launch.html)。它启动了一个单独的协程，这是一个轻量级的线程并与其它所有的协程一起并发的工作。不同之处在于 `launch` 返回一个 [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html) 并且不附带任何结果值，而 `async` 返回一个 [Deferred](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/index.html) —— 一个轻量级的非阻塞 future， 这代表了一个将会在稍后提供结果的 promise。你可以使用 `.await()` 在一个延期的值上得到它的最终结果， 但是 `Deferred` 也是一个 `Job`，所以如果需要的话，你可以取消它

```kotlin
val time = measureTimeMillis {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    println("The answer is ${one.await() + two.await()}")
}
println("Completed in $time ms")


```

### 惰性启动的 async

可选的，[async](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/async.html) 可以通过将 `start` 参数设置为 [CoroutineStart.LAZY](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-start/-l-a-z-y.html) 而变为惰性的。 在这个模式下，只有结果通过 [await](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-deferred/await.html) 获取的时候协程才会启动，或者在 `Job` 的 [start](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/start.html) 函数调用的时候

```kotlin
val time = measureTimeMillis {
    val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
    val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
    // 执行一些计算
    one.start() // 启动第一个
    two.start() // 启动第二个
    println("The answer is ${one.await() + two.await()}")
}
println("Completed in $time ms")
```

### 使用 async 的结构化并发

coroutineScope

```kotlin
suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}
```

如果在 `concurrentSum` 函数内部发生了错误，并且它抛出了一个异常， 所有在作用域中启动的协程都会被取消。

## 协程上下文与调度器

协程总是运行在一些以 [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/) 类型为代表的上下文中，它们被定义在了 Kotlin 的标准库里。

协程上下文是各种不同元素的集合。其中主元素是协程中的 [Job](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html)， 我们在前面的文档中见过它以及它的调度器，而本文将对它进行介绍



### 调度器与线程

协程上下文包含一个 *协程调度器* （参见 [CoroutineDispatcher](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html)）它确定了相关的协程在哪个线程或哪些线程上执行。协程调度器可以将协程限制在一个特定的线程执行，或将它分派到一个线程池，亦或是让它不受限地运行。

所有的协程构建器诸如 [launch](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/launch.html) 和 [async](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/async.html) 接收一个可选的 [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/) 参数，它可以被用来显式的为一个新协程或其它上下文元素指定一个调度器。

```kotlin

//当调用 launch { …… } 时不传参数，它从启动了它的 CoroutineScope 中承袭了上下文（以及调度器）。在这个案例中，它从 main 线程中的 runBlocking 主协程承袭了上下文。
launch { // 运行在父协程的上下文中，即 runBlocking 主协程
    println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
}
//Dispatchers.Unconfined 是一个特殊的调度器且似乎也运行在 main 线程中，
launch(Dispatchers.Unconfined) { // 不受限的——将工作在主线程中
    println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
}
//当协程在 GlobalScope 中启动时，使用的是由 Dispatchers.Default 代表的默认调度器。 默认调度器使用共享的后台线程池。 所以 launch(Dispatchers.Default) { …… } 与 GlobalScope.launch { …… } 使用相同的调度器。
launch(Dispatchers.Default) { // 将会获取默认调度器
    println("Default               : I'm working in thread ${Thread.currentThread().name}")
}
//newSingleThreadContext 为协程的运行启动了一个线程。 一个专用的线程是一种非常昂贵的资源。 在真实的应用程序中两者都必须被释放，当不再需要的时候，使用 close 函数，或存储在一个顶层变量中使它在整个应用程序中被重用。
launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
    println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
}
```

### 非受限调度器 vs 受限调度器

[Dispatchers.Unconfined](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-unconfined.html) 协程调度器在调用它的**线程启动了一个协程**，但它仅仅只是运行到第一个挂起点。挂起后，它恢复线程中的协程，而这完全由被调用的挂起函数来决定。

非受限的调度器非常适用于执行不消耗 CPU 时间的任务，以及不更新局限于特定线程的任何共享数据（如UI）的协程。



## 异步流

### 返回多个值

在 Kotlin 中可以使用[集合](https://kotlinlang.org/docs/reference/collections-overview.html)来表示多个值

```
fun simple(): List<Int> = listOf(1, 2, 3)
 
fun main() {
    simple().forEach { value -> println(value) } 
}
```

#### 序列

	如果使用一些消耗 CPU 资源的阻塞代码计算数字（每次计算需要 100 毫秒）那么我们可以使用 [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/index.html) 来表示数字：

#### 挂起函数

计算过程阻塞运行该代码的主线程。 当这些值由异步代码计算时，我们可以使用 `suspend` 修饰符标记函数 `simple`， 这样它就可以在不阻塞的情况下执行其工作并将结果作为列表返回：

```
suspend fun simple(): List<Int> {
    delay(1000) // 假装我们在这里做了一些异步的事情
    return listOf(1, 2, 3)
}

fun main() = runBlocking<Unit> {
    simple().forEach { value -> println(value) } 
}
```
