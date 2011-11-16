package com.helkaerea.dp.observer.weather.display;

public interface Observer {
	public void update(float temp, float humidity, float pressure);
}
