package com.cheer.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author cheer
 */
@Data
@Accessors(chain = true)
public class MallProductAddDTO {

    @NotBlank(message = "主标题不能为空")
    @Length(max = 200, message = "主标题字符数不能超过200")
    private String mainTitle;

    @NotBlank(message = "简略标题不能为空")
    @Length(max = 200, message = "简略标题字符数不能超过200")
    private String shortTitle;

    @NotBlank(message = "缩略图不能为空")
    @Length(max = 255, message = "缩略图地址字符数不能超过255")
    private String thumbnailUrl;

    @NotNull(message = "商户Id不能为空")
    private Long shopId;

    private String marketPrice;

    private String displayPrice;

    private String credit;

    private List<Long> categoryIdList;

    @NotNull(message = "库存不能为空")
    private Integer stock;

    @NotNull(message = "产品类型不能为空")
    private Integer productType;

    private List<String> attr;

    @NotEmpty(message = "首页展示图不能为空")
    @Size(max = 4, message = "首页展示图最多上传4张")
    private List<String> displayImageUrl;

    @Length(max = 8, message = "关键词字符数不能超过8")
    private String keywords;

    private String desc;

    @Size(max = 10, message = "详情图最多上传10张")
    private List<String> descImageUrl;

    @NotNull(message = "提交类型不能为空")
    private Integer submitType;

}
