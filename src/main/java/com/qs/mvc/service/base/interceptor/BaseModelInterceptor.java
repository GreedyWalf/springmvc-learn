package com.qs.mvc.service.base.interceptor;

import com.qs.mvc.base.context.ExecutionContext;
import com.qs.mvc.entity.BaseEntity;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Date;

public class BaseModelInterceptor extends EmptyInterceptor {

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        return audit(entity, state, propertyNames);
    }


    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        for (int i = 0; i < propertyNames.length; i++) {
            if ("updateTime".equals(propertyNames[i])) {
                currentState[i] = new Date();
            } else if ("updateBy".equals(propertyNames[i])) {
                currentState[i] = ExecutionContext.getUserId();
            }
        }

        return true;
    }


    private boolean audit(Object entity, Object[] state, String[] propertyNames) {
        if (!(entity instanceof BaseEntity)) {
            return false;
        }

        boolean changed = false;
        for (int i = 0; i < propertyNames.length; i++) {
            String propertyName = propertyNames[i];
            if ("createTime".equals(propertyName)) {
                Object currState = state[i];
                if (currState == null) {
                    state[i] = new Date();
                    changed = true;
                }
            } else if ("updateTime".equals(propertyName)) {
                Object currState = state[i];
                if (currState == null) {
                    state[i] = new Date();
                    changed = true;
                }
            } else if ("createBy".equals(propertyName)) {
                Object currState = state[i];
                if (currState == null) {
                    state[i] = ExecutionContext.getUserId();
                    changed = true;
                }
            } else if ("updateBy".equals(propertyName)) {
                Object currState = state[i];
                if (currState == null) {
                    state[i] = ExecutionContext.getUserId();
                    changed = true;
                }
            }
        }

        return changed;
    }
}
