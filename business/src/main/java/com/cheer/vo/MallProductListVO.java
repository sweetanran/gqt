package com.cheer.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author cheer
 */
@Data
@Accessors(chain = true)
public class MallProductListVO {

    private Long id;

    private String number;

    private String name;

    private String marketPrice;

    private String displayPrice;

    private String createTime;

    private Integer publishStatus;

    private String category;

    private Integer stock;
}
