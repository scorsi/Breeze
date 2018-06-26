# Breeze - Code with freshness without limits

Work in progress. I'm again refractoring everything.

Breeze:
```breeze
MyClass = {
  a = "str"
  b = 42
  c = () {
    a + b
  }
}
a = MyClass()
system.print(a.c())
```
Javascript (NodeJS):
```javascript
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
```
Python (3):
```python
class MyClass(object):
    def __init__(self):
        self.a = "str"
        self.b = 42

    def c(self):
        return self.a + str(self.b)


a = MyClass()
print(a.c())
```
Ruby:
```ruby
class MyClass
  attr_reader :a, :b
  def initialize()
    @a = "str"
    @b = 42
  end
  def c()
    a + b.to_s
  end
end

a = MyClass.new()
puts(a.c)
```
C
```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "breeze/intlib.h"

struct MyClass {
  char* a;
  int b;
  char* c();
}

char* MyClass_c(struct MyClass* class) {
  size_t len = strlen(class->a) + breeze_intlen(class->b);
  char* buf = (char*) malloc(len);
  sprintf(buf, "%s%d", class->a, class->b);
  return buf;
}

struct MyClass* MyClass_new() {
  struct MyClass* class = (struct MyClass*) malloc(sizeof(struct MyClass));
  class->c = &MyClass_c;
  return class;
}

int main(int argc, char const *argv[]) {
  struct MyClass* a = MyClass_new();
  char* a_c = a->c(a);
  printf("%s\n", a_c);
  free(a_c);
  free(a);
  return 0;
}
```
