package org.designer.mybatis.base.service;

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/5 3:34
 */
public interface BaseService<T> {

    int deleteById(Serializable id_);

    int insert(T record);

    int insertBatch(Collection<T> records);

    int insertSelective(T record);

    Optional<T> selectById(Serializable id_);

    int updateById(T record);

    int updateByIdSelective(T record);

    long count(SelectStatementProvider var1);

    T selectOne(SelectStatementProvider completer);

    List<T> list(SelectStatementProvider selectStatement);

    /**
     * 条件更新
     *
     * @param var1
     * @return
     */
    int updateSelective(UpdateStatementProvider var1);

}
