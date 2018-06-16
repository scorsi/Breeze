# Yaul - Code with freshness

Code with freshness.

```yaul
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

## The idea behind Yaul

The idea behind Yaul was to create something new and also learning how programming language works and how to do one. Because in the future I would like to create my own language.

I wanted to have a really light syntaxe with high possibility.

## Why using Yaul ?

You probably don't want to use Yaul.

Yaul is an experimental language, not ready to use in production.
