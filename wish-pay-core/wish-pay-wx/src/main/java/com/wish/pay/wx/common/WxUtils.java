package com.wish.pay.wx.common;

import com.beust.jcommander.internal.Lists;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.thoughtworks.xstream.XStream;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/13 19:14
 */
public class WxUtils {

    /**
     * md5加密
     *
     * @param dueStr
     * @return
     */
    public static String md5(String dueStr) {
        byte[] md5 = Hashing.md5().hashString(dueStr, Charsets.UTF_8).asBytes();
        /**
         *
         * 二行制转字符串
         */
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < md5.length; n++) {
            stmp = (java.lang.Integer.toHexString(md5[n] & 0XFF));
            if (stmp.length() == 1)
                hs.append("0").append(stmp);
            else
                hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    /**
     * 获取一定长度的随机字符串
     *
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        if (length == 0) {
            length = 32;
        }
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     *
     * @return boolean
     */
    public static boolean isTenpaySign(String characterEncoding, SortedMap<String, String> packageParams,
                                       String API_KEY) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String, String>> es = packageParams.entrySet();
        Iterator<Map.Entry<String, String>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + API_KEY);

        // 算出摘要
        String mysign = md5(sb.toString()).toLowerCase();
        String tenpaySign = ((String) packageParams.get("sign")).toLowerCase();

        // System.out.println(tenpaySign + " " + mysign);
        return tenpaySign.equals(mysign);
    }


    /**
     * @Description：将请求参数转换为xml格式的string
     */
    public static String getRequestXml(SortedMap<String, String> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set<Map.Entry<String, String>> es = parameters.entrySet();
        Iterator<Map.Entry<String, String>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 根据当前时间戳生成yyyyMMddHHmmss日期格式
     *
     * @param time
     * @return
     */
    public static String getDateByLongTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dt = new Date(time);
        String timeStr = sdf.format(dt);
        return timeStr;
    }

    /**
     * 加时间  (单位分钟)
     *
     * @param time
     * @param addMin
     * @return
     */
    public static String addMinTime(long time, String addMin) {
        if (addMin.contains("m")) {
            addMin = addMin.replace("m", "");
        }
        long ad = Long.valueOf(addMin);
        long i = ad * 60 * 1000;
        return getDateByLongTime(time + i);
    }

    /**
     * 签名算法
     *
     * @param o 要参与签名的数据对象
     * @return 签名
     * @throws IllegalAccessException
     */
    public static String getSign(Object o, String key) throws IllegalAccessException {
        List<String> list = Lists.newArrayList();
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.get(o) != null && f.get(o) != "") {
                list.add(f.getName() + "=" + f.get(o) + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        //log.log("Sign Before MD5:" + result);
        result = md5(result).toUpperCase();
        //Util.log("Sign Result:" + result);
        return result;
    }

    public static String getSign(Map<String, Object> map, String key) {
        List<String> list = Lists.newArrayList();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + key;
        //Util.log("Sign Before MD5:" + result);
        result = md5(result).toUpperCase();
        //Util.log("Sign Result:" + result);
        return result;
    }

    /**
     * 获取本机ip地址
     *
     * @return
     */
    public static String getLocalIP() {
        String sIP = "";
        InetAddress ip = null;
        try {
            boolean bFindIP = false;
            Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                if (bFindIP) {
                    break;
                }
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ip = (InetAddress) ips.nextElement();
                    if (!ip.isLoopbackAddress() && ip.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
                        bFindIP = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != ip) {
            sIP = ip.getHostAddress();
        }
        return sIP;
    }

    /**
     * 从xml里获取对象
     *
     * @param xml
     * @param tClass
     * @return
     */
    public static Object getObjectFromXML(String xml, Class tClass) {
        //将从API返回的XML数据映射到Java对象
        XStream xStreamForResponseData = new XStream();
        xStreamForResponseData.alias("xml", tClass);
        xStreamForResponseData.ignoreUnknownElements();//暂时忽略掉一些新增的字段
        return xStreamForResponseData.fromXML(xml);
    }

    /**
     * 通知返回成功xml
     *
     * @return
     */
    public static String notifySuccessXml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<return_code><![CDATA[SUCCESS]]></return_code>");
        sb.append("<return_msg><![CDATA[OK]]></return_msg>");
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 返回错误的xml
     *
     * @return
     */
    public static String notifyFalseXml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        sb.append("<return_code><![CDATA[FAIL]]></return_code>");
        sb.append("<return_msg><![CDATA[OK]]></return_msg>");
        sb.append("</xml>");
        return sb.toString();
    }

}
