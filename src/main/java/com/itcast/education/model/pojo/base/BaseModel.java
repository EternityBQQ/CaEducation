package com.itcast.education.model.pojo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 基础Model
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel<T extends BaseModel> {
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 创建人ID
     */
    private String createPerson;
    /**
     * 更新人ID
     */
    private String updatePerson;
}
