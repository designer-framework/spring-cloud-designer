package org.designer.mybatis.base.service.impl;

import org.designer.mybatis.base.mapper.BaseMapper;
import org.designer.mybatis.base.service.IService;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/5 3:33
 */
public class IServiceImpl<I extends Serializable, E, M extends BaseMapper<I, E>> implements IService<I, E> {

    @Autowired
    private M baseMapper;

    @Override
    public M getBaseMapper() {
        return baseMapper;
    }

    @Override
    public long count(CountDSLCompleter completer) {
        return baseMapper.count(completer);
    }

    @Override
    public E getOne(SelectDSLCompleter completer) {
        return baseMapper.selectOne(completer).orElse(null);
    }

    @Override
    public List<E> list(SelectDSLCompleter completer) {
        return baseMapper.select(completer);
    }

    @Override
    public int update(UpdateDSLCompleter completer) {
        return baseMapper.update(completer);
    }

    @Override
    public int remove(DeleteDSLCompleter completer) {
        return baseMapper.delete(completer);
    }

}
