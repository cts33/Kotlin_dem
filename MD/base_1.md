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

使用 Array 类来表示，它定义了 get 与 set 函数（按照运算符重载约定这会转变为 []）以及 
size 属性,

数组是**不型变的**（invariant）。这意味着 Kotlin 不让我们把 Array<String> 赋值给 Array<Any>

```
val array = arrayOf(1, 2, 3)
val array = arrayOfNulls()
```

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

class Person public @Inject constructor(name:String){...}
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
初始化块中的代码实际上会成为主构造函数的一部分

```
class Constructors {
	//优先执行，再执行次构造函数
    init {
        println("Init block")
    }
	//再执行
    constructor(i: Int) {
        println("Constructor")
    }

}
```

在 JVM 上，如果主构造函数的**所有的参数都有默认值**，编译器会生成 一个额外的**无参构造函数**

## 2. init 初始化代码

主构造函数不能包含任何的代码。初始化的代码可以放到以 *init* 关键字作为前缀的**初始化块（initializer blocks）**中。可以有多个init模块代码,多个init模块会按照先后顺序执行。

```
class User(name:String){

	init{
		println("First initializer block that prints ${name}")
	}
	
	init{
		println("second initializer block that prints ${name}")
	}
}
```

## 3.声明属性和初始化属性 var val

类似于不仅为主函数构造，且给当前类添加了属性。可以用var val 修饰

```
class Person(val firstName: String, val lastName: String, var age: Int) { /*……*/ }
```



## 4 继承

在 Kotlin 中所有类都有一个**共同的超类 Any**

**Any 有三个方法：equals()、 hashCode() 与 toString()**
默认情况下，Kotlin 类是最终（final）的：它们不能被继承。 要使一个类可继承，请用 **open** 关键字标记它

open class Base // 该类开放继承
```kotlin
open class Base(p: Int)

class Derived(p: Int) : Base(p)
```
### 1.覆盖方法  覆盖属性

Circle.draw() 函数上必须加上 **override 修饰**符
你也可以用**一个 var 属性覆盖一个 val 属性**，但反之则不行。 这是允许的，因为一个 `val` 属性本质上声明了一个 `get` 方法， 而将其覆盖为 `var` 只是在子类中额外声明一个 `set` 方法。

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
你可以在主构造函数中使用 *override* 关键字作为属性声明的一部分。

```
interface Shape {
    val vertexCount: Int
}
//声明了属性，且覆盖了之前的属性
class Rectangle(override val vertexCount: Int = 4) : Shape // 总是有 4 个顶点
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
### 3.派生类初始化执行顺序

在构造派生类的新实例的过程中，先完成其基类的初始化 ，因此发生在派生类的初始化逻辑运行之前

```kotlin
open class Base(val name: String) {

    init { println("Initializing Base") }

    open val size: Int = 
        name.length.also { println("Initializing size in Base: $it") }
}

