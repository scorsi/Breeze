class MyClass {
  String a = "str";
  int b = 42;

  public MyClass() {
  }

  public String c() {
    return a + Integer.toString(b);
  }
}

class Main {
  public static void main(String[] args) {
    MyClass a = new MyClass();
    System.out.println(a.c());
  }
}
