package com.itcast.education.model.pojo.media;

import com.itcast.education.model.pojo.base.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MediaOutput extends BaseModel<MediaOutput> {
    /**
     * 媒体基本信息
     */
    private Integer mediaId;
    private String mediaType;
    private String url;

    /**
     * 如果是图标
     */
    private String mediaIcon;
    private String mediaIconColor;
    private Integer mediaIconBadge;
    private String mediaIconName;
}
