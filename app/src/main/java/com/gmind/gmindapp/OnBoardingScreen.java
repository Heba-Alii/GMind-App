package com.gmind.gmindapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

//import com.gmind.gmindapp.databinding.ActivityOnBoardingScreenBinding;

public class OnBoardingScreen extends AppCompatActivity {
    private LinearLayout linearLayout;
    private IntroPref introPref;
    private int[] layouts;
    private TextView[] dots;
    private MyViewPagerAdapter viewPagerAdapter;
    private TextView tvNext;
    private ViewPager viewPager;
    private LinearLayout layout_dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_on_boarding_screen);
        viewPager = findViewById(R.id.viewPager);
        tvNext = findViewById(R.id.tvNext);
        layout_dots = findViewById(R.id.layout_dots);

        viewPager = findViewById(R.id.viewPager);
        introPref = new IntroPref(this);
        if (!introPref.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        layouts = new int[]{
                R.layout.intro_one,
                R.layout.intro_two,
                R.layout.intro_three
        };
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current = getItem(+1);
                if (current < layouts.length) {
                    //binding.viewPager.setCurrentItem(current);
                    viewPager.setCurrentItem(current);

                } else {
                    launchHomeScreen();
                }
            }
        });
        viewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        addBottomDots(0);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            if (position == layouts.length - 1) {
                tvNext.setText("Get Started");
            } else {
                tvNext.setText("NEXT");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void addBottomDots(int currentPages) {
        dots = new TextView[layouts.length];
        int[] activeColors = getResources().getIntArray(R.array.active);
        int[] inactiveColors = getResources().getIntArray(R.array.inactive);
        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226", Html.FROM_HTML_MODE_LEGACY));
            dots[i].setTextSize(50);
            dots[i].setTextColor(inactiveColors[currentPages]);
            layout_dots.addView(dots[i]);
        }
        if (dots.length > 0) {

            dots[currentPages].setTextColor(activeColors[currentPages]);
        }
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {

        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + 1;
    }

    private void launchHomeScreen() {
        introPref.setIsFirstTimeLaunch(false);
        startActivity(new Intent(OnBoardingScreen.this, MainActivity.class));
        finish();
    }
}