class Derived(
    name: String,
    val lastName: String,
) : Base(name.capitalize().also { println("Argument for Base: $it") }) {

    init { println("Initializing Derived") }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

fun main() {
    println("Constructing Derived(\"hello\", \"world\")")
    val d = Derived("hello", "world")
}
```

### 4.super 调用其超类的函数与属性访问器

```
open class Rectangle {
    open fun draw() { println("Drawing a rectangle") }
    val borderColor: String get() = "black"
}

class FilledRectangle : Rectangle() {
    override fun draw() {
        super.draw()
        println("Filling the rectangle")
    }

    val fillColor: String get() = super.borderColor
}
```

在一个内部类中访问外部类的超类，可以通过由外部类名限定的 *super* 关键字来实现：`super@Outer`：



## 5.抽象类

类以及其中的某些成员可以声明为 **abstract**。 抽象成员在本类中**可以不用实现**。 需要注意的是，我们并**不需要用 open 标注一个抽象类或者函数**。我们可以用一个抽象成员覆盖一个非抽象的开放成员
```kotlin
open class Polygon {
    open fun draw() {}
}
abstract class Rectangle : Polygon() {
    abstract override fun draw()
}
```
## 6.属性

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
### 1.Getters 与 Setters

属性声明涉及三个点：**初始器（initializer）、getter 和 setter** 。属性默认情况下都有set和get方法。

```kotlin
var allByDefault: Int? // 错误：需要显式初始化器，隐含默认 getter 和 setter
var initialized = 1 // 类型 Int、默认 getter 和 setter

val simple: Int? // 类型 Int、默认 getter、必须在构造函数中初始化
val inferredType = 1 // 类型 Int 、默认 getter
```

val 因为不可变，所以**没有set方法**，必须在构造函数初始化

var 必须显式初始化

### 2. **自定义get方法set方法**

```kotlin
var stringRepresentation: String
    get() = this.toString() //get方法
    set(value) {			//set方法
        setDataFromString(value) // 解析字符串并赋值给其他属性
    }
```
### 3.延迟初始化属性与变量   lateinit

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
检测一个 lateinit var **是否已初始化**

```kotlin
if (foo::bar.isInitialized) {
    println(foo.bar)
}
```

### 4 幕后字段和幕后属性？？？



### 5 编译期常量 *const* 

如果只读属性的值在编译期是已知的，那么可以使用 *const* 修饰符将其标记为*编译期常量*



### 6. 委托属性？？？



# 4.接口

Kotlin 的接口可以既包含**抽象方法的声明也包含实现**。接口无法保存状态。它可以有属性但必须声明为抽象或提供访问器实现。
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
在接口中声明的属性**要么是抽象的，要么提供访问器**的实现。在接口中声明的属性不能有幕后字段（backing field），因此接口中声明的访问器不能引用它们。

## 1.解决覆盖冲突

```kotlin
interface A {
    fun foo() { print("A") }
    fun bar()
}

interface B {
    fun foo() { print("B") }
    fun bar() { print("bar") }
}

class C : A {
    override fun bar() { print("bar") }
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }

    override fun bar() {
        super<B>.bar()
    }
}
//接口 A 和 B 都定义了方法 foo() 和 bar()。 两者都实现了 foo(), 但是只有 B 实现了 bar() (bar() 在 A 中没有标记为抽象， 因为在接口中没有方法体时默认为抽象）。因为 C 是一个实现了 A 的具体类，所以必须要重写 bar() 并实现这个抽象方法。

//然而，如果我们从 A 和 B 派生 D，我们需要实现我们从多个接口继承的所有方法，并指明 D 应该如何实现它们。这一规则既适用于继承单个实现（bar()）的方法也适用于继承多个实现（foo()）的方法。
```



## 2 函数式（SAM）接口

**只有一个抽象方法的接口称为函数式接口**或 SAM（单一抽象方法）接口。函数式接口可以有多个非抽象成员，但只能有一个抽象成员。

```kotlin
//可以用 fun 修饰符在 Kotlin 中声明一个函数式接口。
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

 

## 3.可见性修饰符

private、它只会在声明**它的文件内可**见；
protected、 不适用于顶层声明。子类可见
internal 它会在**相同模块内随处可见**
public 随处可见,默认可见



## 4.扩展函数

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

### 1.**伴生对象的扩展**????



## 5.数据类

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

#### 1. copy 浅拷贝

在很多情况下，我们需要复制一个对象改变它的一些属性，但其余部分保持不变.

```
val jack = User(name = "Jack", age = 1)
val olderJack = jack.copy(age = 2)

```

#### 2. 数据类与解构声明

```
val jane = User("Jane", 35)
val (name, age) = jane
println("$name, $age years of age") // 输出 "Jane, 35 years of age
```

## 6.密封类？？？？

密封类用来表示受限的类继承结构：当一个值为有限几种的类型、而不能有任何其他类型时。

在某种意义上，他们是**枚举类的扩展**：**枚举类型的值集合也是受限的**，但每个**枚举常量只存在一个实例**，而密封类的一个子类可以有可包**含状态的多个实例**。

## 7.泛型

```kotlin
class Box<T>(t: T) {
    var value = t
}
```

**型变**

声明处型变（declaration-site variance）与类型投影（type projections）

### 1.声明处型变out in

在 Kotlin 中，有一种方法向编译器解释这种情况。这称为**声明处型变**：我们可以标注 `Source` 的**类型参数** `T` 来确保它仅从 `Source<T>` 成员中**返回**（生产），并从不被消费。

```kotlin
interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // 这个没问题，因为 T 是一个 out-参数
    // ……
}
//简而言之，他们说类 C 是在参数 T 上是协变的，或者说 T 是一个协变的类型参数。 你可以认为 C 是 T 的生产者，而不是 T 的消费者
```

**out**修饰符称为**型变注解**，并且由于它在类型参数声明处提供，所以我们称之为**声明处型变**。 这与 Java 的**使用处型变**相反，其类型用途通配符使得类型协变。

