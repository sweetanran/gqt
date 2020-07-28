package com.cheer.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

/**
 * @author cheer
 */
public class PageUtil<T> {

    /**
     * @param pageNum  当前页
     * @param pageSize 每页几条数据
     * @param sidx     排序字段
     * @param order    排序顺序
     */
    public IPage<T> buildPage(Integer pageNum, Integer pageSize, String sidx, String order) {
        //分页对象
        Page<T> page = new Page<>(pageNum, pageSize);

        //排序字段
        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = SQLFilter.sqlInject(sidx);

        //前端字段排序
        if (StringUtils.isNotBlank(orderField) && StringUtils.isNotBlank(order)) {
            if ("asc".equalsIgnoreCase(order)) {
                return page.addOrder(OrderItem.asc(orderField));
            } else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }

        return page;
    }
}
