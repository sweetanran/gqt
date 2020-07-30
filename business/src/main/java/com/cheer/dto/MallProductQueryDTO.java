package com.cheer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author cheer
 */
@Data
@Accessors(chain = true)
public class MallProductQueryDTO {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createDate;

    private Integer publishStatus;
}
