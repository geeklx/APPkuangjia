package com.haier.cellarette.baselibrary.recycleviewgallery.activitys.gallerybigcards;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.recycleviewgallery.discretescrollviews.DSVOrientation;
import com.haier.cellarette.baselibrary.recycleviewgallery.discretescrollviews.DiscreteScrollView;
import com.haier.cellarette.baselibrary.recycleviewgallery.discretescrollviews.InfiniteScrollAdapter;
import com.haier.cellarette.baselibrary.recycleviewgallery.discretescrollviews.transform.ScaleTransformer;
import com.haier.cellarette.baselibrary.recycleviewgallery.discretescrollviews.util.DiscreteScrollUtil;

import java.util.List;


public class ShopActivity extends AppCompatActivity implements DiscreteScrollView.OnItemChangedListener,
        View.OnClickListener {

    private List<Item> data;
    private Shop shop;

    private TextView currentItemName;
    private TextView currentItemPrice;
    private ImageView rateItemButton;
    private DiscreteScrollView itemPicker;
    private InfiniteScrollAdapter infiniteAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallerybigcards);
        DiscreteScrollUtil.init(this, getString(R.string.pref_key_transition_time1));
        currentItemName = findViewById(R.id.item_name);
        currentItemPrice = findViewById(R.id.item_price);
        rateItemButton = findViewById(R.id.item_btn_rate);

        shop = Shop.get();
        data = shop.getData();
        itemPicker = findViewById(R.id.item_picker);
        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);
        infiniteAdapter = InfiniteScrollAdapter.wrap(new ShopAdapter(data));
        itemPicker.setAdapter(infiniteAdapter);
        itemPicker.setItemTransitionTimeMillis(DiscreteScrollUtil.getTransitionTime());
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        onItemChanged(data.get(0));

        findViewById(R.id.item_btn_rate).setOnClickListener(this);
        findViewById(R.id.item_btn_buy).setOnClickListener(this);
        findViewById(R.id.item_btn_comment).setOnClickListener(this);

        findViewById(R.id.homess).setOnClickListener(this);
        findViewById(R.id.btn_smooth_scroll).setOnClickListener(this);
        findViewById(R.id.btn_transition_time).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.item_btn_rate) {
            int realPosition = infiniteAdapter.getRealPosition(itemPicker.getCurrentItem());
            Item current = data.get(realPosition);
            shop.setRated(current.getId(), !shop.isRated(current.getId()));
            changeRateButtonState(current);

        } else if (i == R.id.homess) {
            finish();

        } else if (i == R.id.btn_transition_time) {
            DiscreteScrollUtil.configureTransitionTime1(itemPicker);

        } else if (i == R.id.btn_smooth_scroll) {
            DiscreteScrollUtil.smoothScrollToUserSelectedPosition(itemPicker, v);

        } else {
            showUnsupportedSnackBar();

        }
    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
        int positionInDataSet = infiniteAdapter.getRealPosition(position);
        onItemChanged(data.get(positionInDataSet));
    }

    private void onItemChanged(Item item) {
        currentItemName.setText(item.getName());
        currentItemPrice.setText(item.getPrice());
        changeRateButtonState(item);
    }

    private void showUnsupportedSnackBar() {
        Snackbar.make(itemPicker, "Unsupported operation", Snackbar.LENGTH_SHORT).show();
    }

    private void changeRateButtonState(Item item) {
        if (shop.isRated(item.getId())) {
            rateItemButton.setImageResource(R.drawable.ic_star_black_24dp);
            rateItemButton.setColorFilter(ContextCompat.getColor(this, R.color.shopRatedStar));
        } else {
            rateItemButton.setImageResource(R.drawable.ic_star_border_black_24dp);
            rateItemButton.setColorFilter(ContextCompat.getColor(this, R.color.shopSecondary));
        }
    }
}
