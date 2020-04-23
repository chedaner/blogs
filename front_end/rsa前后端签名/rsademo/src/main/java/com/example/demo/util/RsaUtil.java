package com.example.demo.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * RSA工具类
 * 
 */
public class RsaUtil {

    /** 数字签名密钥算法 */
    public static final String KEY_ALGORITHM = "RSA";
    /** 数字名签名/验证算法 */
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    /** RSA密钥长度 */
    public static final int KEY_SIZE = 2048;
    public static final String PUBLIC_KEY = "PUBLIC_KEY";
    public static final String PRIVATE_KEY = "PRIVATE_KEY";

    /**
     * 使用SHA256withRSA进行签名(签名结果表示成16进制字符串)
     * 
     * @param sourceStr 需要签名的原始字符串
     * @param privateKeyStr RSA私钥(16进制字符串表示)
     * @return
     */
    public static String sign(String sourceStr, String privateKeyStr, String charset) {
        try {
            byte[] sourceData = null;
            if (charset != null && !"".equals(charset)) {
                sourceData = sourceStr.getBytes(charset);
            } else {
                sourceData = sourceStr.getBytes();
            }
            byte[] privateKey = Hex.decodeHex(privateKeyStr.toCharArray());
            // 转换私钥材料
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
            // 实例化密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 实例化Signature
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            // 初始化Signature
            signature.initSign(priKey);
            // 更新
            signature.update(sourceData);
            // 签名
            byte[] result = signature.sign();
            return Hex.encodeHexString(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SHA256withRSA进行验签
     * 
     * @param sourceStr 需要签名的原始字符串
     * @param sign 签名(16进制字符串表示)
     * @param publicKey RSA公钥(16进制字符串表示)
     * @return
     */
    public static boolean verify(String sourceStr, String sign, String publicKey, String charset) {
        try {
            byte[] sourceData = null;
            if (charset != null && !"".equals(charset)) {
                sourceData = sourceStr.getBytes(charset);
            } else {
                sourceData = sourceStr.getBytes();
            }
            byte[] publicKeyBytes = Hex.decodeHex(publicKey.toCharArray());
            byte[] signBytes = Hex.decodeHex(sign.toCharArray());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            // 实例化Signature
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            // 初始化Signature
            signature.initVerify(pubKey);
            // 加载数据
            signature.update(sourceData);
            // 验证
            return signature.verify(signBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean verify(String data, byte[] publicKey,
                                 byte[] signatureResult) {
        try {
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
                    publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey2 = keyFactory
                    .generatePublic(x509EncodedKeySpec);

            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey2);
            signature.update(data.getBytes());

            return signature.verify(signatureResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Map<String, String> generateKeyPair() {
        Map<String, String> keyMap = new HashMap<String, String>();
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            keyMap.put(PUBLIC_KEY, Hex.encodeHexString(keyPair.getPublic().getEncoded()));
            keyMap.put(PRIVATE_KEY, Hex.encodeHexString(keyPair.getPrivate().getEncoded()));
            return keyMap;
        } catch (Throwable e) {

        }
        return keyMap;
    }


    public static byte[] get_rsa_key_byte(String rsaKeyFileName) throws IOException {
        InputStream dataFile = RsaUtil.class.getResourceAsStream(rsaKeyFileName);
//        InputStream dataFile = new FileInputStream(rsaKeyFileName);
        List<String> privateKeyLines = IOUtils.readLines(dataFile, "utf-8");
        List<String> lines = privateKeyLines.subList(1, privateKeyLines.size()-1);
        String privateKeys = StringUtils.join(lines);
        byte[] decodedKeys = new Base64().decode(privateKeys);
        return decodedKeys;
    }

    public static void main(String[] args){
    	try {
            String sourceStr = "test";
            String sign = "testsign";
            boolean flag = verify(sourceStr, get_rsa_key_byte("/rsakey/pub.key"),new BASE64Decoder().decodeBuffer(sign));
            System.out.println(flag);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
