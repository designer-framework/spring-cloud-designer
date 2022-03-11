package org.designer.mybatis.generator;

import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.SimpleXMLMapperGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/10 1:27
 */
public class BasicXMLMapperGenerator extends SimpleXMLMapperGenerator {

    @Override
    protected void addResultMapElement(XmlElement parentElement) {
        super.addResultMapElement(parentElement);
    }

    @Override
    protected void addSelectByPrimaryKeyElement(XmlElement parentElement) {
    }

    @Override
    protected void addSelectAllElement(XmlElement parentElement) {
    }

    @Override
    protected void addDeleteByPrimaryKeyElement(XmlElement parentElement) {
    }

    @Override
    protected void addInsertElement(XmlElement parentElement) {
    }

    @Override
    protected void addUpdateByPrimaryKeyElement(XmlElement parentElement) {
    }

}
