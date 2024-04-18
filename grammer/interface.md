
```kotlin
interface MyInterface {
    fun bar();
    fun foo() {
       // optional body 
    }

}
```

- 코틀린은 인터페이스에 추상 메서드 선언, 메서드 구현이 가능함
- 메서드 구현을 하지 않는 이상, 상속 받는 구현체에 구현이 강제됨

```kotlin
class Child : MyInterface {
    override fun bar() {

    }
}
```

### 인터페이스 속성

```kotlin
interface MyInterface {
    val prop: Int // abstract

    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(prop)
    }
}

class Child : MyInterface {
    override val prop: Int = 29
}
```

1. prop : 추상 프로퍼티는 인터페이스 구현체에서 오버라이드 하여 값을 제공해야함
2. 구현된 프로퍼티 (propertyWithImplementation): get() 함수가 "foo"를 반환하도록 설정되어 있으며, 이 인터페이스를 구현하는 모든 클래스에서 이 프로퍼티를 추가로 구현할 필요가 없음, 해당 프로퍼티를 호출하면 "foo"라는 문자열을 반환


### 인터페이스 상속
```kotlin
interface Named {
    val name: String
}

interface Person : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

data class Employee(
    // implementing 'name' is not required
    override val firstName: String,
    override val lastName: String,
    val position: Position
) : Person

```
1. 추상 프로퍼티 name
2. Person 인터페이스에 Named 인터페이스 확장, 추가 프로퍼티 정의
3. Employee 클래스에서 프로퍼티를 오버라이드 하여 추가적인 position 프로퍼티

* Employee 에서 추가적으로 name 에 대한 구현이 필요가 없어짐,

### 사용 예
```kotlin

val employee = Employee("John", "Doe", Position.Manager)
println(employee.name)  // "John Doe"

```


