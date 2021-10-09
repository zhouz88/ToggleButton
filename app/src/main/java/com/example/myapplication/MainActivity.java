package com.example.myapplication;

import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager2 viewPager2 = findViewById(R.id.vp2);
        viewPager2.setAdapter(new Adapter(this));
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(0);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(1);
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(2);
            }
        });
        Log.d("zhouzheng" ,"activity created");
    }

    @Override
    protected void onStart() {
        Log.d("zhouzheng" ,"activity start 开始");
        super.onStart();
        Log.d("zhouzheng" ,"activity start 结束");
    }

    @Override
    protected void onResume() {
        Log.d("zhouzheng" ,"activity resume 开始");
        super.onResume();
        Log.d("zhouzheng" ,"activity resume 结束");
    }

    private static final class Adapter extends FragmentStateAdapter {

        public Adapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                 return new Fragment1();
            } else if (position == 1) {
                return new Fragment2();
            } else {
                return new Fragment3();
            }
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }
}