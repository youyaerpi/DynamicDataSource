package com.ecjtu.dynamicdatasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author hht
 * @since 2019-10-22
 */
@Data
@TableName("orders")
@Accessors(chain = true)
public class Order {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.INPUT)
    private String id;
    @TableField("project_id")
    private String projectId;

    @TableField("create_time")
    private Date createTime;

    @TableField("pay_price")
    private Integer payPrice;

    private String contact;

    @TableField("school_name")
    private String schoolName;

    /**
     * 订单状态：1 待支付 2 待发包 3 完成 4 退款
     */
    private Integer status;

    /**
     * 支付人昵称
     */
    @TableField("payer_name")
    private String payerName;
}
