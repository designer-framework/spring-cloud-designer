package org.designer.mybatis.generator;

import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.runtime.dynamic.sql.DynamicSqlMapperGeneratorV2;
import org.mybatis.generator.runtime.dynamic.sql.elements.v2.SelectByPrimaryKeyMethodGeneratorV2;

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
    protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
        super.addSelectByPrimaryKeyMethod(interfaze);

        addSelectByPrimaryKeysMethod(interfaze);
    }

    protected void addSelectByPrimaryKeysMethod(Interface interfaze) {
        SelectByPrimaryKeysMethodGenerator generator = new SelectByPrimaryKeysMethodGenerator.Builder()
                .withContext(context)
                .withFragmentGenerator(fragmentGenerator)
                .withIntrospectedTable(introspectedTable)
                .withTableFieldName(tableFieldName)
                .withRecordType(recordType)
                .build();

        generate(interfaze, generator);
    }

    @Override
    protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
        super.addDeleteByPrimaryKeyMethod(interfaze);

        addDeleteByPrimaryKeysMethod(interfaze);
    }

    protected void addDeleteByPrimaryKeysMethod(Interface interfaze) {
        DeleteByPrimaryKeysMethodGenerator generator = new DeleteByPrimaryKeysMethodGenerator.Builder()
                .withContext(context)
                .withFragmentGenerator(fragmentGenerator)
                .withIntrospectedTable(introspectedTable)
                .withTableFieldName(tableFieldName)
                .withRecordType(recordType)
                .build();

        generate(interfaze, generator);
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return new BasicXMLMapperGenerator();
    }

}
