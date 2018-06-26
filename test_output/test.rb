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
