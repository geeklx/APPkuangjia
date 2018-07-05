package com.haier.cellarette.baselibrary.recycleviewgallery.activitys.weathersmallcards;

public enum Weather {

    PERIODIC_CLOUDS("Periodic Clouds"),
    CLOUDY("Cloudy"),
    MOSTLY_CLOUDY("Mostly Cloudy"),
    PARTLY_CLOUDY("Partly Cloudy"),
    CLEAR("Clear");

    private String displayName;

    Weather(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
