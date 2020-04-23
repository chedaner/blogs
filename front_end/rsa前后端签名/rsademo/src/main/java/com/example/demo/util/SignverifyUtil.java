package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.*;

import static com.example.demo.util.RsaUtil.get_rsa_key_byte;

public class SignverifyUtil {

    private static Logger logger = LoggerFactory.getLogger(SignverifyUtil.class);


    public static boolean getSignVeryfy(String request) {
        Map<String, Object> Params = JSONObject.parseObject(request);
        String sign = (String) Params.get("sign");
        System.out.println("backEnd_sign="+sign);
        if (sign == null || "".equals(sign)) {
            logger.warn("sign is null");
            return false;
        }
        // 过滤空值、sign
        Map<String, Object> sParaNew = paraFilter(Params);
        // 获取待签名字符串
        String preSignStr = createLinkString(sParaNew);
        System.out.println("backEnd_beforeSignStr="+preSignStr);
        // 获得签名验证结果
        boolean isSign = false;
        try {
            isSign = RsaUtil.verify(preSignStr, get_rsa_key_byte("/rsakey/pub.key"),new BASE64Decoder().decodeBuffer(sign));
            System.out.println("verify_result="+isSign);
        } catch (IOException e) {
            e.printStackTrace();;
        }
        return isSign;
    }


    public static Map<String, Object> paraFilter(Map<String, Object> sArray) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = "";
            if("bizData".equals(key)){
                Map<String, Object> bizDataObj  = JSONObject.parseObject(sArray.get(key).toString());
                value = createBizDataString(bizDataObj);
            }else {
                value = (String) sArray.get(key);
            }
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
            logger.debug(key + "----" + value);
        }
        return result;
    }

    public static String createBizDataString(Map<String, Object> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = (String) params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "__";
            }
        }
        return prestr;
    }

    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, Object> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = (String) params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

}
