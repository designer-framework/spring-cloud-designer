package org.designer.mybatis.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.designer.mybatis.base.BaseMapper;
import org.designer.mybatis.service.IService;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/5 3:33
 */
public class IServiceImpl<T, E extends BaseMapper<T>> implements IService<T> {

    private Class<T> tClass;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private E baseMapper;

    private T toModel(Map<String, Object> map) {
        if (tClass == null) {
            synchronized (this) {
                Type superClass = getClass().getGenericSuperclass();
                tClass = (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
            }
        }
        return objectMapper.convertValue(map, tClass);
    }

    @Override
    public int deleteById(Serializable id_) {
        return baseMapper.deleteByPrimaryKey(id_);
    }

    @Override
    public int insert(T record) {
        return baseMapper.insert(record);
    }

    @Override
    public int insertBatch(Collection<T> records) {
        return baseMapper.insertMultiple(records);
    }

    @Override
    public int insertSelective(T record) {
        return baseMapper.insertSelective(record);
    }

    @Override
    public Optional<T> selectById(Serializable id_) {
        return baseMapper.selectByPrimaryKey(id_);
    }

    @Override
    public int updateById(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByIdSelective(T record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public long count(SelectStatementProvider provider) {
        return baseMapper.count(provider);
    }

    @Override
    public T selectOne(SelectStatementProvider completer) {
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

}
