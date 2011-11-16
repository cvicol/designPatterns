package com.helkaerea.dp.factory.pizzaaf;

import com.helkaerea.dp.factory.pizzaaf.cheese.Cheese;
import com.helkaerea.dp.factory.pizzaaf.cheese.MozzarellaCheese;
import com.helkaerea.dp.factory.pizzaaf.clams.Clams;
import com.helkaerea.dp.factory.pizzaaf.clams.FrozenClams;
import com.helkaerea.dp.factory.pizzaaf.dough.Dough;
import com.helkaerea.dp.factory.pizzaaf.dough.ThickCrustDough;
import com.helkaerea.dp.factory.pizzaaf.pepperoni.Pepperoni;
import com.helkaerea.dp.factory.pizzaaf.pepperoni.SlicedPepperoni;
import com.helkaerea.dp.factory.pizzaaf.sauce.PlumTomatoSauce;
import com.helkaerea.dp.factory.pizzaaf.sauce.Sauce;
import com.helkaerea.dp.factory.pizzaaf.veggie.BlackOlives;
import com.helkaerea.dp.factory.pizzaaf.veggie.Eggplant;
import com.helkaerea.dp.factory.pizzaaf.veggie.Spinach;
import com.helkaerea.dp.factory.pizzaaf.veggie.Veggies;

public class ChicagoPizzaIngredientFactory
	implements PizzaIngredientFactory 
{

	public Dough createDough() {
		return new ThickCrustDough();
	}

	public Sauce createSauce() {
		return new PlumTomatoSauce();
	}

	public Cheese createCheese() {
		return new MozzarellaCheese();
	}

	public Veggies[] createVeggies() {
		Veggies veggies[] = { new BlackOlives(),
		                      new Spinach(),
		                      new Eggplant() };
		return veggies;
	}

	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FrozenClams();
	}
}
