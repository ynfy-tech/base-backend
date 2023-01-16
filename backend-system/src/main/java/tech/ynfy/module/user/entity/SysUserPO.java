package tech.ynfy.module.user.entity;

import tech.ynfy.frame.module.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * @Inherited 这些注解都没有使用 @Inherited , 可以放心继承
 * @date: 2021/11/30
 * @version: 1.0
 */
@TableName("sys_user")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Tag(name="sys_user对象", description="用户表")
@Data
public class SysUserPO extends BaseEntity implements Serializable {

    /** 用户名 */
    @Schema(description = "用户名")
    private String name;

    /** 密码 */
    @Schema(description = "密码", required = true)
    private String password;

    
}
