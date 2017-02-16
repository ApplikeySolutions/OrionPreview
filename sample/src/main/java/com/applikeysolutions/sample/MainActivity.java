package com.applikeysolutions.sample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applikeysolutions.animation.BlurAnimation;
import com.applikeysolutions.animation.animation.ScaleAnimation;
import com.applikeysolutions.animation.animation.TranslationAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imgIcon)
    ImageView imgIcon;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.imgBackground)
    ImageView imgBackground;
    private float screenHeight;
    private float screenWidth;
    private Bitmap blurredBitmap;

    private ScaleAnimation increaseAnimationImage;
    private ScaleAnimation decreaseAnimationImage;
    private ScaleAnimation increaseAnimationText;
    private ScaleAnimation decreaseAnimationText;
    private TranslationAnimation upAnimationImageView;
    private TranslationAnimation arcUpAnimationTv;
    private TranslationAnimation downAnimationImageView;
    private TranslationAnimation arcDownAnimationTv;
    private BlurAnimation blurAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;

        imgIcon.setOnClickListener(v -> {
            upAnimationImageView.showAnimation();
            arcUpAnimationTv.showAnimation();
            increaseAnimationImage.showAnimation();
            increaseAnimationText.showAnimation();

            imgIcon.setClickable(false);
            activityMain.setClickable(true);
            addBlur();
            tv.setPivotX(tv.getWidth());
        });

        activityMain.setOnClickListener(v -> {
            downAnimationImageView.showAnimation();
            arcDownAnimationTv.showAnimation();
            decreaseAnimationImage.showAnimation();
            decreaseAnimationText.showAnimation();

            activityMain.setClickable(false);
            imgIcon.setClickable(true);
            removeBlur();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initAnimation();
    }


    private void initAnimation() {
        tv.post(() -> {
            increaseAnimationImage = new ScaleAnimation.ScaleAnimationBuilder(imgBackground, 1.5f, 1.5f)
                    .build();
            increaseAnimationText = new ScaleAnimation.ScaleAnimationBuilder(tv, 1.5f, 1.5f)
                    .build();
            decreaseAnimationImage = new ScaleAnimation.ScaleAnimationBuilder(imgBackground, 1f, 1f)
                    .build();
            decreaseAnimationText = new ScaleAnimation.ScaleAnimationBuilder(tv, 1f, 1f)
                    .build();
            upAnimationImageView = new TranslationAnimation.TranslationAnimationBuilder(imgIcon,
                    TranslationAnimation.TranslationMode.TranslationY, 0, -(screenHeight / 3))
                    .build();
            arcUpAnimationTv = new TranslationAnimation.TranslationAnimationBuilder(tv,
                    TranslationAnimation.TranslationMode.TranslationAll, 0, screenHeight / 5)
                    .arcMode(TranslationAnimation.ArcMode.ArcUpward)
                    .additionStartPoint(0)
                    .additionEndPoint(screenWidth / 2 - tv.getWidth() / 2)
                    .build();
            downAnimationImageView = new TranslationAnimation.TranslationAnimationBuilder(imgIcon,
                    TranslationAnimation.TranslationMode.TranslationY, -(screenHeight / 3), 0)
                    .build();
            arcDownAnimationTv = new TranslationAnimation.TranslationAnimationBuilder(tv,
                    TranslationAnimation.TranslationMode.TranslationAll, screenHeight / 5, 0)
                    .arcMode(TranslationAnimation.ArcMode.ArcDownard)
                    .additionStartPoint(screenWidth / 2 - tv.getWidth() / 2)
                    .additionEndPoint(0)
                    .build();
            blurAnimation = new BlurAnimation.BlurAnimationBuilder(0.4f, 7f)
                    .build();

            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),
                    R.drawable.sample_background);
            blurredBitmap = blurAnimation.blur(MainActivity.this, originalBitmap);
        });
    }


    private void addBlur() {
        imgBackground.setImageBitmap(new BitmapDrawable(getResources(), blurredBitmap).getBitmap());
    }

    private void removeBlur() {
        imgBackground.setImageResource(R.drawable.sample_background);
    }
}
