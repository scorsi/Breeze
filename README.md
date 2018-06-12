# Yaul - Yet Another Useless Language

The name may change in the future.

It is not ready for production uses. In hard development.

## The idea behind Yaul

The idea behind Yaul was to create something new and also learning how programming language works and how to do one. Because in the future I would like to create my own language.

I took inspiration of [Clojure](https://clojure.org/guides/learn/syntax), when I see this language I tell myself "Hum, I don't understand what is written right there". So I decided to do the same thing just for fun.

## Why using Yaul ?

You probably don't want to use Yaul.

Yaul is an experimental language absolutly not verbose and also absolutly not self-explained. 

## What does Yaul look like ?

I will compare Yaul with C for better explaination. You will see that Yaul is often lightweight and faster to write than C.

### Variable declaration

We declare variable with the `!` character.

The first is the name of the variable and the second is the type.

```yaul
!(x:int){0}
# OR
!(x : int) { 0 }
```
```c
int x = 0;
```

What does a complexe Yaul variable declaration give in C ?

```yaul
!(a : int) { 0 }
!(test : int : ()) {
  !(x : int) {
    ?(a = 0) { 0 }
    | { 1 } 
  }
}
```
```c
int a = 0;

int test_decl_x() {
  if (a == 0) { return 0; }
  else { return 1; }
}

int test() {
  int x = test_decl_x();
  return x;
}
```

### Function declaration

For function declaration we have 3 parts in the declaration.

The first is the name of the function, the second is the type and the third is the arguments.

```yaul
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

### Function return

By default, the last statement of the execution of a function is the returned value. You can force return by using the '<-' symbol.

```yaul
!(x : int : (a : bool, b : int)) {
  ?(a = true) { <- 0 }
  b * 2
```
```c
int x(bool a, int b) {
  if (a == true) {
    return 0;
  }
  return b * 2;
}
```

### Casting

We cast with the '<:' character.

```yaul
!(c : char) { (i <: char) }
```
```c
char c = (char) i;
```

### If/ElseIf/Else statement

The if statement is declared with the `?` character. Follow by the `|` character for the else.

```yaul
?(a=0){0}|?(a=1){1}|{2}
# OR
?(a = 0) { 0 }
| ?(a = 1) { 1 }
| { 2 }
# OR
?(a = 0) {
  0
} | ?(a = 1) {
  1
} | {
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

### Match/Switch statement

```yaul
!(a : int) { 0 }
~(a)
| (0) { 0 }
| (1) { 1 }
```
```c
int a = 0;
switch (a) {
  case 0: return 0;
  case 1: return 1;
}
```

### Piped functions

If you don't know what is a piped function, the principle is to give the result of the left argument to the right argument.

```yaul
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
