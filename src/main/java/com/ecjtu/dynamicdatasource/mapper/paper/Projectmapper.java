package com.ecjtu.dynamicdatasource.mapper.paper;

import com.ecjtu.dynamicdatasource.entity.Project;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiexiang
 * @date 2019/10/25 5:18 下午
 */
public interface Projectmapper {

    Project getProjectById(@Param("id") String id);

}
