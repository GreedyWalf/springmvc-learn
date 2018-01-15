package com.qs.mvc.service.base;

import com.qs.mvc.entity.BaseEntity;

/**
 * 这个接口抽象出一些常用服务的通用方法。可以被其它服务接口集成。
 *
 * @param <T> 要操作的实体类。
 */
public interface BaseService<T extends BaseEntity>  {

    /**
     * 根据传入的实体是否主键被设置来判断是执行插入（主键未设置）
     * 或者更新操作（主键被调用方设置了）
     *
     * @param model 要保存的实体。
     * @return 保存实体的主键。
     */
    String save(T model);
}
