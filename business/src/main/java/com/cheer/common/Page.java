package com.cheer.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author cheer
 */
@Data
@Accessors(chain = true)
public class Page<T> {

    /**
     * 当前第几页
     */
    private int pageNum;

    /**
     * 每页几条数据
     */
    private int pageSize;

    /**
     * 总共多少条数据
     */
    private long total;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 数据内容
     */
    private List<T> content;

    /**
     * 分页
     */
    public Page(IPage page, List<T> content) {
        this.total = page.getTotal();
        this.pageSize = (int) page.getSize();
        this.pageNum = (int) page.getCurrent();
        this.pages = (int) page.getPages();
        this.content = content;
    }
}