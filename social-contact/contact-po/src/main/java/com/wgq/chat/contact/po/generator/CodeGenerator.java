package com.wgq.chat.contact.po.generator;

import com.wgq.chat.contact.po.Audit;
import com.wgq.chat.contact.po.SparrowExample;

import java.io.IOException;

/**
 * @ClassName CodeGenerator
 * @Description TODO
 * @Author wgq
 * @Date 2023/6/19 22:20
 * @Version 1.0
 **/
public class CodeGenerator {


    public static void generaCreateDDL(Class<?> po) throws IOException {
        AbstractEntityManagerAdapter managerAdapter = new SparrowEntityManager(po);
        String sql = managerAdapter.getCreateDDL();
        System.err.println(sql);
    }
}
