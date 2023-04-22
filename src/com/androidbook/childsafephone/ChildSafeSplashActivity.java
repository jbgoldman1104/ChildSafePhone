package com.androidbook.childsafephone;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ChildSafeSplashActivity extends ChildSafeActivity {
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        startAnimating();
    }

	private void startAnimating() {
		Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade_in);
		
		// Title fades in
		TextView title = (TextView) findViewById(R.id.TextView_title);
		title.startAnimation(fade);
		
		// Version info fades in
		TextView version = (TextView) findViewById(R.id.TextViewBottomVersion);
		version.startAnimation(fade);
		
		// Names info fades in
		TextView names = (TextView) findViewById(R.id.TextViewBottomNames);
		names.startAnimation(fade);
		
		// Images fade in
		ImageView splash1 = (ImageView) findViewById(R.id.ImageView2_Left);
		splash1.startAnimation(fade);		
		ImageView splash2 = (ImageView) findViewById(R.id.ImageView2_Right);
		splash2.startAnimation(fade);		
		ImageView splash3 = (ImageView) findViewById(R.id.ImageView3_Left);
		splash3.startAnimation(fade);		
		ImageView splash4 = (ImageView) findViewById(R.id.ImageView3_Right);
		splash4.startAnimation(fade);
		
		// after animation, automatically goes to the main menu
		fade.setAnimationListener(new AnimationListener() { 
       	 public void onAnimationEnd(Animation animation) {
       		 startActivity(new Intent(ChildSafeSplashActivity.this, ChildSafeMenuActivity.class));
       		 ChildSafeSplashActivity.this.finish();
       	 }

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			} 
        });
	}
	
	protected void onPause() { 
    	super.onPause(); 
    	// Stop the animation
    	TextView title = (TextView) findViewById(R.id.TextView_title);
    	title.clearAnimation();
    	
		TextView version = (TextView) findViewById(R.id.TextViewBottomVersion);
		version.clearAnimation();
		
		TextView names = (TextView) findViewById(R.id.TextViewBottomNames);
		names.clearAnimation();
		
		ImageView splash1 = (ImageView) findViewById(R.id.ImageView2_Left);
		splash1.clearAnimation();		
		ImageView splash2 = (ImageView) findViewById(R.id.ImageView2_Right);
		splash2.clearAnimation();		
		ImageView splash3 = (ImageView) findViewById(R.id.ImageView3_Left);
		splash3.clearAnimation();	
		ImageView splash4 = (ImageView) findViewById(R.id.ImageView3_Right);
		splash4.clearAnimation();
    }
    
    @Override
    protected void onResume() {
        super.onResume();

        // Start animating at the beginning so we get the full splash screen experience
        startAnimating();
    }
}