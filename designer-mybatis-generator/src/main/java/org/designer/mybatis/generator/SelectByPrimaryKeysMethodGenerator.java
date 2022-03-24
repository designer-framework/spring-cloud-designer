package org.designer.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.runtime.dynamic.sql.elements.AbstractMethodGenerator;
import org.mybatis.generator.runtime.dynamic.sql.elements.FragmentGenerator;
import org.mybatis.generator.runtime.dynamic.sql.elements.MethodAndImports;
import org.mybatis.generator.runtime.dynamic.sql.elements.MethodParts;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/22 1:25
 */
public class SelectByPrimaryKeysMethodGenerator extends AbstractMethodGenerator {

    private FullyQualifiedJavaType recordType;
    private FragmentGenerator fragmentGenerator;

    private SelectByPrimaryKeysMethodGenerator(SelectByPrimaryKeysMethodGenerator.Builder builder) {
        super(builder);
        recordType = builder.recordType;
        fragmentGenerator = builder.fragmentGenerator;
    }

    protected SelectByPrimaryKeysMethodGenerator(BaseBuilder<?, ?> builder) {
        super(builder);
    }

    @Override
    public MethodAndImports generateMethodAndImports() {
        Set<FullyQualifiedJavaType> imports = new HashSet<>();

        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("java.util.List");
        returnType.addTypeArgument(recordType);
        imports.add(returnType);

        Method method = new Method("selectBatchPrimaryKeys");
        method.setDefault(true);
        context.getCommentGenerator().addGeneralMethodAnnotation(method, introspectedTable, imports);
        method.setReturnType(returnType);

        method.addBodyLine("return selectMany(");

        MethodAndImports.Builder builder = MethodAndImports.withMethod(method)
                .withStaticImport("org.mybatis.dynamic.sql.SqlBuilder.*")
                .withImports(imports);

        acceptParts(builder, method, getPrimaryKeysWhereClauseAndParametersV2());

        return builder.build();
    }

    public MethodParts getPrimaryKeysWhereClauseAndParametersV2() {
        MethodParts.Builder builder = new MethodParts.Builder();

        boolean first = true;
        for (IntrospectedColumn column : introspectedTable.getPrimaryKeyColumns()) {
            builder.withImport(column.getFullyQualifiedJavaType());
            builder.withImport(new FullyQualifiedJavaType("org.mybatis.dynamic.sql.render.RenderingStrategies"));

            FullyQualifiedJavaType listParameter = new FullyQualifiedJavaType("java.util.Collection");
            listParameter.addTypeArgument(column.getFullyQualifiedJavaType());

            builder.withParameter(new Parameter(
                    listParameter, column.getJavaProperty() + "s"));

            String fieldName = AbstractMethodGenerator.calculateFieldName(tableFieldName, column);

            if (first) {
                builder.withBodyLine("    org.mybatis.dynamic.sql.SqlBuilder.select(selectList)");
                builder.withBodyLine("        .from(" + tableFieldName + ")");
                builder.withBodyLine("        .where(" + fieldName
                        + ", isIn(" + column.getJavaProperty()
                        + "s))");
                first = false;
            } else {
                builder.withBodyLine("    .or(" + fieldName
                        + ", isIn(" + column.getJavaProperty()
                        + "s_))");
            }
        }
        builder.withBodyLine("        .build()");
        builder.withBodyLine("        .render(RenderingStrategies.MYBATIS3)");
        builder.withBodyLine(");");

        return builder.build();
    }

    @Override
    public boolean callPlugins(Method method, Interface interfaze) {
        return context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
    }

    public static class Builder extends BaseBuilder<SelectByPrimaryKeysMethodGenerator.Builder, SelectByPrimaryKeysMethodGenerator> {
        private FullyQualifiedJavaType recordType;
        private FragmentGenerator fragmentGenerator;

        public Builder withRecordType(FullyQualifiedJavaType recordType) {
            this.recordType = recordType;
            return this;
        }

        public Builder withFragmentGenerator(FragmentGenerator fragmentGenerator) {
            this.fragmentGenerator = fragmentGenerator;
            return this;
        }

        @Override
        public SelectByPrimaryKeysMethodGenerator.Builder getThis() {
            return this;
        }

        @Override
        public SelectByPrimaryKeysMethodGenerator build() {
            return new SelectByPrimaryKeysMethodGenerator(this);
        }

    }


}
