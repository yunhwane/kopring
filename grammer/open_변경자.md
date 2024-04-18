## java
- java 에서는 final 이 붙지 않으면, 모든 다른 클래스에서 상속이 가능함
- 하지만 kotlin에 경우 클래스와 메서드는 final 임

따라서 해당 클래스의 상속을 허용하고 싶거나, 메서드의 오버라이드를 허용하고 싶을 경우 open 변경자를 붙여야한다.


## kotlin
```kotlin
pen class Car {

    // 이 메서드는 하위 클래스에서 override 불가능
    fun getNumberOfTires(): Int {
        return 4
    }
    
    // 하위 클래스에서 override 가능
    open fun hasSunRoof() :Boolean {
        return false
    }
}

// open 클래스는 상속이 가능하다!
class Benz() : Car() {

    // getNumberOfTires 메서드는 override 불가능
    // hasSunRoof 메서드는 open변경자가 붙어서 override 가능
    override fun hasSunRoof(): Boolean {
        return true
    }
}
```

```kotlin

abstract class Animal {

    // 추상 메서드는 반드시 override 해야 함
    abstract fun bark()

    // 이 메서드는 하위 클래스에서 선택적으로 override 할 수 있다. (하거나 안하거나 자유)
    open fun running() {
        println("animal running!")
    }
}

class Dog() : Animal() {

    override fun bark() {
        println("멍멍")
    }

    // 이 메서드는 override 하거나 하지 않거나 자유.
    override fun running() {
        println("dog's running!")
    }
}
```
