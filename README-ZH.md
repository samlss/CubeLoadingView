# CubeLoadingView
一个立体3d旋转的loading view

[![Api reqeust](https://img.shields.io/badge/api-11+-green.svg)](https://github.com/samlss/CubeLoadingView)  [![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://github.com/samlss/CubeLoadingView/blob/master/LICENSE) [![Blog](https://img.shields.io/badge/samlss-blog-orange.svg)](https://blog.csdn.net/Samlss)

![gif](https://github.com/samlss/CubeLoadingView/blob/master/screenshots/screenshot1.gif)


### 使用<br>
在根目录的build.gradle添加这一句代码：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

在app目录下的build.gradle添加依赖使用：
```
dependencies {
    implementation 'com.github.samlss:CubeLoadingView:1.0'
}
```

布局中使用：
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

代码中使用：
```
  cubeLoadingView.setFirstSideColor(Color.RED); //设置第一面的颜色 
  cubeLoadingView.setSecondSideColor(Color.RED); //设置第二面的颜色
  cubeLoadingView.setThirdSideColor(Color.RED); //设置第三面的颜色
  cubeLoadingView.setFourthSideColor(Color.RED); //设置第四面的颜色

  cubeLoadingView.pause(); //暂停动画
  cubeLoadingView.resume(); //恢复动画
   
  cubeLoadingView.start(); //开始动画
  cubeLoadingView.stop(); //停止动画
  cubeLoadingView.release(); //不需要使用该loading view的时候可手动释放，例如在activity的ondestroy()中
```

<br>


属性说明：

开始属性说明之前，先看一张图


| 属性        | 说明           |
| ------------- |:-------------:|
| firstSideColor      | 第一面颜色 |
| secondSideColor      | 第二面颜色 |
| thirdSideColor | 第三面颜色 |
| fourthSideColor | 第四面颜色 |


<br>

## [LICENSE](https://github.com/samlss/CubeLoadingView/blob/master/LICENSE)
