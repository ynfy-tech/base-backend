package tech.ynfy.module.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import tech.ynfy.common.constant.JwtConstants;
import tech.ynfy.common.constant.RedisConstant;
import tech.ynfy.frame.util.JwtTokenUtil;
import tech.ynfy.frame.util.RedisUtil;
import tech.ynfy.module.auth.bean.SysUserAuthVO;
import tech.ynfy.module.auth.service.SysAuthService;
import tech.ynfy.module.user.bean.SysUserSaveDTO;
import tech.ynfy.module.user.entity.SysUserPO;
import tech.ynfy.module.user.mapper.SysUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2023/1/2
 */
@Service
public class SysAuthServiceImpl implements SysAuthService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public SysUserAuthVO login(SysUserSaveDTO dto) {
        SysUserPO sysUserPO = sysUserMapper.selectOne(
            new LambdaQueryWrapper<SysUserPO>().eq(SysUserPO::getName, dto.getName())
                                               .eq(SysUserPO::getPassword, DigestUtils.sha256Hex(dto.getPassword()))
        );
        if (ObjectUtil.isNotEmpty(sysUserPO)) {
            throw new IllegalArgumentException("用户名或密码错误");
        }
        String userId = sysUserPO.getId();
        String token = JwtTokenUtil.generateToken(userId);
        redisUtil.set(RedisConstant.PREFIX_USER_TOKEN_API + userId, token, JwtConstants.EXPIRATION_TIME);
        SysUserAuthVO vo = new SysUserAuthVO();
        BeanUtil.copyProperties(sysUserPO, vo);
        vo.setToken(token);
        return vo;
    }

    @Override
    public void logout(HttpServletRequest request) {
        String userId = null;
        try {
            String authToken = request.getHeader(JwtConstants.AUTH_HEADER);
            userId = JwtTokenUtil.getUsernameFromToken(authToken);
        } catch (Exception e) {
            throw new IllegalArgumentException("用户已登出! ");
        }

        redisUtil.del(RedisConstant.PREFIX_USER_TOKEN_API + userId);
    }
}
