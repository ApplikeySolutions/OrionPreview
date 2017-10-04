# Orion
[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/AppliKeySolutions/Orion/blob/master/LICENSE)

Made by [Applikey Solutions](https://applikeysolutions.com)

A simple animation with translation or scale views.

<img src="screenshots/orion_preview.gif?raw=true" alt="" width="640"/>

## Usage
### Scale Animation
```java
ScaleAnimation scaleAnimation =
                        new ScaleAnimation.ScaleAnimationBuilder(view, scaleRate, scaleRate)
                        .build();
scaleAnimation.show();
```
### Translation Animation
```java
TranslationAnimation tanslationAnimation =
                        new TranslationAnimation.TranslationAnimationBuilder(view,
                                               TranslationAnimation.TranslationMode.TranslationAll, startY, endY)
                                               .arcMode(TranslationAnimation.ArcMode.ArcUpward)
                                               .additionStartPoint(startX)
                                               .additionEndPoint(endX)
                                               .build();
tanslationAnimation.show();
```
### Blur Animation
```java
BlurAnimation blurAnimation =
                        new BlurAnimation.BlurAnimationBuilder(bitmapScale, blurRadius)
                        .build();
Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.image);
blurredBitmap = blurAnimation.blur(MainActivity.this, originalBitmap)
```
See [sample](sample/src/main/java/com/applikeysolutions/sample/MainActivity.java).

# License

    MIT License

    Copyright (c) 2017 Applikey Solutions

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

