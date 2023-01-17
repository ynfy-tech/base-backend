package tech.ynfy.system.swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2022/8/3
 */
@Data
public class PostSwaggerVO {

    @Schema(description = "r-1")
    private String r1;

    @Schema(description = "r-2")
    private String r2;

}
