package com.qs.mvc.service.impl;

import com.qs.mvc.entity.BaseEntity;
import com.qs.mvc.service.HibernateBaseService;
import com.qs.mvc.service.base.BaseService;
import com.qs.mvc.util.ReflectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    @Resource
    protected HibernateBaseService baseService;

    protected final Class<T> modelClass;

    public BaseServiceImpl(){
        modelClass = ReflectUtil.getGenericParamClass(this.getClass());
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
}