package com.qs.mvc.service.base;

import com.qs.mvc.entity.BaseEntity;

/**
 * 这个接口抽象出一些常用服务的通用方法。可以被其它服务接口集成。
 *
 * @param <T> 要操作的实体类。
 */
public interface BaseService<T extends BaseEntity>  {

    /**
     * 保存实体到数据库中
     *
     * <p>如果主键未设置，则会由配置的UUID主键生成策略，保存数据后，返回生成的主键</p>
     * <p>如果设置了主键，hibernate设置的主键策略，保存在数据库的实体数据中的主键为生成的主键，
     * 返回的主键为hibernate自动生成的，而非自己设置的主键</p>
     *
     * @param model 要保存的实体。
     * @return 保存实体的主键。
     */
    String save(T model);

    /**
     * 根据实体的主键，更新该实体数据；（必须设置实体的主键，如果根据主键在数据库中查询不到数据，则会抛出异常）
     *
     * @param model 待更新的实体
     */
    void update(T model);

    /**
     *  将指定fields字段值更新为指定values，根据实体主键更新
     *
     * @param fields 需要更新的字段
     * @param values 更新字段对应的值
     * @param model 更新的实体（实体主键，作为更新的条件）
     * @return 更新受影响的行数
     */
    int update(String[] fields, Object[] values, T model);

    /**
     *  将指定field字段值更新为指定value，根据实体主键更新
     *
     * @param fields 需要更新的字段
     * @param values 更新字段对应的值
     * @param model 更新的实体（实体主键，作为更新的条件）
     * @return 更新受影响的行数
     */
    int update(String fields, Object values, T model);

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
}
