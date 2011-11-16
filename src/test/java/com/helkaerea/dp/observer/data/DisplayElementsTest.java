package com.helkaerea.dp.observer.data;

import com.helkaerea.dp.observer.weather.data.WeatherData;
import com.helkaerea.dp.observer.weather.display.CurrentConditionsDisplay;
import com.helkaerea.dp.observer.weather.display.ForecastDisplay;
import com.helkaerea.dp.observer.weather.display.HeatIndexDisplay;
import com.helkaerea.dp.observer.weather.display.StatisticsDisplay;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DisplayElementsTest {

    public static final int TEMPERATURE_80 = 80;
    public static final int HUMIDITY_65 = 65;
    public static final float PRESSURE_30_4 = 30.4f;
    public static final int TEMPERATURE_82 = 82;
    public static final int TEMPERATURE_78 = 78;
    public static final float PRESSURE_29_2 = 29.2f;
    public static final int HUMIDITY_90 = 90;
    public static final int HUMIDITY_70 = 70;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private static WeatherData weatherData;
    @Before
    public void beforeAny() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeClass
    public static void setUp() {
       weatherData = new WeatherData();
    }

    @Test
    public void anObservableDisplayRegisteresItselfToAnWeatherDataObjectWhenCreated() throws Exception {
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        assertThat(weatherData.getSubscribers().size(), is(1));
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        assertThat(weatherData.getSubscribers().size(), is(2));
        HeatIndexDisplay   heatIndexDisplay = new HeatIndexDisplay(weatherData);
        assertThat(weatherData.getSubscribers().size(), is(3));
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        assertThat(weatherData.getSubscribers().size(), is(4));
        assertThat(weatherData.getSubscribers(),
                Matchers.hasItems(new com.helkaerea.dp.observer.weather.display.Observer[]{currentConditionsDisplay, forecastDisplay, heatIndexDisplay, statisticsDisplay}));

        weatherData.removeObserver(currentConditionsDisplay);
        weatherData.removeObserver(forecastDisplay);
        weatherData.removeObserver(heatIndexDisplay);
        weatherData.removeObserver(statisticsDisplay);
    }

    @Test
    public void currentConditionsAreDisplayedIfMeasurementsChange() throws Exception {
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        assertEquals("Current conditions: 80.0F degrees and 65.0% humidity", outContent.toString().replaceAll("\\n", ""));
        weatherData.removeObserver(currentConditionsDisplay);
    }

    @Test
    public void forecatsDisplayForMeasurementChangesOfGoodWeatherAreComputed() throws Exception {
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        weatherData.setMeasurements(TEMPERATURE_80, HUMIDITY_65, PRESSURE_30_4);
        assertEquals("Forecast: Improving weather on the way!", outContent.toString().replaceAll("\\n", ""));
        weatherData.removeObserver(forecastDisplay);
    }

    @Test
    public void forecastDisplayForBadWeatherAreComputed() throws Exception {
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        weatherData.setMeasurements(TEMPERATURE_82, 70, PRESSURE_29_2);
        assertEquals("Forecast: Watch out for cooler, rainy weather", outContent.toString().replaceAll("\\n", ""));
        weatherData.removeObserver(forecastDisplay);
    }

    @Test
    public void ifPressureRemainsTheSameForecastDowsNotChange() throws Exception {
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        weatherData.setMeasurements(TEMPERATURE_78, HUMIDITY_90, PRESSURE_29_2);
        weatherData.setMeasurements(TEMPERATURE_82, HUMIDITY_70, PRESSURE_29_2);
        assertEquals("Forecast: Watch out for cooler, rainy weatherForecast: More of the same", outContent.toString().replaceAll("\\n", ""));
        weatherData.removeObserver(forecastDisplay);
    }

    @Test
    public void IfReadingsChangeOverTiveSatisticsDisplayAverage() throws Exception {
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        weatherData.setMeasurements(TEMPERATURE_80, HUMIDITY_65, PRESSURE_30_4);
        weatherData.setMeasurements(TEMPERATURE_78, HUMIDITY_90, PRESSURE_29_2);
        weatherData.setMeasurements(TEMPERATURE_82, HUMIDITY_70, PRESSURE_29_2);
        assertEquals("Avg/Max/Min temperature = 80.0/80.0/80.0...Avg/Max/Min temperature = 79.0/80.0/78.0...Avg/Max/Min temperature = 80.0/82.0/78.0...", outContent.toString().replaceAll("\\n", ""));
        weatherData.removeObserver(statisticsDisplay);
    }

    @After
    public void afterEach(){
        System.setOut(null);
    }
}
