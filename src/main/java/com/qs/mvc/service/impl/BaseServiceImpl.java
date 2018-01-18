package com.qs.mvc.service.impl;

import com.qs.mvc.entity.BaseEntity;
import com.qs.mvc.service.HibernateBaseService;
import com.qs.mvc.service.base.BaseService;
import com.qs.mvc.util.HqlBuilder;
import com.qs.mvc.util.ReflectUtil;
import com.qs.mvc.util.SQLConstant;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T>,SQLConstant {
    @Resource
    protected HibernateBaseService baseService;


    protected final Class<T> modelClass;

    //实例化时完成modelClass属性的赋值
    public BaseServiceImpl(){
        modelClass = ReflectUtil.getGenericParamClass(this.getClass());
    }

    //获取实体类名
    protected String getEntityName(){
        return ClassUtils.getShortClassName(modelClass.getName());
    }

    //获取criteria对象
    protected Criteria createCriteria(){
        return baseService.getSession().createCriteria(modelClass);
    }

    //指定实体，实例化获取criteria对象
    protected Criteria createCriteria(Class<T> clazz){
        return baseService.getSession().createCriteria(clazz);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public String save(T model) {
        Assert.notNull(model, "model is null!");
        return baseService.save(model);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public String saveOrUpdate(T model) {
        Assert.notNull(model, "model is null!");
        String modelId = model.getId();
        if (StringUtils.isBlank(modelId)) {
            return baseService.save(model);
        }

        baseService.update(model);
        baseService.getSession().flush();
        return modelId;
    }

    @Override
    @Transactional(readOnly = true)
    public T load(String modelId) {
        Assert.hasText(modelId, "modelId is empty!");
        return baseService.load(modelClass, modelId);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertEntity(String[] fields, String values[], T model) {
        Assert.hasText(model.getId(), "primary key is must not null");
        Assert.noNullElements(fields);
        Assert.noNullElements(values);

        HqlBuilder builder = new HqlBuilder();
        builder.append(INSERT).append(INTO).append(getEntityName());
        builder.append(LEFT_BRACKET);
        for (int i = 0; i < fields.length; i++) {
            builder.append(fields[i]).append(COMMA);
            if (i == fields.length - 1) {
                builder.append(fields[i]);
            }
        }

        builder.append(RIGHT_BRACKET);
        builder.append(VALUES).append(LEFT_BRACKET);
        for (int i = 0; i < values.length; i++) {
            builder.append(values[i]).append(COMMA);
            if (i == values.length - 1) {
                builder.append(values[i]);
            }
        }

        builder.append(RIGHT_BRACKET);

        Query query = baseService.getSession().createQuery(builder.toString());
        return query.executeUpdate();
    }

}