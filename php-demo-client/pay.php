<?php
include_once "functions.php";
include_once "RsaUtil.php";

// 公钥
$public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiuvyiDU8rtL0bTnvkGrQGicuJBlprcHfS72eLCYKg8sQUZuSzLyiYHGCdASMoTbBqS9wdntFUgCmkMSu+oko5fto6NdA8UW+WCBStScHMbfcRIkvNMwESKxDyXf2ctb3qoraZmmolGnuAx9lqj00RD5mZyGMOtgvlqVvuWQl6YwIDAQAB";
$sh = "111";  // 商户名称
$sh_code = "SM6ML9Y8AP29XIWD5B5J";  // 商户编码

$url = "http://localhost:8080/order/create";

$money = $_POST['money'];

$data = [
    'custCode' => $sh_code
];

$sign_data = [
    "custCode" => $sh_code,
    "custOrderId" => date('YmdHis') . rand(1000, 9999),
    "unit" => 'USD',
    "orderAmount" => $money,
];
$rsa = new RsaUtil();
$sign = $rsa->publicEncrypt(json_encode($sign_data), $rsa->str2key($public_key));
$data['data'] = $sign;
// var_dump($data);die;
$res = post($url, json_encode($data));

if(isset($res['code']) && $res['code'] == 200){
    $url = $res['data']['payUrl'] ?? '';
    $to_url = 'Location: ' . $url;
    header($to_url);
    die;
}else{
    echo 'pay failed';
};
