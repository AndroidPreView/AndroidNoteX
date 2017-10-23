package com.hansheng.mylibrary.decorator.Observer.weatherobservable.observer;

import com.hansheng.mylibrary.decorator.Observer.weatherobservable.DisplayElement;
import com.hansheng.mylibrary.decorator.Observer.weatherobservable.subject.WeatherData;

import java.util.Observable;
import java.util.Observer;
//observable可观察者
	
public class CurrentConditionsDisplay implements Observer, DisplayElement {
	Observable observable;
	private float temperature;
	private float humidity;
	
	public CurrentConditionsDisplay(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}
	
	public void update(Observable obs, Object arg) {
		if (obs instanceof WeatherData) {
			WeatherData weatherData = (WeatherData)obs;
			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			display();
		}
	}
	
	public void display() {
		System.out.println("Current conditions: " + temperature 
			+ "F degrees and " + humidity + "% humidity");
	}
}
