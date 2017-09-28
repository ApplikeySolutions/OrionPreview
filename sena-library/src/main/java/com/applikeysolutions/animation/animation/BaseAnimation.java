package com.applikeysolutions.animation.animation;

public abstract class BaseAnimation {
    public static final long LEAVE_ANIMATION_DURATION = 195L;
    public static final long ENTER_ANIMATION_DURATION = 300L;
    public static final long SMALL_ANIMATION_DOWNARD = 200;
    public static final long SMALL_ANIMATION_DURATION = 200;
    public static final long LARGE_ANIMATION_DURATION = 350L;

    protected abstract void onStartAnimation();
}
