package com.ecjtu.dynamicdatasource.entity;

import lombok.Data;
import lombok.experimental.Accessors;

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
public class Type  {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer id;


}
