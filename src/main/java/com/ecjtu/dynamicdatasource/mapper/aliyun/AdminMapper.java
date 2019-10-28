package com.ecjtu.dynamicdatasource.mapper.aliyun;

import com.ecjtu.dynamicdatasource.entity.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiexiang
 * @date 2019/10/25 5:17 下午
 */
public interface AdminMapper {

    Admin getAdminById(@Param("id") Integer id);
}
