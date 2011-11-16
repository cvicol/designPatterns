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
 * Time: 1:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuackBehaviourTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void beforeAny() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void quackBehaviourLetsOutNormalQuack() throws Exception {
       QuackBehavior quackBehaviour = new Quack();
       quackBehaviour.quack();
       assertEquals("Quack", outContent.toString().replaceAll("\\n", ""));
    }

    @Test
    public void aMuteQuackBehaviourDowsNotQuack() throws Exception {
        QuackBehavior quackBehavior = new MuteQuack();
        quackBehavior.quack();
        assertEquals("<< Silence >>", outContent.toString().replaceAll("\\n", ""));
    }

    @Test
    public void aSqueakIsAFormOfQuack() throws Exception {
        QuackBehavior quackBehavior = new Squeak();
        quackBehavior.quack();
        assertEquals("Squeak", outContent.toString().replaceAll("\\n", ""));
    }

    @After
    public void afterEach(){
        System.setOut(null);
    }
}
