package org.designer.mybatis.base.mapper;

import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
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
public interface BaseMapper<I extends Serializable, T> extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<T>, CommonSelectMapper,
        CommonUpdateMapper {

    long count(CountDSLCompleter completer);

    int delete(DeleteDSLCompleter completer);

    int deleteByPrimaryKey(I id_);

    int deleteBatchByPrimaryKeys(Collection<I> ids_);

    int insert(T record);

    int insertMultiple(Collection<T> records);

    int insertSelective(T record);

    Optional<T> selectOne(SelectDSLCompleter completer);

    List<T> select(SelectDSLCompleter completer);

    List<T> selectDistinct(SelectDSLCompleter completer);

    Optional<T> selectByPrimaryKey(I id_);

    List<T> selectBatchPrimaryKeys(Collection<I> ids);

    int update(UpdateDSLCompleter completer);

    UpdateDSL<UpdateModel> updateAllColumns(T record, UpdateDSL<UpdateModel> dsl);

    UpdateDSL<UpdateModel> updateSelectiveColumns(T record, UpdateDSL<UpdateModel> dsl);

    int updateByPrimaryKey(T record);

    int updateByPrimaryKeySelective(T record);

}
