package edu.brandeis.cs.housingapplication;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by eureyuri on 2017/11/27.
 */

public class CustomPagerAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    int[] resources = {
            R.drawable.slide1,
            R.drawable.slide2
    };

    public CustomPagerAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.view_pager_item, container);
        ImageView imageView = (ImageView)view.findViewById(R.id.image_item);
        imageView.setImageResource(resources[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

    @Override
    public int getCount() {
        return resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (LinearLayout)object;
    }

}
