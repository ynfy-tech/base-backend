package tech.ynfy.module.auth.service;


import tech.ynfy.module.auth.bean.SysUserAuthVO;
import tech.ynfy.module.user.bean.SysUserSaveDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2023/1/2
 */
public interface SysAuthService {

    SysUserAuthVO login(SysUserSaveDTO dto);
    
    void logout(HttpServletRequest request);

}
