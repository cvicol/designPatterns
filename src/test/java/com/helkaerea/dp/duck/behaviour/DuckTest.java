package com.helkaerea.dp.duck.behaviour;


import com.helkaerea.dp.duck.Decoy;
import com.helkaerea.dp.duck.Duck;
import com.helkaerea.dp.duck.MallardDuck;
import org.junit.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class DuckTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Duck aDuck = new Duck() {
        @Override
        public void display() {
           System.out.println("I look like a test class");
        }
    };
    private static Duck aMallardDuck = new MallardDuck();
    private static Duck aDecoy;

    @BeforeClass
    public static void beforeClass() {
        FlyBehavior flyWithWings = new FlyWithWings();
        QuackBehavior quackBehavior = new Quack();
        aMallardDuck.setFlyBehaviour(flyWithWings);
        aMallardDuck.setQuackBehaviour(quackBehavior);
    }

    @BeforeClass
    public static void beforeClassDoDecoy() {
        aDecoy = new Decoy();
        FlyBehavior noFly = new FlyNoWay();
        QuackBehavior squeak = new Squeak();
        aDecoy.setFlyBehaviour(noFly);
        aDecoy.setQuackBehaviour(squeak);
    }
    @Before
    public void beforeAny() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void aDuckHasAFlyBehaviour() throws Exception {
        FlyBehavior flyWithWings = new FlyWithWings();
        aDuck.setFlyBehaviour(flyWithWings);
        assertThat(aDuck.getFlyBehaviour(), is(flyWithWings));
    }

    @Test
    public void aDuckHasAQuackBehaviour() throws Exception {
        QuackBehavior quackBehavior = new MuteQuack();
        aDuck.setQuackBehaviour(quackBehavior);
        assertThat(aDuck.getQuackBehaviour(), is(quackBehavior));
    }

    @Test
    public void anMallardDuckFlyesWithWings() throws Exception {
        aMallardDuck.fly();
        assertEquals("I'm flying with wings!!", outContent.toString().replaceAll("\\n", ""));
    }

    @Test
    public void anMallardDuckQuacks() throws Exception {
        aMallardDuck.makeSound();
        assertEquals("Quack", outContent.toString().replaceAll("\\n", ""));
    }

    @Test
    public void aMallardDuckDisplaysAsANormalDuck() throws Exception {
        aMallardDuck.display();
        assertEquals("I'm a normal Mallard Duck", outContent.toString().replaceAll("\\n", ""));
    }

    @Test
    public void anDecoyDuckFlyiesNoWay() throws Exception {
        aDecoy.fly();
        assertEquals("I can't fly", outContent.toString().replaceAll("\\n", ""));
    }

    @Test
    public void anDecoyDuckSqueaks() throws Exception {
        aDecoy.makeSound();
        assertEquals("Squeak", outContent.toString().replaceAll("\\n", ""));
    }

    @Test
    public void aDecoyDuckDisplaysFloating() throws Exception {
        aDecoy.display();
        assertEquals("I am a wooden decoy", outContent.toString().replaceAll("\\n", ""));
    }

    @After
    public void afterEach(){
        System.setOut(null);
    }
}
