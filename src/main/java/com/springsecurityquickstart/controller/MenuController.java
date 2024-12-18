package com.springsecurityquickstart.controller;

import com.springsecurityquickstart.anno.Log;
import com.springsecurityquickstart.pojo.Result;
import com.springsecurityquickstart.pojo.Menu;
import com.springsecurityquickstart.service.MenuService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 主題Controller
 */
@Slf4j
@RequestMapping("/menus")
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查詢全部主題數據
     * @return
     */
    @GetMapping
    public Result menuList(){
        log.info("查詢全部主題數據");
        // 調用service查詢主題數據
        List<Menu> menuList = menuService.menuList();
        return Result.success(menuList);
    }

    /**
     * 新增主題
     * @param menu
     * @return
     */
    @Log
    @PostMapping
    public Result insert(@RequestBody Menu menu){
        log.info("新增主題: {}", menu);
        // 調用service新增主題
        menuService.insert(menu);
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
        menuService.delete(id);
        return Result.success();
    }

    /**
     * 修改主題
     * @param menu
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Menu menu){
        log.info("修改主題: {}", menu);
        menuService.update(menu);
        return Result.success();
    }
}
