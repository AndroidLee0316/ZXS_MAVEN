package com.pasc.lib.net;

import android.text.TextUtils;
import android.util.Log;
import java.net.URLEncoder;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 3Des 加密解码工具类
 */
public class DES3Utils {
    private static String baseStr;

    static String BASESTR_BETA =
            "qwertyuiop[]~-!@#$%^&*()_+ASDFGHJKL:ZXCVBNM<>?1234567890QWERTYUIOP{}asdfghjkl;zxcvbnm,./";

    static String BASESTR_PRODUCT =
            "~1234567890-!@#$%^&*()_+qwertyuiop[]asdfghjkl;zxcvbnm,./QWERTYUIOP{}ASDFGHJKL:ZXCVBNM<>?";

    /**
     * Description: 构造方法
     */
    private DES3Utils() {

    }

    /**
     * 3des要求长度
     */
    private static final int DES_LENGTH = 24;

    /**
     * Description: 加密
     */
    public static String encrypt(String content, String desKey) {
        String str = null;
        try {
            if (TextUtils.isEmpty(desKey) || DES_LENGTH != desKey.length()) {
                Log.e("DES3Utils", "3des key's length must be 24");
                return "";
            }
            if (TextUtils.isEmpty(content)) {
                return "";
            }
            SecretKeySpec key = new SecretKeySpec(desKey.getBytes("utf-8"), "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            //			SecretKeySpec key = new SecretKeySpec(desKey.getBytes("utf-8"), "AES");
            //			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
            //			cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptBytes = cipher.doFinal(content.getBytes("utf-8"));
            str = new Base64Encoder().encode(encryptBytes);
        } catch (Exception e) {
            Log.e("DES3Utils", "加密失败!");
        }
        return str;
    }

    public static String encryptStarUser(String content, String desKey) {
        String str = null;
        try {
            if (TextUtils.isEmpty(desKey) || DES_LENGTH != desKey.length()) {
                Log.e("DES3Utils", "3des key's length must be 24");
                return "";
            }
            if (TextUtils.isEmpty(content)) {
                return "";
            }
            SecretKeySpec key = new SecretKeySpec(desKey.getBytes("utf-8"), "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            //			SecretKeySpec key = new SecretKeySpec(desKey.getBytes("utf-8"), "AES");
            //			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
            //			cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptBytes = cipher.doFinal(content.getBytes("utf-8"));
            str = URLEncoder.encode(new Base64Encoder().encode(encryptBytes));
        } catch (Exception e) {
            Log.e("DES3Utils", "加密失败!");
        }
        return str;
    }

    ///**
    // * Description: 解密
    // *
    // * @param content
    // * @param desKey
    // * @return
    // */
    //public static String decrypt(String content, String desKey) {
    //	try {
    //		if(StringUtil.isEmpty(desKey) || DES_LENGTH != desKey.length()) {
    //			log.error("3des key's length must be 24");
    //			return "";
    //		}
    //		if(StringUtil.isEmpty(content)) {
    //			return "";
    //		}
    //
    //		byte[] decryptBytes = null;
    //		decryptBytes = new BASE64Decoder().decodeBuffer(content);
    //		// decryptBytes = new
    //		// BASE64Decoder().decodeBuffer(decryptBytes.toString());
    //		SecretKeySpec key = new SecretKeySpec(desKey.getBytes("utf-8"), "DESede");
    //		Cipher cipher = Cipher.getInstance("DESede");
    //		cipher.init(Cipher.DECRYPT_MODE, key);
    //
    //		return new String(cipher.doFinal(decryptBytes), "utf-8");
    //	}catch (Exception e) {
    //		log.error("解密失败！", e);
    //	}
    //	return null;
    //}

    public static String get3DESSecretKey() {

        // 这个后续用配置文件或在数据库里面存
        baseStr = BuildConfig.DEBUG ? BASESTR_BETA:BASESTR_PRODUCT;
        //baseStr = BuildConfig.PRODUCT_MODE ? BASESTR_PRODUCT : BASESTR_BETA;
        StringBuffer str = new StringBuffer();
        str.append(baseStr.substring(1, 4));
        str.append(baseStr.substring(60, 65));
        str.append(baseStr.substring(15, 19));
        str.append(baseStr.substring(5, 6));
        str.append(baseStr.substring(32, 37));
        str.append(baseStr.substring(71, 73));
        str.append(baseStr.substring(25, 28));
        str.append(baseStr.substring(40, 41));

        return str.toString();
    }
}