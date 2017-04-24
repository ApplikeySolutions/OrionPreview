[![License](https://img.shields.io/badge/license-Apache--2.0-green.svg)](https://github.com/AppliKeySolutions/Sena/blob/master/LICENSE)

# Sena

A simple animation with translation or scale views.

<img src="screenshots/demo-sena.gif?raw=true" alt="" width="240"/>

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

License
-----

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.