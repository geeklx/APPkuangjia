package com.haier.cellarette.baselibrary.recycleviewgallery.activitys.weathersmallcards;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.recycleviewgallery.discretescrollviews.DiscreteScrollView;
import com.haier.cellarette.baselibrary.recycleviewgallery.discretescrollviews.transform.ScaleTransformer;
import com.haier.cellarette.baselibrary.recycleviewgallery.discretescrollviews.util.DiscreteScrollUtil;

import java.util.List;


public class WeatherActivity extends AppCompatActivity implements
        DiscreteScrollView.ScrollStateChangeListener<ForecastAdapter.ViewHolder>,
        DiscreteScrollView.OnItemChangedListener<ForecastAdapter.ViewHolder>,
        View.OnClickListener {

    private List<Forecast> forecasts;

    private ForecastView forecastView;
    private DiscreteScrollView cityPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        DiscreteScrollUtil.init(this,getString(R.string.pref_key_transition_time2));
        forecastView = findViewById(R.id.forecast_view);

        forecasts = WeatherStation.get().getForecasts();
        cityPicker = findViewById(R.id.forecast_city_picker);
        cityPicker.setSlideOnFling(true);
        cityPicker.setAdapter(new ForecastAdapter(forecasts));
        cityPicker.addOnItemChangedListener(this);
        cityPicker.addScrollStateChangeListener(this);
        cityPicker.scrollToPosition(2);
        cityPicker.setItemTransitionTimeMillis(DiscreteScrollUtil.getTransitionTime());
        cityPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        forecastView.setForecast(forecasts.get(0));

        findViewById(R.id.homess).setOnClickListener(this);
        findViewById(R.id.btn_transition_time).setOnClickListener(this);
        findViewById(R.id.btn_smooth_scroll).setOnClickListener(this);
    }

    @Override
    public void onCurrentItemChanged(@Nullable ForecastAdapter.ViewHolder holder, int position) {
        //viewHolder will never be null, because we never remove items from adapter's list
        if (holder != null) {
            forecastView.setForecast(forecasts.get(position));
            holder.showText();
        }
    }

    @Override
    public void onScrollStart(@NonNull ForecastAdapter.ViewHolder holder, int position) {
        holder.hideText();
    }

    @Override
    public void onScroll(
            float position,
            int currentIndex, int newIndex,
            @Nullable ForecastAdapter.ViewHolder currentHolder,
            @Nullable ForecastAdapter.ViewHolder newHolder) {
        Forecast current = forecasts.get(currentIndex);
        if (newIndex >= 0 && newIndex < cityPicker.getAdapter().getItemCount()) {
            Forecast next = forecasts.get(newIndex);
            forecastView.onScroll(1f - Math.abs(position), current, next);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.homess) {
            finish();

        } else if (i == R.id.btn_transition_time) {
            DiscreteScrollUtil.configureTransitionTime2(cityPicker);

        } else if (i == R.id.btn_smooth_scroll) {
            DiscreteScrollUtil.smoothScrollToUserSelectedPosition(cityPicker, v);

        }
    }

    @Override
    public void onScrollEnd(@NonNull ForecastAdapter.ViewHolder holder, int position) {

    }
}
