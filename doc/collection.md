<style>
r { color: #CD5050 }
o { color: #FF9614 }
g { color: #2C952C }
b { color: #1478FF }
</style>

## Java Collection Framework

#### java.util.Collection 인터페이스

- Collection Framework 에서 가장 기본이 되는 인터페이스이다.
- 해당 인터페이스는 <r>_순서를 기억하지 않고_</r>, <b>_중복을 허용하여_</b> 자료를 다루는 목적으로 만들어졌다.

#### java.util.List 인터페이스

- <r>_순서가 중요한 자료를 다룰때_</r> 사용하는 인터페이스이다.
- Collection을 상속받음으로써 Collection이 가지고 있는 add(), size(), iterator() method 를 사용할 수 있다.
- 해당 인터페이스는 순서를 알고 있다고 가정하기 때문에 <b>_특정 순서로 저장된 자료를 꺼낼 수 있는 get(int) method 를 가지고 있다_</b>.
- 중복된 자료를 허용한다.

#### java.util.Set 인터페이스

- <r>_중복을 허용하지 않는 자료를 다룰 때_</r> 사용하는 인터페이스이다.
- 중복을 허용하지 않는 다는 것은 같은 값을 저장할 수 없다는 것이다.
- <r>_같은 값을 여러번 추가하여도 마지막 값 하나만 저장됨_</r>을 의미한다.
- Set 인터페이스에 저장되는 갭체들은 Object가 가지고 있는 <b>_equal() method 와 hashCode() method 를 Overriding_</b> 해야한다.
- <r>_Q)_</r> 자료를 add() 할때마다 equal(), hashCode() method 를 이용하여 중복 여부를 체크 한다는 의미겠지???? 그렇다면 성능이....

#### java.util.Iterator 인터페이스

- java.util.Iterator 인터페이스는 자료구조에서 <b>_자료를 꺼내기 위한 목적으로 사용되는 인터페이스_</b>이다.
- Iterator 패턴을 구현하고 있다.

---

## 예외 (Exception) 처리하기

### 자바에서는 실행시 2가지 형태의 오류가 발생할 수 있다.

- Error: 수습할 수 없는 심각한 오류
- Exception(예외): 예외 처리를 통해 수습할 수 있는 덜 심각한 오류
- 메모리 부족, Stack overflow 등이 발생하여 프로그램이 죽는 것은 프로그래머가 제어할 수 없다.

## IO란?

- Input & Output
- 입출력
- 입력은 키보다, 네트웤, 파일 등으로부터 받을 수 있다.
- 출력은 화면, 네트웤, 파일 등에 할 수 있다.

## File 클래스

- java.io.File 클래스는 파일의 크기, 파일의 접근 권한, 파일의 삭제, 파일 이름 변경 등의 작업을 할 수 있는 기능을 제공하여 준다.
- 주의해야 할 것은 디렉토리(폴더) 역시 파일로써 취급된다.
- 파일 인스턴스를 만들었다고, 실제 폴더에 팡리이 생선되는건 아니다.

## Stream

#### InputStream, OutputStream

- 추상클래스
- byte 단위 입출력 클래스는 모두 InputStream, OutputStream 의 후손이다.

#### Reader, Writer

- 추상클래스
- char 단위 입출력 클래스는 모두 Reader, Writer 의 후손이다.

### 패턴

- Decorator 패턴
- Composition 패턴

## DI (Dependency Injection)

- 부품을 조립해준다.
- class 를 조립해준다.
- Setter Injection
- Construction Injection
- Spring 이 알아서 Injection 을 해준다.

## IoC (Inversion of Control) 컨테이너

-
