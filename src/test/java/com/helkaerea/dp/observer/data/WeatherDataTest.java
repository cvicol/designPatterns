package com.helkaerea.dp.observer.data;


import com.helkaerea.dp.observer.weather.data.WeatherData;
import com.helkaerea.dp.observer.weather.display.Observer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class WeatherDataTest {

    public static final float TEMPERATURE_35_CELCIUS = 35f;
    public static final float HUMIDITY_15_PERCENT = 15f;
    public static final float PRESSURE_12_ATM = 12f;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final Observer anObserver = new Observer() {
            @Override
            public void update(float temp, float humidity, float pressure) {
                System.out.println("I have been updated with temperature:" + temp + " humidity:" + humidity + " pressure:" + pressure);
            }
        };

    @Before
    public void beforeAny() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void weatherDataSubscribersObservers() throws Exception {
        WeatherData weatherData = new WeatherData();
        weatherData.registerObserver(anObserver);
        assertThat(weatherData.getSubscribers().size(), is(1));
        assertThat(weatherData.getSubscribers(), contains(anObserver));
    }

    @Test
    public void weatherDataUnregisteresObservers() throws Exception {
        WeatherData weatherData = new WeatherData();
        weatherData.registerObserver(anObserver);
        assertThat(weatherData.getSubscribers().size(), is(1));
        weatherData.removeObserver(anObserver);
        assertThat(weatherData.getSubscribers().size(), is(0));
    }

    @Test
    public void whenWeatherDataChangeAllObserversAreNotified() throws Exception {
        WeatherData weatherData = new WeatherData();
        weatherData.registerObserver(anObserver);
        assertThat(weatherData.getSubscribers().size(), is(1));

        weatherData.setMeasurements(TEMPERATURE_35_CELCIUS, HUMIDITY_15_PERCENT, PRESSURE_12_ATM);
        assertEquals("I have been updated with temperature:35.0 humidity:15.0 pressure:12.0", outContent.toString().replaceAll("\\n", ""));

    }

    @After
    public void afterEach(){
        System.setOut(null);
    }


}
