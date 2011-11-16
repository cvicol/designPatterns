package com.helkaerea.dp.decorator.starcoffee;

import org.easymock.EasyMock;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.easymock.EasyMock.eq;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class BeverageTest {

    private Beverage darkRoast;
    private Beverage espresso;
    private Beverage houseBlend;

    @Before
    public void setUp() throws Exception {
        darkRoast = new DarkRoast();
        espresso = new Espresso();
        houseBlend = new HouseBlend();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetDescription() throws Exception {
       assertThat(darkRoast.getDescription(), is("Dark Roast Coffee"));
       assertThat(espresso.getDescription(), is("Espresso"));
       assertThat(houseBlend.getDescription(), is("House Blend Coffee"));
    }

    @Test
    public void testCost() throws Exception {
       assertEquals(0.99, darkRoast.cost());
       assertEquals(1.99, espresso.cost());
       assertEquals(.89, houseBlend.cost());
    }
}
