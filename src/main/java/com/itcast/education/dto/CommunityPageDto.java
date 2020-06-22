package com.itcast.education.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author zheng.zhang
 * @description 社区交流信息模块DTO
 * @date 2020/5/15 11:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "社区页面数据")
public class CommunityPageDto implements Serializable {
    @ApiModelProperty(value = "热帖")
    private Map<String, Object> hotPost;
    @ApiModelProperty(value = "其他帖子")
    private List<Map<String, Object>> posts;
}
