package com.ecjtu.dynamicdatasource.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author hht
 * @since 2019-10-09
 */
@Data
@Accessors(chain = true)
@TableName("project")
public class Project{

    private static final long serialVersionUID = 1L;
    private String id;
    /**
     * 名称
     */
    private String name;

    /**
     * 类别id
     */
    @TableField("type_id")
    private Integer typeId;

    /**
     * 制作人id
     */
    @TableField("maker_id")
    private Integer makerId;

    /**
     * 摘要目录图片地址
     */
    @TableField("abs_dir_img_url")
    private String absDirImgUrl;

    /**
     * 项目图片地址json
     */
    @TableField("prj_img_url")
    private String prjImgUrl;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 点击数
     */
    @TableField("click_num")
    private Integer clickNum;
    private  String description;

    @TableField(exist = false)
    private List<String> prjImgUrlList;

}
