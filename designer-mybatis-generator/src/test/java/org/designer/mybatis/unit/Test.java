package org.designer.mybatis.unit;

import java.util.ArrayList;

/**
 * @description:
 * @author: Designer
 * @date : 2022/3/15 19:17
 */
public class Test {
    public static void main(String[] args) {
        final ArrayList<String> a = new ArrayList<>();
        final ArrayList<Object> b = new ArrayList<>();
        System.out.println(a.getClass().getComponentType());
        System.out.println(b.getClass().getComponentType());
    }
}
