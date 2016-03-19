package com.example.findgo.Instruction;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.findgo.R;


public class FirstGuideFragment extends BaseGuideFragment {

    final long ANIMATION_DURATION = 500;
    final long ANIMATION_OFFSET = 200;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide_first, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      
    }

    @Override
    public int[] getChildViewIds() {
        return new int[]{
                R.id.guide_item_1
        };
    }

    @Override
    public int getRootViewId() {
        return R.id.layout_guide_first;
    }
}
