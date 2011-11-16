package com.helkaerea.dp.observer.weather.data;

import com.helkaerea.dp.observer.weather.display.Observer;

public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
