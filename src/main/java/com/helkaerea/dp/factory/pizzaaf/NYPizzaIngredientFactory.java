package com.helkaerea.dp.factory.pizzaaf;

import com.helkaerea.dp.factory.pizzaaf.cheese.Cheese;
import com.helkaerea.dp.factory.pizzaaf.cheese.ReggianoCheese;
import com.helkaerea.dp.factory.pizzaaf.clams.Clams;
import com.helkaerea.dp.factory.pizzaaf.clams.FreshClams;
import com.helkaerea.dp.factory.pizzaaf.dough.Dough;
import com.helkaerea.dp.factory.pizzaaf.dough.ThinCrustDough;
import com.helkaerea.dp.factory.pizzaaf.pepperoni.Pepperoni;
import com.helkaerea.dp.factory.pizzaaf.pepperoni.SlicedPepperoni;
import com.helkaerea.dp.factory.pizzaaf.sauce.MarinaraSauce;
import com.helkaerea.dp.factory.pizzaaf.sauce.Sauce;
import com.helkaerea.dp.factory.pizzaaf.veggie.*;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
 
	public Dough createDough() {
		return new ThinCrustDough();
	}
 
	public Sauce createSauce() {
		return new MarinaraSauce();
	}
 
	public Cheese createCheese() {
		return new ReggianoCheese();
	}
 
	public Veggies[] createVeggies() {
		Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
		return veggies;
	}
 
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FreshClams();
	}
}
