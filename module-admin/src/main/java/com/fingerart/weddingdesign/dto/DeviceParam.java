package com.fingerart.weddingdesign.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 设备参数
 * Created by yecy on 2023/8/15.
 */
@Getter
@Setter
public class DeviceParam implements Serializable {

    @ApiModelProperty(value = "设备", required = false)
    private String brand;

    @ApiModelProperty(value = "机型", required = false)
    private String model;

    @ApiModelProperty(value = "系统版本")
    private String system;

    @ApiModelProperty(value = "平台")
    private String platform;

    @ApiModelProperty(value = "版本")
    private String version;

    @ApiModelProperty(value = "语言")
    private String language;

    @ApiModelProperty(value = "字体大小")
    private String fontSize;

    @ApiModelProperty(value = "窗口宽度")
    private String windowWidth;

    @ApiModelProperty(value = "窗口高度")
    private String windowHeight;

    @Override
    public String toString() {
        return "DeviceParam{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", system='" + system + '\'' +
                ", platform='" + platform + '\'' +
                ", version='" + version + '\'' +
                ", language='" + language + '\'' +
                ", fontSize='" + fontSize + '\'' +
                ", windowWidth='" + windowWidth + '\'' +
                ", windowHeight='" + windowHeight + '\'' +
                '}';
    }
}
