package com.qs.mvc.service.base;

import com.qs.mvc.entity.BaseEntity;

/**
 * 这个接口抽象出一些常用服务的通用方法。可以被其它服务接口集成。
 *
 * @param <T> 要操作的实体类。
 */
public interface BaseService<T extends BaseEntity>  {

    /**
     * 保存实体到数据库中，如果没有设置主键，则使用uuid生成主键；（插入操作,主键由hibernate uuid生成）
     *
     * @param model 要保存的实体。
     * @return 保存实体的主键。
     */
    String save(T model);

    /**
     * 根据传入的实体是否主键被设置来判断是执行插入（未设置主键插入，设置主键更新）
     * 或者更新操作（主键被调用方设置了）
     *
     * @param model 需要执行插入或更新的实体
     * @return 保存或更新的实体主键。
     */
    String saveOrUpdate(T model);

    /**
     * 该方法用于根据实体主键获取实体类
     * <p>load Hibernate load</p>
     *
     * @param modelId 实体主键
     * @return 对应实体T
     */
    T load(String modelId);

    int insertEntity(String[] fields, String values[], T model);
}
