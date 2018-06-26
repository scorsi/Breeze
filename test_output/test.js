class MyClass {
  constructor() {
    this.a = "str"
    this.b = 42
  }
  c() {
    return this.a + this.b
  }
}
a = new MyClass()
console.log(a.c())
