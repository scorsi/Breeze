# Kick

The name may change in the future.

It is not ready for production uses. In hard development.

## The idea behind Kick

The idea behind Kick was to create something new and also learning how programming language works and how to do one. Because in the future I would like to create my own language.

I took inspiration of [Clojure](https://clojure.org/guides/learn/syntax), when I see this language I say to myself "Hum, I don't understand what is written right there". So I decided to do the same thing just for fun.

## Why using Kick ?

You probably don't want to use Kick.

Kick is an experimental language absolutly not verbose and also absolutly not self-explained. 

I will compare Kick with C. You will see that Kick is all the time lightweight and faster to write than C.


### Variable declaration
```
!(x:int){0}
# OR
!(x : int) { 0 }
```
```c
int x = 0;
```

### Function declaration
```
!(add:int:(a:int,b:int)){a+b}
# OR
!(add : int : (a : int, b : int)) {
  a + b
}
```
```c
int add(int a, int b) {
  return a + b;
}
```

### If/ElseIf/Else statement
```
?(a=0){0}|?(a=1){1}|_{2}
# OR
?(a = 0) { 0 }
|?(a = 1) { 1 }
|_ { 2 }
# OR
?(a = 0) {
  0
} | ?(a = 1) {
  1
} | _ {
  2
}
```
```c
if (a == 0) {
  return 0;
} else if (a == 1) {
  return 1;
} else {
  return 2;
}
```

### Variable declaration with returned value of a if statement
```
!(x:int){?(a=0){0}|_{1}}
# OR
!(x : int) {
  ?(a = 0) { 0 }
  |_ { 1 } 
}
```
```c
int x;
if (a == 0) {
  x = 0;
} else {
  x = 1;
}
```

### Piped functions
```
a()|>b()|>c()|>d()
# OR
a()
|> b()
|> c()
|> d()
```
```c
d(c(b(a())));
```
