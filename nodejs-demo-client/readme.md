关键加密参数：

const key: NodeRSA = new NodeRSA(this.publickey, format: "public");
key.setOptions({ encryptionScheme: "pkcs1" });
return key.encrypt(data,
encoding: "base64");


