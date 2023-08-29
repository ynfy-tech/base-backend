/**
 * Copyright (C), 2006-2022
 * @FileName: ReflactUtil
 * @Author:   Hsiong
 * @Date:     2022/4/8 2:20 PM
 * @Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package tech.ynfy.common.util;


import cn.hutool.core.util.ObjectUtil;
import tech.ynfy.common.annotation.SqlUtilProperty;
import tech.ynfy.common.module.SqlResultBO;

import java.lang.reflect.Field;

/**
 * 〈反射工具类〉
 *
 * @author Hsiong
 * @create 2022/4/8
 * @since 1.0.0
 */
public class ReflectUtil {
    
    /**
     * 动态拼接sql
     * @param selectSql selectSql
     * @param queryDTO 查询参数, 根据反射转化为下划线命名 查询参数
     * @return 拼接后的 sql 
     */
    public static String getSql(String selectSql, Object queryDTO) {
        SqlResultBO sqlResultBO = getSqlByFieldValue(queryDTO);
        String sql = sqlResultBO.getSql();
        Boolean ifGenerateSql = sqlResultBO.getIfGenerateSql();
        if (selectSql.toUpperCase().contains("WHERE")) {
            sql = selectSql + sql;
        } else if (ifGenerateSql) {
            sql = selectSql + " WHERE " + sql.replaceFirst("AND", "");
        } else {
            sql = selectSql;
        }

        return sql;
    }
    
    /**
     * 通过反射获取值
     * @param field 参数
     * @param o 类
     * @return 获取的值
     */
    private static Object getFieldValue(Field field, Object o) {
        field.setAccessible(true);
        String fieldName = field.getName();
        if ("serialVersionUID".equals(fieldName)) {
            return "";
        }
        Object filedValue = null;
        try {
            filedValue = field.get(o);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return filedValue;
    }

    /**
     * 将驼峰命名转化成下划线
     * @param param 驼峰命名的参数
     * @return 转换为下划线的结果
     */
    private static String camelToUnderline(String param) {
        if (param.length() < 3) {
            return param.toLowerCase();
        }
        StringBuilder sb = new StringBuilder(param);
        int temp = 0;//定位
        //从第三个字符开始 避免命名不规范
        for (int i = 2; i < param.length(); i++) {
            if (Character.isUpperCase(param.charAt(i))) {
                sb.insert(i + temp, "_");
                temp += 1;
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 从字段值中拼接 sql
     * @param queryDTO 查询参数, 根据反射转化为下划线命名
     * @return 返回结果
     */
    private static SqlResultBO getSqlByFieldValue(Object queryDTO) {
        String sql = " ";
        boolean ifGenerateSql = false;
        if (ObjectUtil.isNotEmpty(queryDTO)) {
            Class c = queryDTO.getClass();
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                Object filedValue = getFieldValue(field, queryDTO);
                if (ObjectUtil.isNotEmpty(filedValue)) {
                    String tempSql = "";
                    SqlUtilProperty property = field.getAnnotation(SqlUtilProperty.class);
                    String tableAlias = "";
                    if (ObjectUtil.isNotEmpty(property)) {
                        tableAlias = property.tableAlias() + ".";
                    }
                    // 转驼峰
                    String fieldName = field.getName();
                    String fieldSqlName = tableAlias + camelToUnderline(fieldName);
                    Class fieldClass = field.getType();
                    if ("java.lang.String".equals(fieldClass.getName())) {
                        // 字符串
                        tempSql = String.format(" (%s LIKE '%s%%') ", fieldSqlName, filedValue);
                    }

                    else {
                        tempSql = String.format(" (%s = %s) ", fieldSqlName, filedValue);
                    }

                    sql = sql + " AND " + tempSql;
                    ifGenerateSql = true;
                }
            }
        }

        SqlResultBO sqlResultBO = new SqlResultBO();
        sqlResultBO.setSql(sql);
        sqlResultBO.setIfGenerateSql(ifGenerateSql);
        return sqlResultBO;
    }
}
