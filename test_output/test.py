class MyClass(object):
    def __init__(self):
        self.a = "str"
        self.b = 42

    def c(self):
        return self.a + str(self.b)


a = MyClass()
print(a.c())
