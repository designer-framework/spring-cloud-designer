package org.designer.mybatis.base.service;

import org.designer.mybatis.base.mapper.BaseMapper;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
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
public interface IService<T> {

    BaseMapper<T> getBaseMapper();

    @Transactional(rollbackFor = Exception.class)
    default int deleteById(Serializable id_) {
        return getBaseMapper().deleteByPrimaryKey(id_);
    }

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

    default Optional<T> getById(Serializable id_) {
        return getBaseMapper().selectByPrimaryKey(id_);
    }

    @Transactional(rollbackFor = Exception.class)
    default int updateById(T record) {
        return getBaseMapper().updateByPrimaryKey(record);
    };

    @Transactional(rollbackFor = Exception.class)
    default int updateByIdSelective(T record) {
        return getBaseMapper().updateByPrimaryKeySelective(record);
    }

    long count(SelectStatementProvider var1);

    T getOne(SelectStatementProvider completer);

    List<T> list(SelectStatementProvider selectStatement);

    default List<T> list() {
        return getBaseMapper().select(SelectDSLCompleter.allRows());
    }

    /**
     * 条件更新
     *
     * @param var1
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    int updateSelective(UpdateStatementProvider var1);

}
