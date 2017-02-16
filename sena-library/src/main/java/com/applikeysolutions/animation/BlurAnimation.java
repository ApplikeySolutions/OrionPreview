package com.applikeysolutions.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

public class BlurAnimation {
    private final float BITMAP_SCALE;
    private final float BLUR_RADIUS;

    private BlurAnimation(BlurAnimation.BlurAnimationBuilder builder) {
        this.BITMAP_SCALE = builder.BITMAP_SCALE;
        this.BLUR_RADIUS = builder.BLUR_RADIUS;
    }

    public Bitmap blur(Context context, Bitmap image) {
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }

    public static class BlurAnimationBuilder {
        private final float BITMAP_SCALE;
        private final float BLUR_RADIUS;

        public BlurAnimationBuilder(float bitmapScale, float blurRadius) {
            this.BITMAP_SCALE = bitmapScale;
            this.BLUR_RADIUS = blurRadius;
        }

        public BlurAnimation build() {
            return new BlurAnimation(this);
        }
    }
}
