package com.gec.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.model.system.SysCategory;
import com.gec.model.system.SysMovie;
import com.gec.model.vo.SysCategoryQueryVo;
import com.gec.model.vo.SysMovieQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author duo
 * @since 2023-06-20
 */
@Repository
public interface SysMovieMapper extends BaseMapper<SysMovie> {
    //        分页方法
    public IPage<SysMovie> selectPage(IPage<SysMovie> page1, @Param("vo") SysMovieQueryVo sysMovieQueryVo);


}
