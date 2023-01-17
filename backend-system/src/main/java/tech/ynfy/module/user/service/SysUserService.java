package tech.ynfy.module.user.service;


import tech.ynfy.module.user.bean.SysUserListDTO;
import tech.ynfy.module.user.bean.SysUserListVO;
import tech.ynfy.module.user.bean.SysUserSaveDTO;
import tech.ynfy.module.user.bean.SysUserUpdateDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2022/8/17
 */
public interface SysUserService {

    /**
     * 保存新用户表
     * @param dto
     */
    void save(SysUserSaveDTO dto);

    /**
     * 更新用户表信息
     * @param dto
     */
    void update(SysUserUpdateDTO dto);

    /**
     * 删除用户表
     * @param id
     */
    void delete(Long id);

    /**
     * 用户表列表
     * @param 
     * @return
     */
    IPage<SysUserListVO> list(SysUserListDTO dto);
    

}
