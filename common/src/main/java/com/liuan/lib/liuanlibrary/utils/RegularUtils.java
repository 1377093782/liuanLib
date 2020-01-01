package com.liuan.lib.liuanlibrary.utils;

import android.text.TextUtils;

public class RegularUtils {
    //    手机密码 字符 字母 数字 任意两种 6-16位数
    public static boolean validatePassWord(String pass) {
        String passRegex = "((?=.*\\d)(?=.*\\D)|(?=.*[a-zA-Z])(?=.*[^a-zA-Z]))^.{6,16}$";
        return !TextUtils.isEmpty(pass) && pass.matches(passRegex);
    }

    //手机号正则
    public static boolean validatePhone(String phone) {
        String passRegex = "^1\\d{10}$";
        return !TextUtils.isEmpty(phone) && phone.matches(passRegex);
    }

    //邮箱正则
    public static boolean validateEmail(String email) {
        String passRegex = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        return !TextUtils.isEmpty(email) && email.matches(passRegex);
    }
    // 帐号正则  用户名（4到16位，字母数字）：
    public static boolean validateUserName(String uaserName) {
        String passRegex = "/^[a-zA-Z0-9]{4,16}$/";
        return !TextUtils.isEmpty(uaserName) && uaserName.matches(passRegex);
    }
    // 身份证：
    public static boolean validateIdcard(String idcard) {
        String passRegex = " /^\\d{17}(\\d|x|X)$/";
        return !TextUtils.isEmpty(idcard) && idcard.matches(passRegex);
    }
    //   复杂密码（最少6位，包括至少一位大写字母，一位小写字母，一个数字，一个特殊字符）：
    public static boolean validateComplexPassword(String idcard) {
        String passRegex = "  /(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[$@!%*#?&])[A-Za-z\\d$@!%*#?&]{6,}$/\n";
        return !TextUtils.isEmpty(idcard) && idcard.matches(passRegex);
    }

}
