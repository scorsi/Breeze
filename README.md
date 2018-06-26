# Breeze - Code with freshness without limits

Work in progress. I'm again refractoring everything.

Breeze:
```breeze
MyClass = {
  titi = "titi"
}
a = MyClass.new()
system.print(a.titi)
a.toto = "toto"
system.print(a.toto)
a.tata = () {
  "tata"
}
```
Javascript:
```javascript
class MyClass {
  constructor() {
    this.titi = "titi"
  }
}
a = new MyClass()
console.log(a.titi)
a.toto = "toto"
console.log(a.toto)
a.tata = function() {
  return "tata"
}
console.log(a.tata())
```
Python:
```python
class MyClass(object):
    def __init__(self):
        self.titi = "titi"


def tata():
    return "tata"


a = MyClass()
print(a.titi)
a.toto = "toto"
print(a.toto)
a.tata = tata
print(a.tata())
```
