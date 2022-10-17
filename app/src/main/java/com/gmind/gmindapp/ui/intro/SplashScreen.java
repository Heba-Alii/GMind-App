package com.gmind.gmindapp.ui.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmind.gmindapp.R;
import com.gmind.gmindapp.databinding.ActivitySplashScreenBinding;
import com.gmind.gmindapp.ui.intro.OnBoardingScreen;

public class SplashScreen extends AppCompatActivity {
    //Variables
    private ActivitySplashScreenBinding binding;
    Animation tobAnima, bottomAnim;
    ImageView logo;
    TextView gMind;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(view);
        //Animations
        tobAnima = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
//Hooks
        logo=findViewById(R.id.logo);
        gMind=findViewById(R.id.gMindTxt);

        logo.setAnimation(tobAnima);
        gMind.setAnimation(bottomAnim);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                runActivity();
            }
        };
        handler.postDelayed(runnable, 3 * 1000);
    }

    public void runActivity() {
        Intent intent = new Intent(this, OnBoardingScreen.class);
        startActivity(intent);
        finish();
    }
}