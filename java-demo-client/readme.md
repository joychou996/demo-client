# Demo使用说明

## 1、Clone代码

在Idea中打开，在applicaton.yaml文件中修改

商户编码（custCode）、公钥信息（publicKey）以及服务地址

商户编码和公钥信息我们会下发

如果有变动上述信息，我们会通知

## 2、代码说明

核心就看代码 OrderService.java

### 创建充值订单

url:  api地址/order/create

type:POST

data:

```json
{
 "data":"加密字符串”,
 "custCode":"商户编码"
}
```

采用公钥加密

未加前的数据为：

```json
{
"custCode":"商户编码",
"custOrderId":"商户订单ID",
"unit":"订单金额单位 USD"，
"orderAmount":"订单金额"
}
```

响应数据：

```json
{
code: 200, //200表示正确响应
msg:"消息",
data: {
    orderId:"系统订单ID",
    custCode:"商户编码",
    custOrderId:"商户订单编号",
    createAt:"创建时间",
    unit:"订单金额单位 USD",
    orderAmount:"订单金额"
    payUrl:"支付链接"
  }
}
```

拿到正确数据后，直接打开支付链接即可

### 充值回调接口

系统在得知用户支付成功后，会回调你的接口通知你

该接口的调用方式 POST

接收的数据：

```json
{
 "data":"加密字符串”,
}
```

用Rsa公钥解密

解密后的数据为：

```json
{
    orderId:"系统订单ID",
    custOrderId:"商户订单编号",
    unit:"订单金额单位USD",
    orderAmount:"订单金额",
    payName:"付款人",
    payCashTag:"付款标签",
    cashId:"Cash交易ID"
}
```

响应的内容

```json
{
    code:200, #200表示成功
    data:true,
    msg:"错误消息"
}
```

**说明：为了保证接口幂等性，如果订单已经处理，也请直接返回成功**



### 创建代付订单

url:  api地址/behalfpay/create

type:POST

data:

```json
{
 "data":"加密字符串”,
 "custCode":"商户编码"
}
```

采用公钥加密

未加前的数据为：

```json
{
"custCode":"商户编码",
"custOrderId":"商户订单ID",
"custCashTag":"收款的用户标签",
"behalfpayAmount":"订单金额"
}
```

响应数据：

```json
{
code: 200, //200表示正确响应
msg:"消息",
data: {
    orderId:"系统订单ID",
    custCode:"商户编码",
    custOrderId:"商户订单编号",
    createAt:"创建时间",
    behalfpayAmount:"订单金额"
  }
}
```



### 代付回调接口

系统在代付支付成功后，会回调你的接口通知你

该接口的调用方式 POST

接收的数据：

```json
{
  "data":"加密字符串”,
}
```

用Rsa公钥解密

解密后的数据为：

```json
{
    orderId:"系统订单ID",
    custOrderId:"商户订单编号",
    isPass:"是否成功",
    noPassReason："不成功原因",
    orderAmount:"订单金额",
    payTime:"支付时间"
}
```

响应的内容

```json
{
    code:200, #200表示成功
    data:true,
    msg:"错误消息"
}
```

**说明：为了保证接口幂等性，如果订单已经处理，也请直接返回成功**

## 3、移植代码到你业务系统中





