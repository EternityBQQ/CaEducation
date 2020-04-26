package com.itcast.education.model.media;

import com.itcast.education.model.base.BaseModel;

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

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMediaIcon() {
        return mediaIcon;
    }

    public void setMediaIcon(String mediaIcon) {
        this.mediaIcon = mediaIcon;
    }

    public String getMediaIconColor() {
        return mediaIconColor;
    }

    public void setMediaIconColor(String mediaIconColor) {
        this.mediaIconColor = mediaIconColor;
    }

    public Integer getMediaIconBadge() {
        return mediaIconBadge;
    }

    public void setMediaIconBadge(Integer mediaIconBadge) {
        this.mediaIconBadge = mediaIconBadge;
    }

    public String getMediaIconName() {
        return mediaIconName;
    }

    public void setMediaIconName(String mediaIconName) {
        this.mediaIconName = mediaIconName;
    }
}
