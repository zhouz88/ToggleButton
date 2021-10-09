package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.jetbrains.annotations.NotNull;

public class Fragment2 extends Fragment {
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("zhouzheng", "fragment1 onviewcreated");
    }

    @Override
    public void onStart() {
        Log.d("zhouzheng", "fragment1 onstart 开始");
        super.onStart();
        Log.d("zhouzheng", "fragment1 onstart 结束");
    }

    @Override
    public void onResume() {
        Log.d("zhouzheng", "fragment1 resume 开始");
        super.onResume();
        Log.d("zhouzheng", "fragment1 resume 结束");
    }
}
