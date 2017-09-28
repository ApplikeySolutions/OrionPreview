package com.applikeysolutions.sample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applikeysolutions.animation.BlurAnimation;
import com.applikeysolutions.animation.animation.ScaleAnimation;
import com.applikeysolutions.animation.animation.TranslationAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //Views
    @BindView(R.id.cv_product_details) CardView cvProductDetails;
    @BindView(R.id.ll_product_details) LinearLayout llProductDetails;
    @BindView(R.id.rl_container) RelativeLayout rlContainer;
    @BindView(R.id.tv_product_title) TextView tvProductTitle;
    @BindView(R.id.imgBackground) ImageView imgBackground;

    //Animations
    private ScaleAnimation increaseAnimationImage;
    private ScaleAnimation decreaseAnimationImage;
    private ScaleAnimation increaseAnimationText;
    private ScaleAnimation decreaseAnimationText;
    private TranslationAnimation upAnimationImageView;
    private TranslationAnimation arcUpAnimationTv;
    private TranslationAnimation downAnimationImageView;
    private TranslationAnimation arcDownAnimationTv;
    private BlurAnimation blurAnimation;

    private float screenHeight;
    private float screenWidth;
    private Bitmap blurredBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getScreenSize();
        setProductBottomMargin();
    }

    @OnClick(R.id.cv_product_details)
    void onProductDetailsClick() {
        upAnimationImageView.showAnimation();
        arcUpAnimationTv.showAnimation();
        increaseAnimationImage.showAnimation();
        increaseAnimationText.showAnimation();

        cvProductDetails.setClickable(false);
        rlContainer.setClickable(true);
        addBlur();
    }

    @OnClick(R.id.rl_container)
    void onContainerClick() {
        downAnimationImageView.showAnimation();
        arcDownAnimationTv.showAnimation();
        decreaseAnimationImage.showAnimation();
        decreaseAnimationText.showAnimation();

        rlContainer.setClickable(false);
        cvProductDetails.setClickable(true);
        removeBlur();
    }

    private void getScreenSize(){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }

    private void setProductBottomMargin(){
        llProductDetails.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    llProductDetails.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    llProductDetails.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) cvProductDetails.getLayoutParams();
                params.bottomMargin = llProductDetails.getMeasuredHeight() * -1;
                cvProductDetails.setLayoutParams(params);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initAnimation();
    }

    private void initAnimation() {
        tvProductTitle.post(() -> {
            increaseAnimationImage = new ScaleAnimation.ScaleAnimationBuilder(imgBackground, 1.5f, 1.5f).build();
            increaseAnimationText = new ScaleAnimation.ScaleAnimationBuilder(tvProductTitle, 2.25f, 2.25f).build();
            decreaseAnimationImage = new ScaleAnimation.ScaleAnimationBuilder(imgBackground, 1f, 1f).build();
            decreaseAnimationText = new ScaleAnimation.ScaleAnimationBuilder(tvProductTitle, 1f, 1f).build();
            upAnimationImageView = new TranslationAnimation.TranslationAnimationBuilder(cvProductDetails,
                    TranslationAnimation.TranslationMode.TranslationY, 0, -(screenHeight / 2.2f))
                    .build();
            arcUpAnimationTv = new TranslationAnimation.TranslationAnimationBuilder(tvProductTitle,
                    TranslationAnimation.TranslationMode.TranslationAll, 0, screenHeight / 10)
                    .arcMode(TranslationAnimation.ArcMode.ArcUpward)
                    .additionStartPoint(0)
                    .additionEndPoint(screenWidth / 2 - tvProductTitle.getWidth())
                    .build();
            downAnimationImageView = new TranslationAnimation.TranslationAnimationBuilder(cvProductDetails,
                    TranslationAnimation.TranslationMode.TranslationY, -(screenHeight / 3), 0)
                    .build();
            arcDownAnimationTv = new TranslationAnimation.TranslationAnimationBuilder(tvProductTitle,
                    TranslationAnimation.TranslationMode.TranslationAll, screenHeight / 7, 0)
                    .arcMode(TranslationAnimation.ArcMode.ArcDownard)
                    .additionStartPoint(screenWidth / 2 - tvProductTitle.getWidth())
                    .additionEndPoint(0)
                    .build();

            blurAnimation = new BlurAnimation.BlurAnimationBuilder(0.4f, 7f).build();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample_background);
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