除了 **out**，Kotlin 又补充了一个型变注释：**in**。它使得一个类型参数**逆变**：只可以被消费而不可以被生产。逆变类型的一个很好的例子是 `Comparable`：

```kotlin
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 拥有类型 Double，它是 Number 的子类型
    // 因此，我们可以将 x 赋给类型为 Comparable <Double> 的变量
    val y: Comparable<Double> = x // OK！
}
```

### 2.类型投影

```
fun copy(from: Array<out Any>, to: Array<Any>) { …… }
```

这里out 称为**类型投影**：我们说`from`不仅仅是一个数组，而是一个受限制的（**投影的**）数组：我们只可以调用返回类型为类型参数 `T` 的方法，如上，这意味着我们只能调用 `get()`。这就是我们的**使用处型变**的用法，并且是对应于 Java 的 `Array<? extends Object>`、 但使用更简单些的方式。

也可以使用 **in** 投影一个类型：

```
fun fill(dest: Array<in String>, value: String) { …… }
```

`Array<in String>` 对应于 Java 的 `Array<? super String>`，也就是说，你可以传递一个 `CharSequence` 数组或一个 `Object` 数组给 `fill()` 函数。

### 3.星投影 ？？？？

Kotlin 为此提供了所谓的**星投影**语法：

- 对于 `Foo <out T : TUpper>`，其中 `T` 是一个具有上界 `TUpper` 的协变类型参数，`Foo <*>` 等价于 `Foo <out TUpper>`。 这意味着当 `T` 未知时，你可以安全地从 `Foo <*>` *读取* `TUpper` 的值。
- 对于 `Foo <in T>`，其中 `T` 是一个逆变类型参数，`Foo <*>` 等价于 `Foo <in Nothing>`。 这意味着当 `T` 未知时，没有什么可以以安全的方式*写入* `Foo <*>`。
- 对于 `Foo <T : TUpper>`，其中 `T` 是一个具有上界 `TUpper` 的不型变类型参数，`Foo<*>` 对于读取值时等价于 `Foo<out TUpper>` 而对于写值时等价于 `Foo<in Nothing>`。

如果泛型类型具有多个类型参数，则每个类型参数都可以单独投影。 例如，如果类型被声明为 `interface Function <in T, out U>`，我们可以想象以下星投影：

- `Function<*, String>` 表示 `Function<in Nothing, String>`；
- `Function<Int, *>` 表示 `Function<Int, out Any?>`；
- `Function<*, *>` 表示 `Function<in Nothing, out Any?>`。

*注意*：星投影非常像 Java 的原始类型，但是安全。

### 4、泛型约束

 **上界**

最常见的约束类型是与 Java 的 *extends* 关键字对应的 **上界**：

```kotlin
fun <T : Comparable<T>> sort(list: List<T>) {  …… }
```

 冒号之后指定的类型是**上界**：只有 `Comparable<T>` 的子类型可以替代 `T`。 例如：

```kotlin
sort(listOf(1, 2, 3)) // OK。Int 是 Comparable<Int> 的子类型
sort(listOf(HashMap<Int, String>())) // 错误：HashMap<Int, String> 不是 Comparable<HashMap<Int, String>> 的子类型
```

默认的上界（如果没有声明）是 `Any?`。在尖括号中只能指定一个上界。 如果同一类型参数需要多个上界，我们需要一个单独的 **where**-子句：

```kotlin
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
    where T : CharSequence,
          T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}
```

所传递的类型必须同时满足 `where` 子句的所有条件。在上述示例中，类型 `T` 必须*既*实现了 `CharSequence` *也*实现了 `Comparable`。

### 5.类型擦除

Kotlin 为泛型声明用法执行的类型安全检测仅在编译期进行。 运行时泛型类型的实例不保留关于其类型实参的任何信息。 其类型信息称为被*擦除*。	

## 8 嵌套类与内部类

### 1.嵌套类

You can also use interfaces with nesting. All combinations of classes and interfaces are possible: You can nest interfaces in classes, classes in interfaces, and interfaces in interfaces.

你可以在类中嵌套接口，在接口中嵌套类，在接口里嵌套接口。

```kotlin
class Outer {
    private val bar: Int = 1
    class Nested {
        fun foo() = 2
    }
}

val demo = Outer.Nested().foo() // == 2

interface OuterInterface {
    class InnerClass
    interface InnerInterface
}

class OuterClass {
    class InnerClass
    interface InnerInterface
}
```



### 2.内部类

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



### 3.匿名内部类

