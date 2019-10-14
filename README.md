[TOC]



# 说明文档

> 有些代码写第一次 兴致盎然，可再写就索然无味了。

 [CSDN地址](https://mp.csdn.net/postedit/81168513) 

[bintray地址](https://bintray.com/mp624183768/liuan)

为了更好的使用 我没有添加任何权限  请按需添加多个权限

```
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.WAKE_LOCK"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>


//友盟
    <!-- 必须的权限 -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <!-- 推荐的权限 -->
    <!-- 添加如下权限，以便使用更多的第三方SDK和更精准的统计数据 -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

### 依赖

```
  implementation fileTree(include: ['*.jar'], dir: 'libs')
    //noinspection GradleCompatible
    implementation 'com.android.support:appcompat-v7:28.0.0'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
    api 'com.google.firebase:firebase-core:16.0.0'
    api 'com.github.bumptech.glide:glide:4.7.1'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.7.1'
    api 'com.jakewharton:butterknife:10.1.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:10.1.0'
    api 'com.google.code.gson:gson:2.8.5'

    api 'com.squareup.okhttp3:okhttp:3.3.1'
```

这些依赖不需要再次添加了

 如果需要跳过这些依赖可以

```
 implementation ('com.liuan:common:1.0.9') {
       exclude group: 'com.android.support', module: 'appcompat-v7'
   }
```



### 目录大纲 

1. JsonUtil---->json 串操作
2. NetworkUtil---->网络状态判断
3. RecordCollection---->日志收集
4. Firebase---->日志收集
5. AppUtils---->获取app的信息 版本号 cpu型号
6. ToastUtil---->吐司
7. FileUtils---->文件的删除 创建 设置sd卡目录
8. HttpUtil---->网络请求get和post 同步和异步
9. LiuHaiUtils---->判断是否是刘海屏
10. XMLUtil---->xml 和 Object类互换
11. BitmapUtil---->从各种格式总获取Bitmap
12. IntentUtils---->页面跳转 按下home键盘
13. OSUtils---->判断厂商小米 魅族 华为-尚未完善
14. PhoneUtil---->发送短信 是否为连击 获取手机型号 获取手机品牌 打开相机 打开相册
15. ColorsUtil---->返回一些十六进制颜色
16. ColorAnimUtils---->颜色变化工具类
17. DateUtil---->时间格式化 年月日
18. PermisstionUtil---->android6.0权限工具类
19. SettingUtils----> 邮箱反馈 version(弹框) google市场五星好评 分享自己的app
20. LiuAnUtils---->初始化上下文
21. GlideUtils---->GlideUtils方法二次封装
22. ReflectUtil---->反射工具类
23. TextUtils---->对文本文件的一些操作
24. MeasureUtil---->设置布局 list gridview 宽高
25. AesUtil---->加密算法
26. SpUtils---->SharedPreferences 操作工具类
27. ExitActivityUtil---->双击退出界面
28. ScreenUtil---->获取屏幕的宽高 px 和dp的转换 获取屏幕截图 包含状态栏和不包含状态栏
29. ShortCutUtil---->桌面快捷方式操作工具类
30. LogUtils---->日志操作工具类
31. ToolbarUtils---->toolbar 操作工具类
32. ListViewAnimUtils---->listView 加载动画和退出动画
33. OKHttpUtils---->okhtttp的封装使用，详情请看实例中的演示

### 使用方法

项目级 build.gardle->buildscript->repositories

加上jcenter()这个库

moudle级别 build.gradle->android->dependencies

加上 implementation 'com.liuan:common:1.0.9' 这个包

程序最先执行的地方

Application->onCreate() or MainActivity ->onCreate() 下

LiuanUtils.init(this)



### 详解



- NetworkUtils 网络工具类的封装
  -  openWirelessSettings   : 打开网络设置界面
  - isConnected            : 判断网络是否连接
  -  isAvailableByPing      : 判断网络是否可用
  -  getDataEnabled         : 判断移动数据是否打开
  -  setDataEnabled         : 打开或关闭移动数据
  -  is4G                   : 判断网络是否是4G
  - getWifiEnabled         : 判断wifi是否打开
  - setWifiEnabled         : 打开或关闭wifi
  - isWifiConnected        : 判断wifi是否连接状态
  - isWifiAvailable        : 判断wifi数据是否可用
  - getNetworkOperatorName : 获取移动网络运营商名称
  - getNetworkType         : 获取当前网络类型
  - getIPAddress           : 获取IP地址
  - getDomainAddress       : 获取域名ip地址



 



### 小技巧

如果你觉得代码也不是很好，或者找到了bug，可以把更好的发我邮箱上1377093782@qq.com 然后把需要的代码 复制到自己需要的地方，只有开源 才能让代码更完善。

### 未来

此程序只是本人花了少许时间弄的 完全达不到要啥有啥的程度，本人后续会继续完善，直到要啥有啥的 咳咳 程度