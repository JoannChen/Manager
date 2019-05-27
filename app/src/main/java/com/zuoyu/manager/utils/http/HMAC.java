package com.zuoyu.manager.utils.http;

import android.annotation.SuppressLint;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * <pre>
 * Function：HMAC SHA256
 *
 * Created by JoannChen on 16/5/12 10:38.
 * QQ:411083907
 * E-mail:chenyongzuo@51park.com.cn
 * Version Information：V 1.0
 * Copyright Notice：版权所有@北京百会易泊科技有限公司
 * </pre>
 */
public class HMAC {

    @SuppressLint("TrulyRandom")
    public static byte[] initHmacSHA256Key() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    public static byte[] encodeHmacSHA256(byte[] data, byte[] key)
            throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return mac.doFinal(data);
    }
}
