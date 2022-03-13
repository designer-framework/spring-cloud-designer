package org.designer.mybatis.base.service;

import org.designer.mybatis.base.mapper.BaseMapper;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/5 3:34
 */
public interface IService<I extends Serializable, T> {

    BaseMapper<I, T> getBaseMapper();

    @Transactional(rollbackFor = Exception.class)
    default int deleteById(I id_) {
        return getBaseMapper().deleteByPrimaryKey(id_);
    }

    @Transactional(rollbackFor = Exception.class)
    int delete(DeleteDSLCompleter completer);

    @Transactional(rollbackFor = Exception.class)
    default int save(T record) {
        return getBaseMapper().insert(record);
    }

    @Transactional(rollbackFor = Exception.class)
    default int saveBatch(Collection<T> records) {
        return getBaseMapper().insertMultiple(records);
    }

    @Transactional(rollbackFor = Exception.class)
    default int saveSelective(T record) {
        return getBaseMapper().insertSelective(record);
    }

    default Optional<T> getById(I id_) {
        return getBaseMapper().selectByPrimaryKey(id_);
    }

    @Transactional(rollbackFor = Exception.class)
    default int updateById(T record) {
        return getBaseMapper().updateByPrimaryKey(record);
    }

    @Transactional(rollbackFor = Exception.class)
    default int updateByIdSelective(T record) {
        return getBaseMapper().updateByPrimaryKeySelective(record);
    }

    long count(CountDSLCompleter completer);

    T getOne(SelectDSLCompleter completer);

    List<T> list(SelectDSLCompleter completer);

    default List<T> list() {
        return getBaseMapper().select(SelectDSLCompleter.allRows());
    }

    /**
     * 条件更新
     *
     * @param completer
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    int update(UpdateDSLCompleter completer);

    @Transactional(rollbackFor = Exception.class)
    int update(T record, T eqWhere);

}
