package com.ecjtu.dynamicdatasource;

import com.ecjtu.dynamicdatasource.entity.Admin;
import com.ecjtu.dynamicdatasource.entity.Project;
import com.ecjtu.dynamicdatasource.mapper.aliyun.AdminMapper;
import com.ecjtu.dynamicdatasource.mapper.paper.Projectmapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author xiexiang
 * @date 2019/10/25 5:12 下午
 */
@SpringBootTest(classes = DynamicdatasourceApplication.class)
@RunWith(SpringRunner.class)
public class BaseTest {

    @Resource
    private AdminMapper adminMapper;
    @Resource
    private Projectmapper projectmapper;


    @Test
    public void baseTest() {
        Admin adminById = adminMapper.getAdminById(1);
        Project projectById = projectmapper.getProjectById("1");
        System.out.println(projectById);
        System.out.println(adminById);

    }
}
