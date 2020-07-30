package com.cheer.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author cheer
 */
@Data
@Accessors(chain = true)
public class MallCategoryProductVO {

    private Long id;

    private String name;

    private List<MallProductSimpleVO> productList;
}
