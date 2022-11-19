## 开闭原则（Open Closed Principle）

由勃兰特·梅耶（Bertrand Meyer）提出，他在 1988 年的著作《面向对象软件构造》（Object Oriented Software Construction）中提出：软件实体应当对扩展开放，对修改关闭（Software entities should be open for extension，but closed for modification），这就是开闭原则的经典定义。

### 需求变化是唯一不变的真理

当一个需求变化导致程序中多个依赖模块都发生了级联的改动，那么这个程序就展现出了我们所说的 "坏设计（bad design）" 的特质。应用程序也相应地变得脆弱、僵化、无法预期和无法重用。开闭原则（Open Closed Principle）即为解决这些问题而产生，它强调的是你设计的模块应该从不改变。当需求变化时，你可以通过添加新的代码来扩展这个模块的行为，而不去更改那些已经存在的可以工作的代码。

### 开闭原则的主要特性

1. 它们 "面向扩展开放（Open For Extension）"。
   
   也就是说模块的行为是能够被扩展的。当应用程序的需求变化时，我们可以使模块表现出全新的或与以往不同的行为，以满足新的需求。

2. 它们 "面向修改封闭（Closed For Modification）"。

    模块的源代码是不能被侵犯的，任何人都不允许修改已有源代码。

### 实现方法

可以通过“抽象约束、封装变化”来实现开闭原则，即通过接口或者抽象类为软件实体定义一个相对稳定的抽象层，而将相同的可变因素封装在相同的具体实现类中。

因为抽象灵活性好，适应性广，只要抽象的合理，可以基本保持软件架构的稳定。而软件中易变的细节可以从抽象派生来的实现类来进行扩展，当软件需要发生变化时，只需要根据需求重新派生一个实现类来扩展就可以了。

### 示例：Shape 抽象

考虑下面这个例子。我们要在页面上绘制圆形（Circle）和方形（Square）。

```
public enum ShapeType {
    circle, square,rectangle
}

public class BadShape {
    public void draw(ShapeType type) {
        switch (type) {
            case circle:
                log.info("BadShape --》 {}", "画circle");
                break;
            case square:
                log.info("BadShape --》 {}", "画square");
                break;
            case triangle:
                log.info("BadShape --》 {}", "画triangle");
                break;
            default:
                log.info("BadShape --》 {}", "画rectangle");

        }
    }
}

调用方法
new BadShape().draw(ShapeType.circle);
```

这里方法 draw 不符合开闭原则，因为它无法保证对新的 Shape 种类保持封闭。如果我们想要扩展这个方法，使其能够支持一个三角形（Triangle），则我们将不得不修改这个方法。事实上，每当我们需要绘制新的图形种类时，我们都不得不修改这个方法。

当然这个程序仅仅是一个例子。在实践中 draw 方法中的 switch 语句将会随着需求的变化不断地增加新的图形。
而更有可能的则是switch 语句中的 case 子句会和一些逻辑运算绑定到了一起。这将导致每一次的修改都需要测试每个case的逻辑是否正确。

下面这段代码展示了符合开闭原则的 Cicle/Square 问题的一个解决方案。

```
public interface Shape {
    void draw();
}

public class Cicle implements Shape {
    @Override
    public void draw() {
        log.info("better --》 {}", "画Cicle");
    }
}

public class Square implements Shape {
    @Override
    public void draw() {
        log.info("better --》 {}", "画Square");
    }
}

public class Rectangle implements Shape {
    @Override
    public void draw() {
        log.info("better --》 {}", "画Rectangle");
    }
}

调用方法
List<Shape> shapes = Lists.newArrayList();
shapes.add(new Cicle());
shapes.add(new Square());
shapes.add(new Rectangle());
drawShape(shapes);
        
public static void drawShape(List<Shape> shapes) {
   shapes.forEach(shape -> {
      shape.draw();
   });
}    
```

在这个例子中，我们创建了一个 Shape 抽象接口，这个接口包含一个 draw 方法。而 Circle 和 Square 都衍生自 Shape 类。

注意在这里如果我们想扩展 draw 方法的行为来绘制一个新的图形种类，我们所需要做的就是增加一个从 Shape 类衍生的子类。而 draw 方法则无需进行修改。因此 draw 方法符合了开放封闭原则，它的行为可以不通过对其修改而扩展。

在比较现实的情况中，Shape 类可能包含很多个方法。但是在应用程序中增加一个新的图形仍然是非常简单的，因为所需要做的仅是创建一个衍生类来实现这些函数方法。同时，我们也不再需要在应用程序内对所有图形做测试。

因为更改符合开放封闭原则的程序是通过增加新的代码，而不是修改已存在的代码，之前描述的那种级联式的更改也就不存在了。

### 开放和闭合的选择

要明白程序是不可能 100% 完全封闭的。例如，试想上面的 Shape 示例，如果我们现在要增加新的需求，Circle和Square必须以特定的顺序进行绘制，将会发生什么呢？通常来说，无论模块的设计有多封闭，总是有各种各样的变化会打破这种封闭。

因此，完全闭合是不现实的，所以必须讲究策略。也就是说，**程序员必须甄别其设计对哪些变化封闭。** 这需要一些基于经验的预测。有经验的程序员会很好的了解用户和所在的行业，以判断各种变化的可能性。然后可以确定对最有可能的变化保持开放封闭原则。

### 闭合是基于抽象的

java 对排序有很好的支持，已经帮我们对排序进行了某种程度的抽象，使用排序接口Comparable和比较器Comparator都可以。

我们以排序接口Comparable为例

```
public interface Shape extends Comparable<Shape> {
    void draw();

    int getSort();
}

public class Cicle implements Shape {

    int sort;

    public Cicle(int sort) {
        this.sort = sort;
    }

    @Override
    public int getSort() {
        return sort;
    }

    @Override
    public int compareTo(Shape o) {
        //1表示第二个对象比前一个大，0表示其相等，-1表示比前一个小
        return o.getSort() - sort;
    }

    @Override
    public void draw() {
        log.info("better --》 {}", "画Cicle");
    }
}

调用方法
List<Shape> shapes = Lists.newArrayList();
shapes.add(new Cicle(3));
shapes.add(new Square(1));
shapes.add(new Rectangle(2));
drawShape(shapes);

public static void drawShape(List<Shape> shapes) {
   Collections.sort(shapes);
   shapes.forEach(shape -> {
      shape.draw();
   });
}  
```

### 进一步的扩展闭合

 **需求变化是唯一不变的真理** 

### 总结

关于开闭原则（Open Closed Principle）还有很多可以讲的。在很多方面这个原则都是面向对象设计的核心。始终遵循该原则才能从面向对象技术中持续地获得最大的益处，例如：可重用性和可维护性。同时，对该原则的遵循也不是通过使用一种面向对象的编程语言就能够达成的。更确切的说，**它需要程序员更专注于将抽象技术应用到程序中那些趋于变化的部分上。**
