package com.cheer.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author cheer
 */
@Data
public class MallCategoryProductDTO {

    @NotNull(message = "分类Id不能为空")
    private Long categoryId;

    @NotNull(message = "商品Id不能为空")
    private Long productId;
}
