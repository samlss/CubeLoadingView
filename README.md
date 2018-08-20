# CubeLoadingView
A cube 3D rotation loading view.

### [中文](https://github.com/samlss/CubeLoadingView/blob/master/README-ZH.md)

 <br/>

[![Api reqeust](https://img.shields.io/badge/api-11+-green.svg)](https://github.com/samlss/CubeLoadingView)  [![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://github.com/samlss/CubeLoadingView/blob/master/LICENSE) [![Blog](https://img.shields.io/badge/samlss-blog-orange.svg)](https://blog.csdn.net/Samlss)


![gif](https://github.com/samlss/CubeLoadingView/blob/master/screenshots/screenshot1.gif)

## English

### Use<br>
Add it in your root build.gradle at the end of repositories：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

Add it in your app build.gradle at the end of repositories:
```
dependencies {
    implementation 'com.github.samlss:CubeLoadingView:1.0'
}
```


in layout.xml：
```
 <com.iigo.library.CubeLoadingView
        app:firstSideColor="@android:color/holo_red_light"
        app:secondSideColor="@android:color/holo_blue_light"
        app:thirdSideColor="@android:color/holo_orange_light"
        app:fourthSideColor="@android:color/holo_green_light"
        android:layout_centerInParent="true"
        android:layout_width="50dp"
        android:layout_height="50dp"/>

```

<br>

in java code：
```
  cubeLoadingView.setFirstSideColor(Color.RED); //set the color of the first side
  cubeLoadingView.setSecondSideColor(Color.RED); //set the color of the second side
  cubeLoadingView.setThirdSideColor(Color.RED); //set the color of the third side
  cubeLoadingView.setFourthSideColor(Color.RED); //set the color of the fourth side
  
  cubeLoadingView.pause(); //pause animation
  cubeLoadingView.resume(); //resume animation
   
  cubeLoadingView.start(); //start animation
  cubeLoadingView.stop(); //stop animation
  cubeLoadingView.release(); //Can 'released' when you don't need to use the loading view, for example in the activity's onDestroy()
```
<br>


Attributes description：

Before describe the attributes, let's look at a attributes piture firstly.

![picture](https://github.com/samlss/CubeLoadingView/blob/master/screenshots/description.png)

| attr        | description  |
| ------------- |:-------------:|
| firstSideColor      | the color of the first side |
| secondSideColor      | the color of the first side |
| thirdSideColor | the color of the first side |
| fourthSideColor | the color of the first side |

<br>


## [LICENSE](https://github.com/samlss/CubeLoadingView/blob/master/LICENSE)
