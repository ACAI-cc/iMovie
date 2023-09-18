package com.gec.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gec.model.system.SysMovie;

import com.gec.model.vo.SysMovieQueryVo;
;
import com.gec.system.mapper.SysMovieMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.system.service.SysMovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ACAI
 * @since 2023-06-20
 */
@Service
@Transactional
public class SysMovieServiceImpl extends ServiceImpl<SysMovieMapper, SysMovie> implements SysMovieService {


    @Override
    public IPage<SysMovie> selectPage(IPage<SysMovie> page1, SysMovieQueryVo sysMovieQueryVo) {
        IPage<SysMovie> iPage = this.baseMapper.selectPage(page1, sysMovieQueryVo);

        return iPage;
    }
}
