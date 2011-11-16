package com.helkaerea.dp.factory.pizzaaf;

import com.helkaerea.dp.factory.pizzaaf.cheese.Cheese;
import com.helkaerea.dp.factory.pizzaaf.clams.Clams;
import com.helkaerea.dp.factory.pizzaaf.dough.Dough;
import com.helkaerea.dp.factory.pizzaaf.pepperoni.Pepperoni;
import com.helkaerea.dp.factory.pizzaaf.sauce.Sauce;
import com.helkaerea.dp.factory.pizzaaf.veggie.Veggies;

public interface PizzaIngredientFactory {
 
	public Dough createDough();
	public Sauce createSauce();
	public Cheese createCheese();
	public Veggies[] createVeggies();
	public Pepperoni createPepperoni();
	public Clams createClam();
 
}
