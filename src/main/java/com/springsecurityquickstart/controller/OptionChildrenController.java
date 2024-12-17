package com.springsecurityquickstart.controller;

import com.springsecurityquickstart.anno.Log;
import com.springsecurityquickstart.pojo.Result;
import com.springsecurityquickstart.pojo.option.OptionChildren;
import com.springsecurityquickstart.service.OptionChildrenService;
import com.springsecurityquickstart.service.OptionService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/optionChildren")
@RestController
public class OptionChildrenController {
    @Autowired
    private OptionChildrenService optionChildrenService;

    @Autowired
    private OptionService optionService;

    /**
     * 新增子選項
     * @param child
     * @return
     */
    @Log
    @PostMapping
    public Result addOptionChildren(@RequestBody OptionChildren child){
        log.info("新增子選項");
        optionService.addOptionChildren(child);
        return Result.success();
    }

    /**
     * 刪除子選項
     * @param childId
     * @return
     */
    @Log
    @DeleteMapping("/{childId}")
    public Result deleteOptionChildren(@PathVariable Integer childId){
        log.info("刪除子選項:{}",childId);
        optionChildrenService.deleteOptionChildren(childId);
        return Result.success();
    }

    /**
     * 修改子選項
     * @param child
     * @return
     */
    @Log
    @PutMapping
    public Result updateOptionChildren(@RequestBody OptionChildren child){
        log.info("修改子選項:{}",child);
        optionChildrenService.updateOptionChildren(child);
        return Result.success();
    }
}
