package com.wish.pay.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * json工具类
 *
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/2/27 15:25
 */
public class JsonUtils {


    /**
     * map转换成json String
     *
     * @param map
     * @return
     */
    public static String formateMap(Map map) {
        String result = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        return result;
    }


    /**
     * 把json数据转换成类 T
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 把json数据转换成 list<T> 类型
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> List<T> fromJsonToList(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }


    /**
     * po类转换成json String
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        String result = JSON.toJSONString(obj/*, SerializerFeature.WriteMapNullValue*/);
        return result;
    }
}
