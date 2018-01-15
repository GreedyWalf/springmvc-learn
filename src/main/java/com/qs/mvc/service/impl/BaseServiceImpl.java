package com.qs.mvc.service.impl;

import com.qs.mvc.entity.BaseEntity;
import com.qs.mvc.service.HibernateBaseService;
import com.qs.mvc.service.base.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;

public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    @Resource
    protected HibernateBaseService baseService;

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public String save(T model) {
        Assert.notNull(model, "model is null!");
        String modelId = model.getId();
        if (StringUtils.isBlank(modelId)) {
            return baseService.save(model);
        }

        baseService.update(model);
        baseService.getSession().flush();
        return modelId;
    }
}