# 常见问题
## 1.！！ 不做非空校验。如果运行时发现变量为空，就扔出异常
## 2.java kotlin交互的语法

java code
TestMain.class

kotlin code
TestMain::class.java

//传入一个class对象
kotlin: testClass（JavaMain::class.java）
## 3.没有封装类 静态变量
返回值 尽量可空类型

## 4.扩展函数--给某些提供了增加函数的功能，静态解析

var file = File("/mp4");
println(file.readText())





# 1.基础

## 1.1 基础语法

### 1.函数
函数传参  
返回值：Unit代表午饭后，可以忽略

```kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}
```

### 2.变量
定义只读局部变量使用关键字 val 定义。只能为其赋值一次
可重新赋值的变量使用 var 关键字：
顶层变量：val PI = 3.14
### 3.字符串模板
```kotlin
var a = 1
// 模板中的简单名称：
val s1 = "a is $a" 

a = 2
// 模板中的任意表达式：
val s2 = "${s1.replace("is", "was")}, but now is $a"
```

### 4.空值与 null 检测

当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空

### 5.类型检测与自动类型转换
is 运算符检测一个表达式是否某类型的一个实例
```kotlin
if (obj is String) {
        // `obj` 在该条件分支内自动转换成 `String`
        return obj.length
}
```
### 6.for while when
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


### 7.使用区间（range） in

```kotlin

if (x in 1..y+1) {
    println("fits in range")
}


```
## 1.2.基本类型

### 1.数字
Byte Short Int Long
如果初始值超过了其最大值，那么推断为 Long 类型
如需显式指定 Long 型值，请在该值后追加 L 后缀

Float	32
Double	64

### 2.字面常量
十进制: 123
Long 类型用大写 L 标记: 123L
十六进制: 0x0F
二进制: 0b00001011
### 3.位运算
shl(bits) – 有符号左移
shr(bits) – 有符号右移
ushr(bits) – 无符号右移
and(bits) – 位与
or(bits) – 位或
xor(bits) – 位异或
inv() – 位非
### 4.字符

字符用 Char 类型表示
### 5.数组

数组在 Kotlin 中使用 Array 类来表示，它定义了 get 与 set 函数（按照运算符重载约定这会转变为 []）以及 
size 属性,Kotlin 中数组是不型变的（invariant）。这意味着 Kotlin 不让我们把 Array<String> 赋值给 Array<Any>

```
val array = arrayOf(1, 2, 3)
val array = arrayOfNulls()
```

### 6.原生类型数组
Kotlin 也有无装箱开销的专门的类来表示原生类型数组: ByteArray、 ShortArray、IntArray 等等。这些类与 Array 并没有继承关系

```
// 大小为 5、值为 [0, 0, 0, 0, 0] 的整型数组
val arr = IntArray(5)
```


### 7.字符串
字符串用 String 类型表示。字符串是不可变的。 字符串的元素——字符可以使用索引运算符访问: s[i]

```
for (c in str) {
	println(c)
}
```



## 1.3 类与继承
Kotlin 中使用关键字 class 声明类

```kotlin
class Person constructor(firstName: String) { /*……*/ }
```

在 Kotlin 中的一个类可以有一个主构造函数以及一个或多个次构造函数
如果**主构造函数没有任何注解或者可见性修饰符**，可以省略这个 constructor 关键字。

```kotlin
class Person(firstName: String) { /*……*/ }
```


### 1.次构造函数
类也可以声明前缀有 constructor的次构造函数：
如果类**有一个主构造函数，每个次构造函数需要委托给主构造函数**， 可以直接委托或者通过
别的次构造函数间接委托。委托到同一个类的另一个构造函数用 this 关键字即可：

```kotlin
class Person(val name: String) {
    var children: MutableList<Person> = mutableListOf()
    //此处this不带明白，可能就是委托主构造
    constructor(name: String, parent: Person) : this(name) {
        parent.children.add(this)
    }
}
```
### 2 继承
在 Kotlin 中所有类都有一个**共同的超类 Any**，这对于没有超类型声明的类是默认超类

**Any 有三个方法：equals()、 hashCode() 与 toString()**
默认情况下，Kotlin 类是最终（final）的：它们不能被继承。 要使一个类可继承，请用 **open** 关键字标记它

open class Base // 该类开放继承
```kotlin
open class Base(p: Int)

class Derived(p: Int) : Base(p)
```
### 3.覆盖方法  覆盖属性

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
### 4.覆盖规则
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
## 1.4.抽象类
类以及其中的某些成员可以声明为 **abstract**。 抽象成员在本类中**可以不用实现**。 需要注意的是，我们并**不需要用 open 标注一个抽象类或者函数**
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

```
var allByDefault: Int? // 错误：需要显式初始化器，隐含默认 getter 和 setter
var initialized = 1 // 类型 Int、默认 getter 和 setter

val simple: Int? // 类型 Int、默认 getter、必须在构造函数中初始化
val inferredType = 1 // 类型 Int 、默认 getter
```

val 因为不可变，所以没有set方法，必须在构造函数初始化

var 必须显式初始化

**自定义get方法set方法**

```
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

```
if (foo::bar.isInitialized) {
    println(foo.bar)
}
```



# 2.接口
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

### 6.密封类

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

  
