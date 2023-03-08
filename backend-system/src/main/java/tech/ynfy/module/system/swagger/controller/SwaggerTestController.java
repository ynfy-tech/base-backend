package tech.ynfy.module.system.swagger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.*;
import tech.ynfy.module.system.swagger.model.PostSwaggerDTO;
import tech.ynfy.module.system.swagger.model.PostSwaggerVO;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2022/8/3
 */
@RestController
@RequestMapping("/test")
@Schema(name = "swagger 测试")
public class SwaggerTestController {

    @Operation(summary = "getSwagger 测试", parameters = {
        @Parameter(in = ParameterIn.PATH, name = "param1", description = "name - param1 "),
        @Parameter(in = ParameterIn.QUERY, name = "param2", description = "name - param2 "),
        @Parameter(in = ParameterIn.QUERY, name = "param3", description = "name - param3 "),
    })
    @GetMapping("/getSwagger")
    public void getSwagger(@PathParam("param1") String param1,
                           @RequestParam(value = "param2", required = true, defaultValue = "1234") String param2,
                           @RequestParam(value = "param3", required = false, defaultValue = "1234") String param3) {
        System.out.println(param1 + " " + param2 + " " + param3);
    }

    @PostMapping("/postSwagger")
    @Operation(summary = "postSwagger 测试")
    public PostSwaggerVO postSwagger(@NotNull @RequestBody PostSwaggerDTO dto) {
        System.out.println(dto);
        return new PostSwaggerVO();
    }


}
