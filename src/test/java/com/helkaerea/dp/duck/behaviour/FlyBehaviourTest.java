package com.helkaerea.dp.duck.behaviour;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: cvicol
 * Date: 11/9/11
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class FlyBehaviourTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void beforeAny() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void flyBehaviourWithWingsShouldFlyAccordingly() throws Exception {
        FlyBehavior flying = new FlyWithWings();
        flying.fly();
        assertEquals("I'm flying with wings!!", outContent.toString().replaceAll("\\n", ""));
    }

    @Test
    public void flyBehaviourForNoFlying() throws Exception {
        FlyBehavior flying = new FlyNoWay();
        flying.fly();
        assertEquals("I can't fly", outContent.toString().replaceAll("\\n", ""));
    }

    @Test
    public void flyBehaviourWithRocketsBehavesAccordingly() throws Exception {
        FlyBehavior flying = new FlyRocketPowered();
        flying.fly();
        assertEquals("I'm flying with a rocket", outContent.toString().replaceAll("\\n", ""));
    }


    @After
    public void afterEach(){
        System.setOut(null);
    }

}
