<?php
include_once "functions.php";
include_once "RsaUtil.php";
// 公钥
$public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCiuvyiDU8rtL0bTnvkGrQGicuJBlprcHfS72eLCYKg8sQUZuSzLyiYHGCdASMoTbBqS9wdntFUgCmkMSu+oko5fto6NdA8UW+WCBStScHMbfcRIkvNMwESKxDyXf2ctb3qoraZmmolGnuAx9lqj00RD5mZyGMOtgvlqVvuWQl6YwIDAQAB";

$jsonData = file_get_contents("php://input");

$res_data = [
    'code' => 200,
    'data' => true,
    'msg' => 'success'
];
if($jsonData){
    $jsonData = json_decode($jsonData, true);
    $data = $jsonData['data'] ?? '';

    $rsa = new RsaUtil();
    $res =$rsa->publicDecrypt($data, $rsa->str2key($public_key));
    $res_data['msg'] = $res;
}else{
    $res_data['code'] = 500;
    $res_data['data'] = false;
    $res_data['msg'] = '数据解密失败';
}

echo json_encode($res_data);
die;
