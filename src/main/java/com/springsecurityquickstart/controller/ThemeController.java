package com.springsecurityquickstart.controller;

import com.springsecurityquickstart.anno.Log;
import com.springsecurityquickstart.pojo.Result;
import com.springsecurityquickstart.pojo.Theme;
import com.springsecurityquickstart.service.ThemeService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 主題Controller
 */
@Slf4j
@RequestMapping("/themes")
@RestController
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    /**
     * 查詢全部主題數據
     * @return
     */
    @GetMapping
    public Result themeList(){
        log.info("查詢全部主題數據");
        // 調用service查詢主題數據
        List<Theme> themeList = themeService.themeList();
        return Result.success(themeList);
    }

    /**
     * 新增主題
     * @param theme
     * @return
     */
    @Log
    @PostMapping
    public Result insert(@RequestBody Theme theme){
        log.info("新增主題: {}", theme);
        // 調用service新增主題
        themeService.insert(theme);
        return Result.success();
    }

    /**
     * 刪除主題
     * @param id
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("刪除主題: {}", id);
        // 調用service刪除主題
        themeService.delete(id);
        return Result.success();
    }

    /**
     * 修改主題
     * @param theme
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Theme theme){
        log.info("修改主題: {}", theme);
        themeService.update(theme);
        return Result.success();
    }
}
