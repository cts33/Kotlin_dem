# 1.类与继承
## 1.1 类
Kotlin 中使用关键字 class 声明类

```kotlin
class Person constructor(firstName: String) { /*……*/ }
```

在 Kotlin 中的一个类可以有一个主构造函数以及一个或多个次构造函数
如果主构造函数没有任何注解或者可见性修饰符，可以省略这个 constructor 关键字。
class Person(firstName: String) { /*……*/ }
## 1.2次构造函数
类也可以声明前缀有 constructor的次构造函数：
如果类有一个主构造函数，每个次构造函数需要委托给主构造函数， 可以直接委托或者通过
别的次构造函数间接委托。委托到同一个类的另一个构造函数用 this 关键字即可：
```kotlin
class Person(val name: String) {
    var children: MutableList<Person> = mutableListOf()
    constructor(name: String, parent: Person) : this(name) {
        parent.children.add(this)
    }
}
```
## 1.2 继承
在 Kotlin 中所有类都有一个共同的超类 Any，这对于没有超类型声明的类是默认超类

Any 有三个方法：equals()、 hashCode() 与 toString()
默认情况下，Kotlin 类是最终（final）的：它们不能被继承。 要使一个类可继承，请用 open 关键字标记它

open class Base // 该类开放继承
```kotlin
open class Base(p: Int)

class Derived(p: Int) : Base(p)
```
## 1.4覆盖方法  覆盖属性

Circle.draw() 函数上必须加上 override 修饰符
你也可以用一个 var 属性覆盖一个 val 属性
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
## 1.覆盖规则
如果一个类从它的直接超类继承相同成员的多个实现， 它必须覆盖这个成员并提供其自己的实现（也许用继承来的其中之一）
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
## 1.抽象类
类以及其中的某些成员可以声明为 abstract。 抽象成员在本类中可以不用实现。 需要注意的是，我们并不需要用 open 标注一个抽象类或者函数
```kotlin
open class Polygon {
    open fun draw() {}
}

abstract class Rectangle : Polygon() {
    abstract override fun draw()
}
```
## 1.属性
Kotlin 类中的属性既可以用关键字 var 声明为可变的，也可以用关键字 val 声明为只读的。
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
## 1.Getters 与 Setters ？？？？？？？？

## 1.接口
Kotlin 的接口可以既包含抽象方法的声明也包含实现
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
## 1 函数式（SAM）接口
只有一个抽象方法的接口称为函数式接口或 SAM（单一抽象方法）接口。函数式接口可以有多个非抽象成员，但只能有一个抽象成员。
```kotlin
fun interface KRunnable {
   fun invoke()
}
```
## 1.可见性修饰符
private、它只会在声明它的文件内可见；
protected、 不适用于顶层声明。子类可见
internal 它会在相同模块内随处可见
public






