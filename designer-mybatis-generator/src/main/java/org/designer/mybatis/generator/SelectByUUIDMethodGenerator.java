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
import java.util.Optional;
import java.util.Set;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/22 1:25
 */
public class SelectByUUIDMethodGenerator extends AbstractMethodGenerator {

    private FullyQualifiedJavaType recordType;
    private FragmentGenerator fragmentGenerator;

    private SelectByUUIDMethodGenerator(SelectByUUIDMethodGenerator.Builder builder) {
        super(builder);
        recordType = builder.recordType;
        fragmentGenerator = builder.fragmentGenerator;
    }

    protected SelectByUUIDMethodGenerator(BaseBuilder<?, ?> builder) {
        super(builder);
    }

    @Override
    public MethodAndImports generateMethodAndImports() {
        Optional<IntrospectedColumn> uuid = introspectedTable.getColumn("uuid");
        if (!uuid.isPresent()) {
            return null;
        }

        Set<FullyQualifiedJavaType> imports = new HashSet<>();

        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("java.util.Optional");
        returnType.addTypeArgument(recordType);
        imports.add(returnType);

        Method method = new Method("selectByUUID");
        method.setDefault(true);
        context.getCommentGenerator().addGeneralMethodAnnotation(method, introspectedTable, imports);
        method.setReturnType(returnType);

        method.addBodyLine("return selectOne(c ->");

        MethodAndImports.Builder builder = MethodAndImports.withMethod(method)
                .withStaticImport("org.mybatis.dynamic.sql.SqlBuilder.*")
                .withImports(imports);

        acceptParts(builder, method, getUUIDWhereClauseAndParametersV2(uuid.get()));

        return builder.build();
    }

    @Override
    protected void acceptParts(MethodAndImports.Builder builder, Method method, MethodParts methodParts) {
        super.acceptParts(builder, method, methodParts);
    }

    @Override
    protected String calculateFieldName(IntrospectedColumn column) {
        return super.calculateFieldName(column);
    }

    public MethodParts getUUIDWhereClauseAndParametersV2(IntrospectedColumn column) {
        MethodParts.Builder builder = new MethodParts.Builder();

        boolean first = true;
        String fieldName = AbstractMethodGenerator.calculateFieldName(tableFieldName, column);
        builder.withImport(column.getFullyQualifiedJavaType());
        builder.withParameter(new Parameter(
                column.getFullyQualifiedJavaType(), column.getJavaProperty() + "_")); //$NON-NLS-1$
        if (first) {
            builder.withBodyLine("    c.where(" + fieldName //$NON-NLS-1$
                    + ", isEqualTo(" + column.getJavaProperty() //$NON-NLS-1$
                    + "_))"); //$NON-NLS-1$
            first = false;
        } else {
            builder.withBodyLine("    .and(" + fieldName //$NON-NLS-1$
                    + ", isEqualTo(" + column.getJavaProperty() //$NON-NLS-1$
                    + "_))"); //$NON-NLS-1$
        }
        builder.withBodyLine(");"); //$NON-NLS-1$

        return builder.build();
    }

    @Override
    public boolean callPlugins(Method method, Interface interfaze) {
        return context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
    }

    public static class Builder extends BaseBuilder<SelectByUUIDMethodGenerator.Builder, SelectByUUIDMethodGenerator> {

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
        public SelectByUUIDMethodGenerator.Builder getThis() {
            return this;
        }

        @Override
        public SelectByUUIDMethodGenerator build() {
            return new SelectByUUIDMethodGenerator(this);
        }

    }


}
