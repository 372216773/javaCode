package com.wj.GoodsShow.Util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String getMD5String (String password) {
        String string=null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //信息摘要
            byte[] digest = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            //
            BigInteger bigInteger = new BigInteger(1,digest);
            string = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return string;
    }

}
