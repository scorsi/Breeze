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
  char* buf = malloc(len);
  sprintf(buf, "%s%d", class->a, class->b);
  return buf;
}

struct MyClass* MyClass_new() {
  struct MyClass* class = malloc(sizeof(struct MyClass));
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
