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
public class SqlResultBO {
    
    private String sql;
    
    private Boolean ifGenerateSql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Boolean getIfGenerateSql() {
        return ifGenerateSql;
    }

    public void setIfGenerateSql(Boolean ifGenerateSql) {
        this.ifGenerateSql = ifGenerateSql;
    }
}
