package com.cheer.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author cheer
 */
@Data
@Accessors(chain = true)
public class MallCategoryUpdateDTO {

    @NotNull(message = "分类Id不能为空")
    private Long id;

    @NotBlank(message = "分类名称不能为空")
    @Length(max = 50, message = "分类名称字符数不能超过50")
    private String name;
}
