package com.wish.pay.ali;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayRequest;
import com.alipay.api.AlipayResponse;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.StringUtils;
import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Maps;
import com.wish.pay.ali.common.AliPayConstants;
import com.wish.pay.common.model.result.PayResult;
import com.wish.pay.common.utils.Base64;
import org.apache.http.HttpServerConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

import static org.apache.commons.codec.CharEncoding.UTF_8;

/**
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/1 17:10
 */
public class AliPayUtil {

    static Logger log = LoggerFactory.getLogger(AliPayUtil.class);


    // 调用AlipayClient的execute方法，进行远程调用
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected static AlipayResponse getResponse(AlipayClient client, AlipayRequest request) {
        try {
            AlipayResponse response = client.execute(request);
            if (response != null) {
                log.info(response.getBody());
            }
            return response;

        } catch (AlipayApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取阿里支付链接的所有参数列表
     *
     * @param request
     * @return
     */
    public static Map<String, String> getAlipayNotify(HttpServletRequest request) {
        Map<String, String> params = Maps.newHashMap();
        Map requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        return params;
    }


    /**
     * 把数组所有元素按照字母顺序排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * <p>
     * 第一步： 在通知返回参数列表中，除去sign、sign_type两个参数外，凡是通知返回回来的参数皆是待验签的参数。
     * 第二步：将剩下参数进行url_decode, 然后进行字典排序，组成字符串，得到待签名字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     * @Link 异步返回结果的验签：https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.OSyDs4&treeId=194&articleId=103296&docType=1#s1
     */
    public static String createStringUrl(Map<String, String> params) {

        List<String> keys = Lists.newArrayList(params.keySet());
        Collections.sort(keys);

        StringBuffer prestr = new StringBuffer();

        int keySize = keys.size();
        int lastKeyLength = keySize - 1;
        for (int i = 0; i < keySize; i++) {
            String key = keys.get(i);
           /* if (*//*key.equals("sign") ||*//* key.equals("sign_type")) {//除去sign、sign_type两个参数
                continue;
            }*/

            String value = params.get(key);
            if (i == lastKeyLength) {//拼接时，不包括最后一个&字符
                prestr.append(key).append("=").append(value);
            } else {
                prestr.append(key).append("=").append(value).append("&");
            }
        }

        return prestr.toString();
    }

    /**
     * RSA验签名检查
     * 第三步： 将签名参数（sign）使用base64解码为字节码串。
     * 第四步 使用RSA的验签方法，通过签名字符串、签名参数（经过base64解码）及支付宝公钥验证签名。
     *
     * @param content        待签名数据
     * @param sign           签名值
     * @param ali_public_key 支付宝公钥
     * @param input_charset  编码格式
     * @return 布尔值
     * @Link 异步返回结果的验签：https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.OSyDs4&treeId=194&articleId=103296&docType=1#s1
     */
    public static boolean verify(String content, String sign, String ali_public_key, String input_charset) {
        try {
            /*PublicKey e = getPublicKeyFromX509("RSA", new ByteArrayInputStream(publicKey.getBytes()));
            Signature signature = Signature.getInstance("SHA256WithRSA");
            signature.initVerify(e);
            if(StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            return signature.verify(com.alipay.api.internal.util.codec.Base64.decodeBase64(sign.getBytes()));*/
            KeyFactory keyFactory = KeyFactory.getInstance(AliPayConstants.ALIPAY_SIGN_TYPE);
            byte[] encodedKey = Base64.decode(ali_public_key);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            java.security.Signature signature = java.security.Signature.getInstance(AliPayConstants.SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes(input_charset));

            boolean bverify = signature.verify(Base64.decode(sign));
            return bverify;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 返回验签结果
     *
     * @param params
     * @param aliPublicKey
     * @return
     */
    public static boolean verifySignWithRSA(Map<String, String> params, String aliPublicKey) {
       /* String content = createStringUrl(params);
        String sign = params.get("sign");*/
        String sign_type = params.get("sign_type");
        try {
            return  AlipaySignature.rsaCheckV1(params, aliPublicKey, "UTF-8", sign_type);
            //return AlipaySignature.rsaCheckV2(params, aliPublicKey, UTF_8);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return false;
        }
        //String sign_type = params.get("sign_type");
       /* try {
            AlipaySignature.rsaSign(content,aliPublicKey,"UTF-8",sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return verify(content, sign, aliPublicKey, "UTF-8");*/
    }

}
