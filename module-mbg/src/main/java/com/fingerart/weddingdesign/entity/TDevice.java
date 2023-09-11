package com.fingerart.weddingdesign.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class TDevice implements Serializable {
    private Integer id;

    private String name;

    private String model;

    private String platform;

    private String osVersion;

    private Integer windowWidth;

    private Integer windowHeight;

    private String rev;

    private Date createAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public Integer getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(Integer windowWidth) {
        this.windowWidth = windowWidth;
    }

    public Integer getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(Integer windowHeight) {
        this.windowHeight = windowHeight;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", model=").append(model);
        sb.append(", platform=").append(platform);
        sb.append(", osVersion=").append(osVersion);
        sb.append(", windowWidth=").append(windowWidth);
        sb.append(", windowHeight=").append(windowHeight);
        sb.append(", rev=").append(rev);
        sb.append(", createAt=").append(createAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}