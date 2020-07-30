package com.cheer.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author cheer
 */
@Data
@Accessors(chain = true)
public class MallProductInfoVO {

    private Long id;

    private Integer type;

    private String mainTitle;

    private String shortTitle;

    private ImageVO thumbnailUrl;

    private Long shopId;

    private String marketPrice;

    private String displayPrice;

    private String credit;

    private List<Long> categoryIdList;

    private Integer stock;

    private List<String> attr;

    private List<ImageVO> displayImageUrl;

    private String keywords;

    private String desc;

    private List<ImageVO> descImageUrl;

}
