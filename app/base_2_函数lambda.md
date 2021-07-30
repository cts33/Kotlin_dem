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



## 2. 高阶函数和lambda表达式



