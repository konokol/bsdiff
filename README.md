
## Overview

**bsdiff** and **bspatch** is algorithms used to building and applying patches for binary files. These 
algorithms are developed by Colin Percival. Details are described in his [paper](https://www.daemonology.net/papers/bsdiff.pdf).
along with the source code at his website. For more information, please visit https://www.daemonology.net/bsdiff.

This library is developed over Colin Percival's source code. We wrap the C source code into Android 
library which can be easily used for Android.

## Usage



## Compilation

## Demo Description

This a Demo for Android incremental update. We use BSDiff to generate a patch file, then wen use BSPatch to generate a new apk.

WeChat apk is used to do the test. The old file is WeChat v6.3.25 and the new one is WeChat v6.3.27.

To run this demo, you need to put all the apk included in the 'apk' folder of this repository files in your Android device SDCard root path.

If you run this Demo successfully, you will find a new file named 'weixin_new.apk' in your Android device's root SDCard path. 
This file should be the same with the file named 'weixin6327android880.apk'.