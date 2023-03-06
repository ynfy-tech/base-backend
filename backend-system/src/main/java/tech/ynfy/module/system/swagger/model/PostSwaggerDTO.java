package tech.ynfy.module.system.swagger.model;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2022/8/3
 */
@Tag(name = "PostSwaggerDTO", description = "test")
@Data
public class PostSwaggerDTO {

    @Schema(description = "v1",required = true)
    @NotBlank
    private String v1;

    @Schema(description = "v2", required = true)
    private String v2;

    @Schema(description = "v3", defaultValue = "333")
    @NotBlank
    private String v3;

    @Schema(name = "v4")
    @Hidden
    private String v4;

}
