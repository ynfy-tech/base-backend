package tech.ynfy.module.user.bean;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserSaveDTO {

    /** 用户名 */
    @Schema(description = "用户名 不能为空", required = true)
    @NotBlank(message = "用户名 不能为空")
    private String name;

    /** 密码 */
    @Schema(description = "密码 不能为空", required = true)
    @NotBlank(message = "密码 不能为空")
    private String password;



}
