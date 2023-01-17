package tech.ynfy.module.user.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.ynfy.common.module.PageBO;

import java.io.Serializable;

/**
 *
 * @Inherited 这些注解都没有使用 @Inherited , 可以放心继承
 * @date: 2021/11/30
 * @version: 1.0
 */
@Data
public class SysUserListDTO extends PageBO implements Serializable {

    /** 用户名 */
    @Schema(description = "用户名")
    private String name;

    /** 密码 */
    @Schema(description = "密码")
    private String password;

    
}
