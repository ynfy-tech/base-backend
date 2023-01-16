package tech.ynfy.module.user.bean;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Description: sysUser 列表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserUpdateDTO extends SysUserSaveDTO {
    
    @Schema(description = "ID", required = true)
    @NotNull(message = "ID 不能为空")
    private Long id;
    
}
