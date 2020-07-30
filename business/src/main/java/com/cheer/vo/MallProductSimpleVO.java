package com.cheer.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author cheer
 */
@Data
@Accessors(chain = true)
public class MallProductSimpleVO {

    private Long id;

    private String name;
}
