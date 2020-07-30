package com.cheer.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author cheer
 */
@Data
public class MallCategoryProductSortDTO {

    @NotNull(message = "分类Id不能为空")
    private Long categoryId;

    private List<Long> productIdList;
}
