package com.applikeysolutions.animation.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;

public class TranslationAnimation extends BaseAnimation {
    private View view;
    private final TranslationMode mode;
    private final ArcMode arcMode;
    private final float startPoint;
    private final float endPoint;
    private final float additionStartPoint;
    private final float additionEndPoint;

    public enum TranslationMode {
        TranslationY,
        TranslationX,
        TranslationAll
    }

    public enum ArcMode {
        ArcUpward,
        ArcDownard
    }

    private TranslationAnimation(TranslationAnimationBuilder builder) {
        this.view = builder.view;
        this.mode = builder.mode;
        this.arcMode = builder.arcMode;
        this.startPoint = builder.startPoint;
        this.endPoint = builder.endPoint;
        this.additionStartPoint = builder.additionStartPoint;
        this.additionEndPoint = builder.additionEndPoint;
    }

    public void showAnimation() {
        onStartAnimation();
    }

    @Override
    protected void onStartAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        if (mode.equals(TranslationMode.TranslationAll)) {
            ObjectAnimator animatorTranslationY = ObjectAnimator.ofFloat(view, "translationY",
                    startPoint, endPoint);
            ObjectAnimator animatorTranslationX = ObjectAnimator.ofFloat(view, "translationX",
                    additionStartPoint, additionEndPoint);

            if (arcMode.equals(ArcMode.ArcUpward)) {
                animatorTranslationY.setDuration(SMALL_ANIMATION_DURATION);
                animatorTranslationX.setDuration(LARGE_ANIMATION_DURATION);
            } else {
                animatorTranslationY.setDuration(LARGE_ANIMATION_DURATION);
                animatorTranslationX.setDuration(SMALL_ANIMATION_DURATION);
            }

            animatorSet.setInterpolator(new LinearOutSlowInInterpolator());
            animatorSet.play(animatorTranslationY).with(animatorTranslationX);
            animatorSet.start();
        } else {
            final ValueAnimator valueAnimator = ValueAnimator.ofFloat(startPoint, endPoint);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (float) valueAnimator.getAnimatedValue();
                    switch (mode) {
                        case TranslationY:
                            view.setTranslationY(value);
                            break;
                        case TranslationX:
                            view.setTranslationX(value);
                            break;
                    }
                }
            });

            valueAnimator.setInterpolator(new FastOutSlowInInterpolator());
            valueAnimator.setDuration(LARGE_ANIMATION_DURATION);
            valueAnimator.start();
        }
    }

    public static class TranslationAnimationBuilder {
        private final View view;
        private final TranslationMode mode;
        private ArcMode arcMode;
        private final float startPoint;
        private final float endPoint;
        private float additionStartPoint;
        private float additionEndPoint;

        public TranslationAnimationBuilder(View view, TranslationMode mode, float startPoint, float endPoint) {
            this.view = view;
            this.mode = mode;
            this.startPoint = startPoint;
            this.endPoint = endPoint;
        }

        public TranslationAnimationBuilder arcMode(ArcMode arcMode) {
            this.arcMode = arcMode;
            return this;
        }

        public TranslationAnimationBuilder additionStartPoint(float additionStartPoint) {
            this.additionStartPoint = additionStartPoint;
            return this;
        }

        public TranslationAnimationBuilder additionEndPoint(float additionEndPoint) {
            this.additionEndPoint = additionEndPoint;
            return this;
        }

        public TranslationAnimation build() {
            return new TranslationAnimation(this);
        }
    }
}
