package org.designer.mybatis.base.mapper;

import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.util.mybatis3.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/5 12:33
 */
public interface BaseMapper<T> extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<T>, CommonSelectMapper,
        CommonUpdateMapper {

    int delete(DeleteDSLCompleter completer);

    int deleteByPrimaryKey(Serializable id_);

    int insert(T record);

    int insertMultiple(Collection<T> records);

    int insertSelective(T record);

    Optional<T> selectOne(SelectStatementProvider selectStatement);

    List<T> select(SelectDSLCompleter completer);

    List<T> selectDistinct(SelectDSLCompleter completer);

    Optional<T> selectByPrimaryKey(Serializable id_);

    int update(UpdateDSLCompleter completer);

    int updateByPrimaryKey(T record);

    int updateByPrimaryKeySelective(T record);

}
