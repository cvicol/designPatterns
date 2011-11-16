package com.helkaerea.dp.duck;

import com.helkaerea.dp.duck.behaviour.FlyBehavior;
import com.helkaerea.dp.duck.behaviour.QuackBehavior;

/**
 * Created by IntelliJ IDEA.
 * User: cvicol
 * Date: 11/9/11
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Duck {
    private FlyBehavior flyBehaviour;
    private QuackBehavior quackBehavior;

    public abstract void display();

    public void setFlyBehaviour(FlyBehavior flyWithWings) {
        this.flyBehaviour = flyWithWings;
    }

    public  FlyBehavior getFlyBehaviour() {
        return this.flyBehaviour;
    }

    public void setQuackBehaviour(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public QuackBehavior getQuackBehaviour() {
        return this.quackBehavior;
    }

    public void makeSound() {
        quackBehavior.quack();
    }

    public void fly() {
        flyBehaviour.fly();
    }
}
