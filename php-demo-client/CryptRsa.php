<?php

class CryptRsa
{
    /**
     * 私钥加密
     */
    public function privEncrypt($content, $privateKey)
    {
        $priKeyId = openssl_pkey_get_private($privateKey);
        $encrypted = '';
        openssl_private_encrypt($content, $encrypted, $priKeyId);

        openssl_free_key($priKeyId);
        return base64_encode($encrypted);
    }

    public function str2key($str)
    {
        $key   = wordwrap($str, 64, PHP_EOL, true);
        $start = '';
        $end   = '';
        $start = '-----BEGIN PUBLIC KEY-----' . PHP_EOL;
        $end   = PHP_EOL . '-----END PUBLIC KEY-----' . PHP_EOL;
        return $start . $key . $end;
    }

    /**
     * 公钥解密
     */
    public function pubDecrypt($content, $rsaPublicKey) {
        $pubKeyId = openssl_pkey_get_public($rsaPublicKey);
        $content = base64_decode($content);
        $decrypted = '';

        openssl_public_decrypt($content, $decrypted, $pubKeyId);

        openssl_free_key($pubKeyId);

        return $decrypted;
    }

    /**
     * 加密
     * @param String $content   待加密内容
     * @param String $path_rsa_public_key   RSA公钥路径(绝对)
     */
    public function encrypt($content, $rsaPublicKey) {
        $pubKeyId = openssl_pkey_get_public($rsaPublicKey);

        $encrypted = '';
        openssl_public_encrypt($content, $encrypted, $pubKeyId);

        openssl_free_key($pubKeyId);

        return base64_encode($encrypted);
    }

    /**
     * 解密
     *
     */
    public function decrypt($content, $rsaPrivateKey) {
        $priKeyId = openssl_pkey_get_private($rsaPrivateKey);
        $content = base64_decode($content);
        $decrypted = '';

        openssl_private_decrypt($content, $decrypted, $priKeyId);

        openssl_free_key($priKeyId);

        return $decrypted;
    }

    /**
     * RSA校验
     */
    public function check($publicKey,$fromSign,$toSign){
        $publicKeyId = openssl_pkey_get_public($publicKey);
        $result = openssl_verify($fromSign, base64_decode($toSign), $publicKeyId);
        openssl_free_key($publicKeyId);

        return $result === 1 ? true : false;
    }


    /**
     * 数据双方约定好规范（一定要约定好，要不能对不上）
     *  比如：数据统一(json+base64)
     *  或者：ksort($signData); -> reset($signData); 排序完后取一个值生成加密串，仅供参考
     */
    public function getRsaSign($signData,$rsaPrivateKey){
        $privKeyId = openssl_pkey_get_private($rsaPrivateKey);
        $signature = '';
        openssl_sign($signData, $signature, $privKeyId);
        openssl_free_key($privKeyId);
        return base64_encode($signature);
    }

}
