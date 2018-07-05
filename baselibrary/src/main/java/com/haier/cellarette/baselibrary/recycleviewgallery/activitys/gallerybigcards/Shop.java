package com.haier.cellarette.baselibrary.recycleviewgallery.activitys.gallerybigcards;

import android.content.Context;
import android.content.SharedPreferences;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.common.BaseApp;

import java.util.Arrays;
import java.util.List;


public class Shop {

    private static final String STORAGE = "shop";

    public static Shop get() {
        return new Shop();
    }

    private SharedPreferences storage;

    private Shop() {
        storage = BaseApp.get().getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
    }

    public List<Item> getData() {
        return Arrays.asList(
                new Item(1, "Everyday Candle", "$12.00 USD", R.drawable.shop1),
                new Item(2, "Small Porcelain Bowl", "$50.00 USD", R.drawable.shop2),
                new Item(3, "Favourite Board", "$265.00 USD", R.drawable.shop3),
                new Item(4, "Earthenware Bowl", "$18.00 USD", R.drawable.shop4),
                new Item(5, "Porcelain Dessert Plate", "$36.00 USD", R.drawable.shop5),
                new Item(6, "Detailed Rolling Pin", "$145.00 USD", R.drawable.shop6));
    }

    public boolean isRated(int itemId) {
        return storage.getBoolean(String.valueOf(itemId), false);
    }

    public void setRated(int itemId, boolean isRated) {
        storage.edit().putBoolean(String.valueOf(itemId), isRated).apply();
    }
}
