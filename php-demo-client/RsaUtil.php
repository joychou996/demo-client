<?php

class RsaUtil
{


    public  function str2key($str)
    {
        $key   = wordwrap($str, 64, PHP_EOL, true);
        $start = '-----BEGIN PUBLIC KEY-----' . PHP_EOL;
        $end   = PHP_EOL . '-----END PUBLIC KEY-----' . PHP_EOL;
        return $start . $key . $end;
    }

    /**
     * @uses 公钥加密
     * @param string $data
     * @return null|string
     */
    public function publicEncrypt($data,$public_key) {
        if (!is_string($data)) {
            return null;
        }

        $crypto = '';

        foreach (str_split($data, 117) as $chunk) {
            openssl_public_encrypt($chunk, $encrypted, $public_key);
            $crypto .= $encrypted;
        }

        return $crypto ? base64_encode($crypto) : null;
    }

    /**
     * @param string $encrypted
     * @return null
     * @uses 公钥解密
     */
    public function publicDecrypt($encrypted,$public_key)
    {
        if (!is_string($encrypted)) {
            return null;
        }

        $crypto = '';

        foreach (str_split(base64_decode($encrypted), 128) as $chunk) {
            openssl_public_decrypt($chunk, $decrypted, $public_key);
            $crypto .= $decrypted;
        }

        return $crypto ? $crypto : null;
    }
}
