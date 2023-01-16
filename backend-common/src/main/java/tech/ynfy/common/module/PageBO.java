/**
 * Copyright (C), 2006-2022,
 *
 * @FileName: SqlBO
 * @Author: Hsiong
 * @Date: 2022/4/25 20:16
 * @Description: History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package tech.ynfy.common.module;


/**
 * 〈〉
 *
 * @author Hsiong
 * @create 2022/4/25
 * @since 1.0.0
 */
public class PageBO {

    /*********************************** 可选字段 ***********************************/

    /**
     * 页数
     */
    private Integer page;

    /**
     * 页大小
     */
    private Integer rows;

    public PageBO() {
        this.page = null;
        this.rows = null;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
