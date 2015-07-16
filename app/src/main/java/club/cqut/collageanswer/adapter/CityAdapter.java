package club.cqut.collageanswer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import club.cqut.collageanswer.R;
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;

/**
 * wheel的adapter
 * Created by fenghao on 2015/7/12.
 */
public class CityAdapter extends AbstractWheelTextAdapter{
    ArrayList<String> arrayList;

    public CityAdapter(Context context, ArrayList arrayList) {
        super(context, R.layout.layout_city, NO_RESOURCE);
        setItemTextResource(R.id.city_name);
        this.arrayList = arrayList;
    }

    @Override
    public View getItem(int index, View cachedView, ViewGroup parent) {
        View view = super.getItem(index, cachedView, parent);
        return view;
    }

    @Override
    public int getItemsCount() {
        return arrayList.size();
    }

    @Override
    protected CharSequence getItemText(int index) {
        return arrayList.get(index);
    }
}