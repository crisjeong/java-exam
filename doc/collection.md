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
- 해당 인터페이스는 순서를 알고 있다고 가정하기 때문에 <b>_특정 순서로 저장된 자룔를 꺼낼 수 있는 get(int) method 를 가지고 있다_</b>.
- 중복된 자료를 허용한다.

#### java.util.Set 인터페이스

- <r>_중복을 허용하지 않는 자료를 다룰 때_</r> 사용하는 인터페이스이다.
- 중복을 허용하지 않는 다는 것은 같은 값을 저장할 수 없다는 것이다.
- <r>_같은 값을 여러번 추가하여도 마지막 값 하나만 저장됨_</r>을 의미한다.
- Set 인터페이스에 저장되는 갭체들은 Object가 가지고 있는 <b>_equal() method 와 hashCode() method 를 Overriding_</b> 해야한다.
- <r>_Q)_</r> 자료를 add() 할때마다 equal(), hashCode() method 를 이용하여 중복 여부를 체크 한다는 의미겠지???? 그렇다면 성능이....

#### java.util.Iterator 인터페이스
