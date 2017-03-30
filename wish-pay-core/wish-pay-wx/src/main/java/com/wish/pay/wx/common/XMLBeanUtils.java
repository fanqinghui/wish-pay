package com.wish.pay.wx.common;

import com.thoughtworks.xstream.XStream;

import java.util.Map;
import java.util.Iterator;

/**
 * XML与JavaBean相互转换工具类
 */
public final class XMLBeanUtils {
    /**
     * 将Bean转换为XML
     *
     * @param clazzMap 别名-类名映射Map
     * @param bean     要转换为xml的bean对象
     * @return XML字符串
     */
    public static String bean2xml(Map<String, Class> clazzMap, Object bean) {
        XStream xstream = new XStream();
        for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next();
            xstream.alias(m.getKey(), m.getValue());
        }
        String xml = xstream.toXML(bean);
        return xml;
    }

    /**
     * 将XML转换为Bean
     *
     * @param clazzMap 别名-类名映射Map
     * @param xml      要转换为bean对象的xml字符串
     * @return Java Bean对象
     */
    public static Object xml2Bean(Map<String, Class> clazzMap, String xml) {
        XStream xstream = new XStream();
        for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next();
            xstream.alias(m.getKey(), m.getValue());
        }
        Object bean = xstream.fromXML(xml);
        return bean;
    }

    /**
     * 获取XStream对象
     *
     * @param clazzMap 别名-类名映射Map
     * @return XStream对象
     */
    public static XStream getXStreamObject(Map<String, Class> clazzMap) {
        XStream xstream = new XStream();
        for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next();
            xstream.alias(m.getKey(), m.getValue());
        }
        return xstream;
    }
}