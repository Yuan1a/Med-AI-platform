package com.graphy.unit.idcreate;

/**
 * 获取唯一标识
 */
public class Api {

    /**
     * 生成唯一标识
     *
     * @return
     */
    public static String getNewId() {
        return IdGenerator.getLongNextId();
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
