package com.wish.pay.web.dao;

import org.springframework.stereotype.Repository;

/**
 * @author fqh
 * @ClassName: MybatisBaseDao
 * @Description: Mybatis基础Dao
 * @date 2015年12月14日
 */
@Repository
public interface MybatisBaseDao<KEY, T> {

    /**
     * 根据主键删除实体
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(KEY id);

    /**
     * 插入对象-返回主键
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 有选择性的插入对象
     *
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 根据主键查找实体
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(KEY id);

    /**
     * 有选择的更新实体
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * 根据主键更新实体
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);
}