```
window.addMouseListener(object : MouseAdapter() {

    override fun mouseClicked(e: MouseEvent) { …… }

    override fun mouseEntered(e: MouseEvent) { …… }
})
```

## 9.枚举类

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



## 10.对象表达式与对象声明

### 1 **对象表达式**

```kotlin
window.addMouseListener(object : MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) { /*……*/ }

    override fun mouseEntered(e: MouseEvent) { /*……*/ }
})
```

如果超类有构造，里面有参数

```kotlin
open class A(x: Int) {
    public open val y: Int = x
}

interface B { /*……*/ }

val ab: A = object : A(1), B {
    override val y = 15
}
```

如果我们只需要“一个对象而已”，并不需要特殊超类型。

```kotlin
fun foo() {
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }
    print(adHoc.x + adHoc.y)
}
```

匿名对象可以用作只在**本地和私有作用域**中声明的类型。

```
class C {
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    // 公有函数，所以其返回类型是 Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x        // 没问题
        val x2 = publicFoo().x  // 错误：未能解析的引用“x”
    }
}
//如果你使用匿名对象作为公有函数的返回类型或者用作公有属性的类型，那么该函数或属性的实际类型会是匿名对象声明的超类型，如果你没有声明任何超类型，就会是 Any。在匿名对象中添加的成员将无法访问。
```





### 2 **对象声明**

使用object声明单例模式，

对象声明的**初始化过程是线程安全的**并且在首次访问时进行。

```kotlin
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

### **3 .伴生对象????**

类内部的对象声明可以用 *companion* 关键字标记：

```kotlin
class MyClass {
    companion object Factory {
        fun create(): MyClass = MyClass()
    }
}
```

可通过只**使用类名作为限定符**来调用

```kotlin
var instance = MyClass.create()
```

也可以这样

```kotlin
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

## 11 类型别名 typealias

类型别名为现有类型提供替代名称

```kotlin
typealias NodeSet = Set<Network.Node>

typealias FileTable<K> = MutableMap<K, MutableList<File>>
```

**???? 待研究**

```kotlin
typealias Predicate<T> = (T) -> Boolean

fun foo(p: Predicate<Int>) = p(42)

fun main() {
    val f: (Int) -> Boolean = { it > 0 }
    println(foo(f)) // 输出 "true"

    val p: Predicate<Int> = { it > 0 }
    println(listOf(1, -2).filter(p)) // 输出 "[1]"
}
```



## 12 内联类 inline



为了解决这类问题，Kotlin 引入了一种被称为 `内联类` 的特殊类，它通过在类的前面定义一个 `inline` 修饰符来声明：

```
inline class Password(val value: String)
```

