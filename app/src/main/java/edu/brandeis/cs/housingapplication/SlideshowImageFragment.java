package edu.brandeis.cs.housingapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by eureyuri on 2017/11/27.
 */

public class SlideshowImageFragment extends Fragment {
    private int image;

    public static SlideshowImageFragment newInstance(int image) {
        SlideshowImageFragment fragment = new SlideshowImageFragment();
        Bundle args = new Bundle();
        args.putInt("image", image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        image = getArguments().getInt("image", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.slideshow_image);
        imageView.setImageResource(image);
        return view;
    }
}
