package com.zzh.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class CategoryVo {

//id，图标路径，图标名称

    private Long id;

    private String avatar;

    private String categoryName;

    private String description;
}

