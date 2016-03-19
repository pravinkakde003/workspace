package com.example.findgo.Instruction;
import android.animation.ArgbEvaluator;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextSwitcher;

import com.example.findgo.Dashboard_Activity;
import com.example.findgo.MainActivity;
import com.example.findgo.R;
import com.pravin.lib_viewpager_new.CirclePageIndicator;





public class InstructionActivity extends  FragmentActivity {

    final float PARALLAX_COEFFICIENT = 1.2f;
    final float DISTANCE_COEFFICIENT = 0.5f;
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    TextSwitcher mTextSwitcher;
    Button btn_sign_up,btn_cancel;

    ViewPager mPager;
    CirclePageIndicator mPagerIndicator;

    FragmentAdapter mAdapter;

    SparseArray<int[]> mLayoutViewIdsMap = new SparseArray<int[]>();

    private void addGuide(BaseGuideFragment fragment) {
        mAdapter.addItem(fragment);
        mLayoutViewIdsMap.put(fragment.getRootViewId(), fragment.getChildViewIds());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.fragment_guide_base);
    	


        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
       
        btn_sign_up =(Button) findViewById(R.id.btn_sign_up);
        btn_cancel =(Button) findViewById(R.id.btn_cancel);
        
       
        
        btn_cancel.setOnClickListener(new View.OnClickListener() {
			
 			@Override
 			public void onClick(View v) {}
 		});  
        

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
			
     			@Override
     			public void onClick(View v) {
     				// TODO Auto-generated method stub
     				Intent intent = new Intent(InstructionActivity.this,Dashboard_Activity.class);
     				startActivity(intent);
     				finish();
     				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
     			}
     		});
        

        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        addGuide(new FirstGuideFragment());
        addGuide(new SecondGuideFragment());
        
      

        mPager.setAdapter(mAdapter);
        mPagerIndicator.setViewPager(mPager);

        mPager.setPageTransformer(true, new ParallaxTransformer(PARALLAX_COEFFICIENT, DISTANCE_COEFFICIENT));
        mPagerIndicator.setOnPageChangeListener(new GuidePageChangeListener());
    }

    
 
    
    class ParallaxTransformer implements ViewPager.PageTransformer {

        float parallaxCoefficient;
        float distanceCoefficient;

        public ParallaxTransformer(float parallaxCoefficient, float distanceCoefficient) {
            this.parallaxCoefficient = parallaxCoefficient;
            this.distanceCoefficient = distanceCoefficient;
        }

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                        (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }

  
  
    }
    

    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        ArgbEvaluator mColorEvaluator;

        int mPageWidth, mTotalScrollWidth;

        int mGuideStartBackgroundColor, mGuideEndBackgroundColor;

        String[] mGuideTips;

      
        public GuidePageChangeListener() {
            mColorEvaluator = new ArgbEvaluator();

            mPageWidth = getWindowManager().getDefaultDisplay().getWidth();
            mTotalScrollWidth = mPageWidth * mAdapter.getCount();

            mGuideStartBackgroundColor = getResources().getColor(R.color.transperant);
            mGuideEndBackgroundColor = getResources().getColor(R.color.transperant);

//            mGuideTips = getResources().getStringArray(R.array.array_guide_tips);
        }

      
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            float ratio = (mPageWidth * position + positionOffsetPixels) / (float) mTotalScrollWidth;
            Integer color = (Integer) mColorEvaluator.evaluate(ratio, mGuideStartBackgroundColor, mGuideEndBackgroundColor);
            mPager.setBackgroundColor(color);
        }

        @Override
        public void onPageSelected(int position) {
            if (mGuideTips != null && mGuideTips.length > position) {
                mTextSwitcher.setText(mGuideTips[position]);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
