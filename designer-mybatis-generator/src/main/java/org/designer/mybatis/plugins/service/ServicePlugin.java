package org.designer.mybatis.plugins.service;

import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/5 2:53
 */
public class ServicePlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

}
