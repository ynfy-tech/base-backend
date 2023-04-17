package tech.ynfy.module.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tech.ynfy.module.user.bean.SysUserListVO;
import tech.ynfy.module.user.entity.SysUserPO;

// 指定 @MapperScan 可不加该注解
@Repository
public interface SysUserMapper extends BaseMapper<SysUserPO> {

    IPage<SysUserListVO> pageList(IPage<SysUserPO> page, @Param("sql") String sql);

}