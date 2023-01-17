package tech.ynfy.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ynfy.common.annotation.Pass;
import tech.ynfy.frame.module.Result;
import tech.ynfy.module.auth.bean.SysUserAuthVO;
import tech.ynfy.module.auth.service.SysAuthService;
import tech.ynfy.module.user.bean.SysUserSaveDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2023/1/2
 */
@RestController
@RequestMapping("/auth")
@Tag(name="鉴权服务")
public class SysAuthController {
    
    @Autowired
    private SysAuthService authService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    @Pass
    public Result<SysUserAuthVO> login(@Valid @RequestBody SysUserSaveDTO dto) {
        SysUserAuthVO vo = authService.login(dto);
        return Result.ok(vo);
    }

    @Operation(summary = "登出")
    @PostMapping("/logout")
    public Result<SysUserAuthVO> logout(HttpServletRequest request) {
        authService.logout(request);
        return Result.ok();
    }

}
