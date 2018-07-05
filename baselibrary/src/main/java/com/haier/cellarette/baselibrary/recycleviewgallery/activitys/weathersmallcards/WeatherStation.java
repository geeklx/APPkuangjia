package com.haier.cellarette.baselibrary.recycleviewgallery.activitys.weathersmallcards;


import com.haier.cellarette.baselibrary.R;

import java.util.Arrays;
import java.util.List;



public class WeatherStation {


    public static WeatherStation get() {
        return new WeatherStation();
    }

    private WeatherStation() {
    }

    public List<Forecast> getForecasts() {
        return Arrays.asList(
                new Forecast("Pisa", R.drawable.pisa, "16", Weather.PERIODIC_CLOUDS),
                new Forecast("Paris", R.drawable.paris, "14", Weather.CLOUDY),
                new Forecast("New York", R.drawable.new_york, "9", Weather.MOSTLY_CLOUDY),
                new Forecast("Rome", R.drawable.rome, "18", Weather.PARTLY_CLOUDY),
                new Forecast("London", R.drawable.london, "6", Weather.CLEAR),
                new Forecast("Washington", R.drawable.washington, "20", Weather.PERIODIC_CLOUDS));
    }
}
