package com.itcast.education.controller.dto;

import com.itcast.education.model.community.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zheng.zhang
 * @description 社区交流信息模块DTO
 * @date 2020/5/15 11:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityPageDto {
    private Post hotPost;
    private List<Post> posts;
}
