package com.anguomob.lib.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {

    private static final String Algorithm = "AES";
    private final static String HEX = "0123456789ABCDEF";
    private final static String key = "abcyy";

    //加密函数，key为密钥
    public static String encrypt(String key, String src) throws Exception {
        byte[] rawKey = getRawKey(key.getBytes());
        byte[] result = encrypt(rawKey, src.getBytes());
        return toHex(result);
    }
    //加密函数，key为密钥
    public static String encrypt(String src) {
        byte[] result = new byte[0];
        try {
            byte[] rawKey = getRawKey(key.getBytes());
            result = encrypt(rawKey, src.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toHex(result);
    }

    //解密函数。key值必须和加密时的key一致
    public static String decrypt(String key, String encrypted) throws Exception {
        byte[] rawKey = getRawKey(key.getBytes());
        byte[] enc = toByte(encrypted);
        byte[] result = decrypt(rawKey, enc);
        return new String(result);
    }    //解密函数。key值必须和加密时的key一致
    public static String decrypt(String encrypted) {
        byte[] result = new byte[0];
        try {
            byte[] rawKey = getRawKey(key.getBytes());
            byte[] enc = toByte(encrypted);
            result = decrypt(rawKey, enc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

    private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(Algorithm);
        // SHA1PRNG 强随机种子算法, 要区别Android 4.2.2以上版本的调用方法
        SecureRandom sr = null;
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
        } else {
            sr = SecureRandom.getInstance("SHA1PRNG");
        }
        sr.setSeed(seed);
        kgen.init(256, sr); // 256位或128位或192位
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();
        return raw;
    }

    private static byte[] encrypt(byte[] key, byte[] src) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, Algorithm);
        Cipher cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(src);
        return encrypted;
    }

    private static byte[] decrypt(byte[] key, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, Algorithm);
        Cipher cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    private static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        }
        return result;
    }

    private static String toHex(byte[] buf) {
        if (buf == null) {
            return "";
        }
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }
}