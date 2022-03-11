package org.designer.mybatis.generator;

import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.runtime.dynamic.sql.DynamicSqlMapperGeneratorV2;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/10 1:13
 */
public class DynamicSqlMapperGeneratorV3 extends DynamicSqlMapperGeneratorV2 {

    public DynamicSqlMapperGeneratorV3(String project) {
        super(project);
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return new BasicXMLMapperGenerator();
    }

}
