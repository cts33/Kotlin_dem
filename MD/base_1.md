# 1.基础语法

## 1.函数
函数传参  
返回值：Unit代表午饭后，可以忽略
```kotlin
fun sum(a: Int, b: Int): Int {
    return a + b
}
```

## 1.变量
定义只读局部变量使用关键字 val 定义。只能为其赋值一次
可重新赋值的变量使用 var 关键字：
顶层变量：val PI = 3.14
## 1.字符串模板
```kotlin
var a = 1
// 模板中的简单名称：
val s1 = "a is $a" 

a = 2
// 模板中的任意表达式：
val s2 = "${s1.replace("is", "was")}, but now is $a"
```

## 1.空值与 null 检测

当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空

## 1.类型检测与自动类型转换
is 运算符检测一个表达式是否某类型的一个实例
```kotlin
if (obj is String) {
        // `obj` 在该条件分支内自动转换成 `String`
        return obj.length
}
```
## 1.for while when
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


## 1.使用区间（range） in

```kotlin

if (x in 1..y+1) {
    println("fits in range")
}


```
# 2.基本类型

## 1数字
Byte Short Int Long
如果初始值超过了其最大值，那么推断为 Long 类型
如需显式指定 Long 型值，请在该值后追加 L 后缀

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
xor(bits) – 位异或
inv() – 位非
## 4.字符

字符用 Char 类型表示
## 5.数组

数组在 Kotlin 中使用 Array 类来表示，它定义了 get 与 set 函数（按照运算符重载约定这会转变为 []）以及 
size 属性,Kotlin 中数组是不型变的（invariant）。这意味着 Kotlin 不让我们把 Array<String> 赋值给 Array<Any>
val array = arrayOf(1, 2, 3)
val array = arrayOfNulls()

## 6.原生类型数组
Kotlin 也有无装箱开销的专门的类来表示原生类型数组: ByteArray、 ShortArray、IntArray 等等。这些类与 Array 并没有继承关系
// 大小为 5、值为 [0, 0, 0, 0, 0] 的整型数组
val arr = IntArray(5)

## 7.字符串
字符串用 String 类型表示。字符串是不可变的。 字符串的元素——字符可以使用索引运算符访问: s[i]

for (c in str) {
println(c)
}

