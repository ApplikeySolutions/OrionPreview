package com.applikeysolutions.animation.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class ScaleAnimation extends BaseAnimation {
    private final View view;
    private final float valueX;
    private final float valueY;

    private ScaleAnimation(ScaleAnimationBuilder builder) {
        this.view = builder.view;
        this.valueX = builder.valueX;
        this.valueY = builder.valueY;
    }

    @Override
    protected void onStartAnimation() {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", valueX);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", valueY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleDownX).with(scaleDownY);
        animatorSet.setDuration(ENTER_ANIMATION_DURATION);
        animatorSet.start();
    }

    public void showAnimation() {
        onStartAnimation();
    }

    public static class ScaleAnimationBuilder {
        private final View view;
        private final float valueX;
        private final float valueY;

        public ScaleAnimationBuilder(View view, float valueX, float valueY) {
            this.view = view;
            this.valueX = valueX;
            this.valueY = valueY;
        }

        public ScaleAnimation build() {
            return new ScaleAnimation(this);
        }
    }
}
