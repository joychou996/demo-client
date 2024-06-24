package com.example.democlient.utils;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

public class RsaUtils {

    /**
     * 公钥解密
     *
     * @param data
     * @param publicKey
     * @return
     */
    public static String DecryptByPublicKey(String data, String publicKey) {
        RSA rsa = new RSA(null, publicKey);
        String decrypt = rsa.decryptStr(data, KeyType.PublicKey);
        return decrypt;
    }


    /**
     * 公钥加密
     *
     * @param data
     * @param publicKey
     * @return
     */
    public static String EncryptByPublicKey(String data, String publicKey) {
        RSA rsa = new RSA(null, publicKey);
        String result = rsa.encryptBase64(data, KeyType.PublicKey);
        return result;
    }
}
