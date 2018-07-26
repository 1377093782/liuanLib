[TOC]



# 说明文档

> 有些代码写第一次 兴致盎然，可再写就索然无味了。

 [CSDN地址](https://mp.csdn.net/postedit/81168513) 

[bintray地址](https://bintray.com/mp624183768/liuan)

为了更好的使用 我已经添加了n多个权限

```
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.WAKE_LOCK"/>

    <!-- um必须的权限 -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />

    <!-- um推荐的权限 -->
    <!-- 添加如下权限，以便使用更多的第三方SDK和更精准的统计数据 -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

### 依赖

```
api 'com.google.firebase:firebase-core:16.0.0'

api 'com.umeng.sdk:common:latest.integration'
api 'com.umeng.sdk:analytics:latest.integration'

api 'com.github.bumptech.glide:glide:4.7.1'
annotationProcessor 'com.github.bumptech.glide:compiler:4.7.1'

api "com.github.bumptech.glide:okhttp3-integration:4.5.0"
```

这些依赖不需要再次添加了

### 目录大纲 

1. JsonUtil
2. NetworkUtil
3. RecordCollection
4. Firebase
5. AppUtils
6. ToastUtil
7. FileUtils
8. HttpUtil
9. StringUtil
10. XMLUtil
11. BitmapUtil
12. IntentUtils
13. OSUtils
14. PhoneUtil
15. ColorsUtil
16. TimeUtils
17. DateUtil
18. PermisstionUtil
19. Common
20. LiuAnUtils
21. GlideUtils
22. ReflectUtil
23. TextUtils
24. MeasureUtil
25. AesUtil
26. SpUtils
27. ExitActivityUtil
28. ScreenUtil
29. ShortCutUtil
30. LogUtils

### 使用方法

项目级 build.gardle->buildscript->repositories

加上jcenter()这个库

moudle级别 build.gradle->android->dependencies

加上 implementation 'com.liuan:common:1.0.1' 这个包

程序最先执行的地方

Application->onCreate() or MainActivity ->onCreate() 下

LiuanUtils.init(this,"")



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