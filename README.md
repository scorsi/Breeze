# Breeze - Code with freshness without limits

Inspired by Kotlin and JavaScript.

### Functional Object-oriented

Control your data as it coming with lot of functional thinking and data control flow.

### Typed with type inferences

Typed for better control of your code.

### Prototype-based

Create or alter a prototype will result of a creation of a new type.

Change your data on the road. Everything is customizable, perfect for high possibility.

### No exceptions

Don't break your code with exceptions or goto/label. Write your code as it should be executed.

### Light and understandable syntax

Read your code from up to down and easily understand it.

## Take a look at Breeze

```breeze
# Variable declaration
a = 0

# Function without arguments declaration
b = () {
  0
}

# Function with arguments declaration
c = (d) {
  a + b() + d
}

# Proto declaration
todoProto = {
  todoString = "todo"
  todo = () { self.todoString }
}

# Proto assignement
test = todoProto.new()

# Function call
system.print(test.todo()) # Display "todo"

# Function declaration to proto
test.todo2 = () { "todo2" }

# Piped function call
test.todo2() |> system.print() # Display "todo2"

# Anonyme function declaration
(s) {
  system.print(s)
} ("tada") # Display "tada"

getFunc = () {
  (a, b) { a + b }
}

func = getFunc()
func(1, 1) |> system.print() # Display "2"
```

## The idea behind Breeze

The idea behind Breeze was to create something new and also learning how programming language works and how to do one. Because in the future I would like to create my own language.

I wanted to have a really light syntaxe with high possibility.

## Why using Breeze ?



Breeze is an experimental language, not ready to use in production.
