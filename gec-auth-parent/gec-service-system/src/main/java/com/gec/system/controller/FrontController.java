//package com.gec.system.controller;
//
//import com.gec.model.system.SysCategory;
//import com.gec.system.service.SysCategoryService;
//import com.gec.system.service.SysMovieService;
//import com.gec.system.util.Result;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Api(tags = "前台")
//@RestController
//@RequestMapping("admin/system/frontController")
//public class FrontController {
//    @Autowired
//    private SysCategoryService categoryService;
//    @Autowired
//    private SysMovieService movieService;
//
//    @ApiOperation("根据分类id查询最新影片分页")
//    @GetMapping("/getNewMovieList")
//    public Result getNewMovieList(Integer pageNum, Integer pageSize, Long cid){
//        return movieService.getNewMovieList(pageNum, pageSize, cid);
//    }
//
//
//}
