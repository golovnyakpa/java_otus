package com.github.golovnyakpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoxTest {

    @Test
    void constructorsTest() {
        List<Apple> apples = new ArrayList<>(Arrays.asList(
                new Apple(1.0), new Apple(2.0), new Apple(0.5))
        );
        Box<Apple> appleBox = new Box<>(apples);
        Assertions.assertEquals(3, appleBox.fruitsNum());
        Box<Fruit> fb = new Box<>(apples);
        Assertions.assertEquals(3, fb.fruitsNum());

        List<Fruit> fruits = new ArrayList<>(Arrays.asList(
                new Apple(1.1), new Orange(0.3))
        );
        Box<Fruit> fruitBox = new Box<>(fruits);
        Assertions.assertEquals(2, fruitBox.fruitsNum());

        Box<Fruit> fruitBox1 = new Box<>(new Apple(1.0), new Orange(2.3));
        Assertions.assertEquals(2, fruitBox1.fruitsNum());

        Assertions.assertEquals(0, new Box<Orange>().fruitsNum());
    }

    @Test
    void addFruitTest() {
        Box<Apple> appleBox = new Box<>();
        appleBox.addFruit(new Apple(0.1));
        Assertions.assertEquals(1, appleBox.fruitsNum());

        Box<Fruit> fruitBox = new Box<>();
        fruitBox.addFruit(new Apple(0.1));
        fruitBox.addFruit(new Orange(0.2));
        fruitBox.addFruit(new Fruit(0.3));

        Assertions.assertEquals(3, fruitBox.fruitsNum());
    }

    @Test
    void weightTest() {
        List<Apple> apples = new ArrayList<>(Arrays.asList(
                new Apple(1.0), new Apple(2.0), new Apple(0.5))
        );
        Box<Apple> appleBox = new Box<>(apples);
        Assertions.assertEquals(3.5, appleBox.weight(), Utils.doubleOpsPrecision);
        List<Fruit> fruits = new ArrayList<>(Arrays.asList(
                new Apple(1.1), new Orange(0.3))
        );
        Box<Fruit> fb = new Box<>(fruits);
        Assertions.assertEquals(1.4, fb.weight(), Utils.doubleOpsPrecision);
        Assertions.assertEquals(
                1.6, fb.addFruit(new Orange(0.2)).weight(), Utils.doubleOpsPrecision
        );
    }

    @Test
    void compareTest() {
        List<Apple> apples = new ArrayList<>(Arrays.asList(
                new Apple(1.0), new Apple(2.0), new Apple(0.5))
        );
        List<Fruit> fruits = new ArrayList<>(Arrays.asList(
                new Apple(1.1), new Orange(0.3))
        );
        Box<Apple> appleBox = new Box<>(apples);
        Box<Fruit> fruitBox = new Box<>(fruits);
        Assertions.assertFalse(appleBox.compare(fruitBox));

        List<Orange> oranges = new ArrayList<>(Arrays.asList(
                new Orange(2.0), new Orange(0.5))
        );
        Box<Orange> orangeBox = new Box<>(oranges);
        Assertions.assertFalse(orangeBox.compare(fruitBox));
        Assertions.assertTrue(orangeBox.addFruit(new Orange(1.0)).compare(appleBox));
    }

    @Test
    void moveContentToOtherBox() {
        List<Apple> apples = new ArrayList<>(Arrays.asList(
                new Apple(1.0), new Apple(2.0), new Apple(0.5))
        );
        Box<Apple> appleBoxSource = new Box<>(apples);
        List<Apple> apples1 = new ArrayList<>(List.of(new Apple(1.1)));
        Box<Apple> appleBoxDest = new Box<>(apples1);
        appleBoxSource.moveContentToOtherBox(appleBoxDest);
        Assertions.assertTrue(appleBoxSource.fruitsNum() == 0 &&
                appleBoxDest.fruitsNum() == 4
        );
        Assertions.assertEquals(4.6, appleBoxDest.weight(), Utils.doubleOpsPrecision);

        Box<Fruit> fruitBoxDest = new Box<>(new Fruit(0.4));
        appleBoxDest.moveContentToOtherBox(fruitBoxDest);
        Assertions.assertTrue(appleBoxDest.fruitsNum() == 0 &&
                fruitBoxDest.fruitsNum() == 5
        );
        Assertions.assertEquals(5.0, fruitBoxDest.weight(), Utils.doubleOpsPrecision);
    }

}
