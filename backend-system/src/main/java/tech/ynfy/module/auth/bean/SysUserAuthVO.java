package tech.ynfy.module.auth.bean;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tech.ynfy.frame.module.BaseEntity;

/**
 * @Description: sysUser 列表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserAuthVO extends BaseEntity {

    /** 用户名 */
    @Schema(description = "用户名")
    private String name;

    /** 密码 */
    @Schema(description = "密码")
    private String password;

    /** token */
    @Schema(description = "token")
    private String token;

}
