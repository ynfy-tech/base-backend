package tech.ynfy.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ynfy.frame.module.Result;
import tech.ynfy.module.user.bean.SysUserListDTO;
import tech.ynfy.module.user.bean.SysUserListVO;
import tech.ynfy.module.user.bean.SysUserSaveDTO;
import tech.ynfy.module.user.bean.SysUserUpdateDTO;
import tech.ynfy.module.user.service.SysUserService;

import javax.validation.Valid;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2022/8/1
 */
@RestController
@RequestMapping("/sysUser")
@Tag(name="用户管理")
public class SysUserController {
    
    @Autowired
    private SysUserService sysUserService;

    @GetMapping
    @Operation(summary = "用户表列表分页", parameters = {
        @Parameter(in = ParameterIn.QUERY, name = "page", description = " 第几页 ", required = true),
        @Parameter(in = ParameterIn.QUERY, name = "rows", description = " 页大小 ", required = true),
    })
    public Result<IPage<SysUserListVO>> list(SysUserListDTO dto,
                                             @RequestParam(value = "page") Integer page,
                                             @RequestParam(value = "rows") Integer rows) {
        return Result.ok(sysUserService.list(dto));
    }

    @Operation(summary = "保存用户表")
    @PostMapping
    public Result save(@Valid @RequestBody SysUserSaveDTO dto) {
        sysUserService.save(dto);
        return Result.ok();
    }

    @Operation(summary = "更新用户表")
    @PutMapping
    public Result update(@Valid @RequestBody SysUserUpdateDTO dto) {
        sysUserService.update(dto);
        return Result.ok();
    }

    @Operation(summary = "删除用户表")
    @DeleteMapping
    public Result delete(@RequestParam(value = "id", required = true) Long id) {
        sysUserService.delete(id);
        return Result.ok();
    }


}
