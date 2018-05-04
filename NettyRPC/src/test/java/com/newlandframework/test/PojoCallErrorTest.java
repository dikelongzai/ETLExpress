/**
 * Copyright (C) 2017 Newland Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.newlandframework.test;

import com.newlandframework.rpc.exception.InvokeModuleException;
import com.newlandframework.rpc.services.PersonManage;
import com.newlandframework.rpc.services.pojo.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tangjie<https://github.com/tang-jie>
 * @filename:PojoCallErrorTest.java
 * @description:PojoCallErrorTest功能模块
 * @blogs http://www.cnblogs.com/jietang/
 * @since 2017/9/26
 */
public class PojoCallErrorTest {
    public static void test1(PersonManage manage) {
        try {
            manage.check();
        } catch (InvokeModuleException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void test2(PersonManage manage) {
        try {
            Person p = new Person();
            p.setId(20150811);
            p.setName("XiaoHaoBaby");
            p.setAge(1);
            manage.checkAge(p);
        } catch (InvokeModuleException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:rpc-invoke-config-client.xml");

        PersonManage manage = (PersonManage) context.getBean("personManage");

        try {
            test1(manage);
            test2(manage);
        } finally {
            context.destroy();
        }
    }
}

