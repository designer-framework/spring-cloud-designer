package org.designer.mybatis.base.service.impl;

import org.designer.mybatis.base.mapper.BaseMapper;
import org.designer.mybatis.base.service.IService;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/5 3:33
 */
public class IServiceImpl<T, E extends BaseMapper<T>> implements IService<T> {

    private Class<T> tClass;

    @Autowired
    private E baseMapper;

    private T toModel(Map<String, Object> map) {
        if (tClass == null) {
            synchronized (this) {
                Type superClass = getClass().getGenericSuperclass();
                tClass = (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
            }
        }
        return null;
    }

    @Override
    public long count(SelectStatementProvider provider) {
        return baseMapper.count(provider);
    }

    @Override
    public T getOne(SelectStatementProvider completer) {
        return baseMapper.selectOne(completer).orElse(null);
    }

    @Override
    public List<T> list(SelectStatementProvider selectStatement) {
        return baseMapper.selectMany(selectStatement, this::toModel);
    }

    @Override
    public int updateSelective(UpdateStatementProvider provider) {
        return baseMapper.update(provider);
    }

    @Override
    public E getBaseMapper() {
        return baseMapper;
    }

}