内联类必须含有**唯一的一个属性在主构造函数中初始化**。在运行时，将使用这个唯一属性来表示内联类的实例（关于运行时的内部表达请参阅[下文](https://www.kotlincn.net/docs/reference/inline-classes.html#表示方式)）：

```
// 不存在 'Password' 类的真实实例对象
// 在运行时，'securePassword' 仅仅包含 'String'
val securePassword = Password("Don't try this in production")
```

### 1.成员

内联类可以声明属性与函数

```kotlin
inline class Name(val s: String) {
    val length: Int
        get() = s.length

    fun greet() {
        println("Hello, $s")
    }
}    

fun main() {
    val name = Name("Kotlin")
    name.greet() // `greet` 方法会作为一个静态方法被调用
    println(name.length) // 属性的 get 方法会作为一个静态方法被调用
}
```

- 内联类不能含有 *init* 代码块
- 内联类不能含有[幕后字段](https://www.kotlincn.net/docs/reference/properties.html#幕后字段)

### 2. 内联继承接口

```
interface Printable {
    fun prettyPrint(): String
}

inline class Name(val s: String) : Printable {
    override fun prettyPrint(): String = "Let's $s!"
}    

fun main() {
    val name = Name("Kotlin")
    println(name.prettyPrint()) // 仍然会作为一个静态方法被调用
}
```

### 3.表示方式--装箱拆箱

在生成的代码中，Kotlin 编译器为每个内联类保留一个包装器。内联类的实例可以在运行时表示为包装器或者基础类型。这就类似于 `Int` 可以[表示](https://www.kotlincn.net/docs/reference/basic-types.html#表示方式)为原生类型 `int` 或者包装器 `Integer`。

为了生成性能最优的代码，Kotlin 编译更倾向于使用基础类型而不是包装器。 然而，有时候使用包装器是必要的。一般来说，只要将内联类用作另一种类型，它们就会被装箱。

```
interface I

inline class Foo(val i: Int) : I

fun asInline(f: Foo) {}
fun <T> asGeneric(x: T) {}
fun asInterface(i: I) {}
fun asNullable(i: Foo?) {}

fun <T> id(x: T): T = x

fun main() {
    val f = Foo(42) 
    
    asInline(f)    // 拆箱操作: 用作 Foo 本身
    asGeneric(f)   // 装箱操作: 用作泛型类型 T
    asInterface(f) // 装箱操作: 用作类型 I
    asNullable(f)  // 装箱操作: 用作不同于 Foo 的可空类型 Foo?
    
    // 在下面这里例子中，'f' 首先会被装箱（当它作为参数传递给 'id' 函数时）然后又被拆箱（当它从'id'函数中被返回时）
    // 最后， 'c' 中就包含了被拆箱后的内部表达(也就是 '42')， 和 'f' 一样
    val c = id(f)  
}
```

因为内联类既可以表示为基础类型有可以表示为包装器，[引用相等](https://www.kotlincn.net/docs/reference/equality.html#引用相等)对于内联类而言毫无意义，因此这也是被禁止的。

### 4.名字修饰

由于内联类被编译为其基础类型，因此可能会导致各种模糊的错误，例如意想不到的平台签名冲突：

```
inline class UInt(val x: Int)

// 在 JVM 平台上被表示为'public final void compute(int x)'
fun compute(x: Int) { }

// 同理，在 JVM 平台上也被表示为'public final void compute(int x)'！
fun compute(x: UInt) { }
```

为了缓解这种问题，一般会通过在函数名后面拼接一些稳定的哈希码来重命名函数。 因此，`fun compute(x: UInt)` 将会被表示为 `public final void compute-<hashcode>(int x)`，以此来解决冲突的问题。

> 请注意在 Java 中 `-` 是一个 *无效的* 符号，也就是说在 Java 中不能调用使用内联类作为形参的函数。

### 5.内联类与类型别名

内联类似乎与[类型别名](https://www.kotlincn.net/docs/reference/type-aliases.html)非常相似。实际上，两者似乎都引入了一种新的类型，并且都在运行时表示为基础类型。

然而，关键的区别在于**类型别名与其基础类型**(以及具有相同基础类型的其他类型别名)是 *赋值兼容* 的，而内联类却不是这样。

**内联类引入了一个真实的新类型**，与类型别名正好相反，**类型别名**仅仅是为现有的类型取了个新的替代名称(别名)：

## 13 委托

类似于Java的代理

```kotlin
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

### 1.委托属性

有一些常见的属性类型，虽然我们可以在每次需要的时候手动实现它们， 但是如果能够为大家把他们只实现一次并放入一个库会更好.

- 延迟属性（lazy properties）: 其值只在首次访问时计算；

- 可观察属性（observable properties）: 监听器会收到有关此属性变更的通知；

- 把多个属性储存在一个映射（map）中，而不是每个存在单独的字段中。

```kotlin
class Example {
    var p: String by Delegate()
}
```

属性委托需要提供set和get方法，Delegate就是一个对象，内部提供了p的get-set方法。

### 2 **延迟属性 Lazy**

[`lazy()`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/lazy.html) 是接受一个 lambda 并返回一个 `Lazy <T>` 实例的函数，返回的实例可以作为实现延迟属性的委托

 第一次调用 `get()` 会执行已传递给 `lazy()` 的 lambda 表达式并记录结果， 后续调用 `get()` 只是返回记录的结果。

```kotlin
val lazyValue: String by lazy {
    println("computed!")
    "Hello"
}

fun main() {
    println(lazyValue)
    println(lazyValue)
}
```

默认情况下，**lazy是由同步锁的，某一刻只能一个线程在使用**。

如果不必须同步锁，将`LazyThreadSafetyMode.PUBLICATION` 作为参数传递给 `lazy()` 函数

如果你确定初始化和使用该属性在同一个线程，可以使用 `LazyThreadSafetyMode.NONE` 模式，他不会有任何线程安全的保证。

### 3.**可观察属性 Observable**

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

### 4.委托给另一个属性

从 Kotlin 1.4 开始，一个属性可以把它的 getter 与 setter 委托给另一个属性

- 顶层属性
- 同一个类的成员或扩展属性
- 另一个类的成员或扩展属性

为将一个属性委托给另一个属性，应在委托名称中使用恰当的 `::` 限定符

```kotlin
var topLevelInt: Int = 0

class ClassWithDelegate(val anotherClassInt: Int)
class MyClass(var memberInt: Int, val anotherClassInstance: ClassWithDelegate) {
    var delegatedToMember: Int by this::memberInt
    var delegatedToTopLevel: Int by ::topLevelInt
    
    val delegatedToAnotherClass: Int by anotherClassInstance::anotherClassInt
}
var MyClass.extDelegated: Int by ::topLevelInt
```

这是很有用的，例如，当想要以一种向后兼容的方式重命名一个属性的时候：引入一个新的属性、 使用 `@Deprecated` 注解来注解旧的属性、并委托其实现。

```kotlin
class MyClass {
   var newName: Int = 0
   @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
   var oldName: Int by this::newName
}

fun main() {
   val myClass = MyClass()
   // 通知：'oldName: Int' is deprecated.
   // Use 'newName' instead
   myClass.oldName = 42
   println(myClass.newName) // 42
}
```

### 5.将属性储存在映射中

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

### 6. 局部委托属性

```kotlin
//你可以将局部变量声明为委托属性
fun example(computeFoo: () -> Foo) {
    val memoizedFoo by lazy(computeFoo)

    if (someCondition && memoizedFoo.isValid()) {
        memoizedFoo.doSomething()
    }
}
//memoizedFoo 变量只会在第一次访问时计算。 如果 someCondition 失败，那么该变量根本不会计算
```

###  7.属性委托要求

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




### 8.翻译规则？？？？

### 9.提供委托？？？？



# 5.函数

## 1.可变数量的参数（Varargs）

函数的参数（通常是最后一个）可以用 `vararg` 修饰符标记：

```
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
    return result
}

val list = asList(1, 2, 3)
```



## 2. 中缀表示法 infix 

标有 *infix* 关键字的函数也可以使用中缀表示法（忽略该调用的点与圆括号）调用。中缀函数必须满足以下要求：

- 它们必须是成员函数或[扩展函数](https://www.kotlincn.net/docs/reference/extensions.html)；
- 它们必须只有一个参数；
- 其参数不得[接受可变数量的参数](https://www.kotlincn.net/docs/reference/functions.html#可变数量的参数varargs)且不能有[默认值](https://www.kotlincn.net/docs/reference/functions.html#默认参数)。

```kotlin
infix fun Int.shl(x: Int): Int { …… }

// 用中缀表示法调用该函数
1 shl 2

// 等同于这样
1.shl(2)
```

## 3.函数作用域

### a.局部函数

```kotlin
//Kotlin 支持局部函数，即一个函数在另一个函数内部：
fun dfs(graph: Graph) {
    fun dfs(current: Vertex, visited: MutableSet<Vertex>) {
        if (!visited.add(current)) return
        for (v in current.neighbors)
            dfs(v, visited)
    }

    dfs(graph.vertices[0], HashSet())
}
//局部函数可以访问外部函数（即闭包）的局部变量
fun dfs(graph: Graph) {
    val visited = HashSet<Vertex>()
    fun dfs(current: Vertex) {
        if (!visited.add(current)) return
        for (v in current.neighbors)
            dfs(v)
    }

    dfs(graph.vertices[0])
}


```



### b.尾递归函数

Kotlin 支持一种称为[尾递归](https://zh.wikipedia.org/wiki/尾调用)的函数式编程风格。一些通常用循环写的算法改用递归函数来写，而无堆栈溢出的风险

当一个函数用 `tailrec` 修饰符标记并满足所需的形式时，编译器会优化该递归，留下一个快速而高效的基于循环的版本



```kotlin
val eps = 1E-10 // "good enough", could be 10^-15

tailrec fun findFixPoint(x: Double = 1.0): Double
        = if (Math.abs(x - Math.cos(x)) < eps) x else findFixPoint(Math.cos(x))
//要符合 tailrec 修饰符的条件的话，函数必须将其自身调用作为它执行的最后一个操作。
//在递归调用后有更多代码时，不能使用尾递归，并且不能用在 try/catch/finally 块中。目前在 Kotlin for JVM 与 Kotlin/Native 中支持尾递归。
```

## 4.高阶函数？？？？？

Kotlin 函数都是[*头等的*](https://zh.wikipedia.org/wiki/头等函数)，这意味着它们可以存储在变量与数据结构中、作为参数传递给其他[高阶函数](https://www.kotlincn.net/docs/reference/lambdas.html#高阶函数)以及从其他高阶函数返回。

高阶函数是将函数用作参数或返回值的函数。





# 5.集合

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
