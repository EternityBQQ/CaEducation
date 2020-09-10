package com.itcast.education.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zheng.zhang
 * Description 用户信息DTO
 * @date 2020/5/8 12:56
 */
@ApiModel(value = "用户信息")
@Data
public class UserDto {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("真实姓名")
    private String userRealName;
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("邮箱")
    private String email;
}
