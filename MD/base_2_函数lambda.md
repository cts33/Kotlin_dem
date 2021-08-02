# 函数

## 1.函数声明用法

```kotlin
fun double(x: Int): Int {
    return 2 * x
}

val result = double(2)
Stream().read() // 创建类 Stream 实例并调用 read()
```

## 2. 参数

默认参数

```kotlin
fun read(
    b: Array<Byte>, 
    off: Int = 0, 
    len: Int = b.size,
) 
```

当覆盖父类的方法，不要给方法添加默认值。

### 具名参数

就是传入参数的时候，写清楚具体哪个参数

```kotlin
fun reformat(
    str: String,
    normalizeCase: Boolean = true,
    upperCaseFirstLetter: Boolean = true,
    divideByCamelHumps: Boolean = false,
    wordSeparator: Char = ' ',
) {
/*……*/
}
//可以这样
reformat(
    'String!',
    false,
    upperCaseFirstLetter = false,
    divideByCamelHumps = true,
    '_'
)

reformat('This is a long String!')
//可以跳跃调用
reformat('This is a short String!', upperCaseFirstLetter = false, wordSeparator = '_')
```

### 可变数量的参数（Varargs）

```kotlin
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) // ts is an Array
        result.add(t)
    return result
}

val list = asList(1, 2, 3)
```

### 尾递归函数

Kotlin 支持一种称为[尾递归](https://zh.wikipedia.org/wiki/尾调用)的函数式编程风格。 这允许一些通常用循环写的算法改用递归函数来写，而无堆栈溢出的风险。 当一个函数用 `tailrec` 修饰符标记并满足所需的形式时，编译器会优化该递归，留下一个快速而高效的基于循环的版本



```kotlin
val eps = 1E-10 // "good enough", could be 10^-15

tailrec fun findFixPoint(x: Double = 1.0): Double
        = if (Math.abs(x - Math.cos(x)) < eps) x else findFixPoint(Math.cos(x))
```



## 3. 高阶函数和lambda表达式



Kotlin 函数都是[*头等的*](https://zh.wikipedia.org/wiki/头等函数)，这意味着它们可以存储在变量与数据结构中、作为参数传递给其他[高阶函数](https://www.kotlincn.net/docs/reference/lambdas.html#高阶函数)以及从其他高阶函数返回。可以像操作任何其他非函数值一样操作函数。



**高阶函数**???

高阶函数是将函数用作参数或返回值的函数

**函数类型**

**函数类型实例化**

**函数实例调用**

函数类型的值可以通过其 [`invoke(……)` 操作符](https://www.kotlincn.net/docs/reference/operator-overloading.html#invoke)调用：`f.invoke(x)` 或者直接 `f(x)`



**Lambda**表达式与函数

`max(strings, { a, b -> a.length < b.length })`

max是高阶函数，第二个参数是函数。

`fun compare(a: String, b: String): Boolean = a.length < b.length`

### Lambda 表达式语法

```kotlin
// sum 方法名
//参数
//返回类型
//方法体逻辑
val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
```

### 从 lambda 表达式中返回一个值

### 匿名函数

```
fun(x: Int, y: Int): Int = x + y
```

### **闭包**

### 带有接收者的函数字面值

## 4.内联函数 inline

使用[高阶函数](https://www.kotlincn.net/docs/reference/lambdas.html)会带来一些运行时的效率损失：每一个函数都是一个对象，并且会捕获一个闭包。 即那些在函数体内会访问到的变量。 内存分配（对于函数对象和类）和虚拟调用会引入运行时间开销。

`inline` 修饰符影响函数本身和传给它的 lambda 表达式：所有这些都将内联到调用处。

