package com.helkaerea.dp.decorator.starcoffee.condiments;

import com.helkaerea.dp.decorator.starcoffee.Beverage;
import com.helkaerea.dp.decorator.starcoffee.DarkRoast;
import com.helkaerea.dp.decorator.starcoffee.condiments.Milk;
import com.helkaerea.dp.decorator.starcoffee.condiments.Mocha;
import com.helkaerea.dp.decorator.starcoffee.condiments.Whip;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class CondimentDecoratorTest {

    private Beverage aBeverage;

    @Before
    public void setUp() throws Exception {
        aBeverage = new DarkRoast();

    }

    @Test
    public void aBevarageWithMilkAddsCondimentToDescription() throws Exception {
        Beverage aBeverageWithMilk = new Milk(aBeverage);
        assertThat("Dark Roast Coffee, Milk", is(aBeverageWithMilk.getDescription()));
    }

    @Test
    public void addingMultipleCondimentsChangesTheBeverageCOmposition() throws Exception {
        Beverage aComplexBeverage = new Whip(new Milk(aBeverage));
        assertThat("Dark Roast Coffee, Milk, Whip", is(aComplexBeverage.getDescription()));
    }

    @Test
    public void aCondimentedBeverageCostadsThePriceOfTheCondiment() throws Exception {
       Beverage mocha = new Mocha(aBeverage);
       assertEquals(1.19, mocha.cost());
    }

    @Test
    public void weCanCreateAnDoubleMocha() throws Exception {


		Beverage doubleMocha = new DarkRoast();
		doubleMocha = new Mocha(doubleMocha);
		doubleMocha = new Mocha(doubleMocha);
		doubleMocha = new Whip(doubleMocha);

        assertThat("Dark Roast Coffee, Mocha, Mocha, Whip", is(equalTo(doubleMocha.getDescription())));
        assertEquals(1.49, doubleMocha.cost());
    }
}
