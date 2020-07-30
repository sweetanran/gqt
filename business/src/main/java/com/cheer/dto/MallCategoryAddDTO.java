package com.cheer.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author cheer
 */
@Data
public class MallCategoryAddDTO {

    @NotBlank(message = "分类名称不能为空")
    @Length(max = 50, message = "分类名称字符数不能超过50")
    private String name;
}
