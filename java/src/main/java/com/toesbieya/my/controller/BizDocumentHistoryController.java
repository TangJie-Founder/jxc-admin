package com.toesbieya.my.controller;

import com.toesbieya.my.model.vo.search.DocumentHistorySearch;
import com.toesbieya.my.service.BizDocumentHistoryService;
import com.toesbieya.my.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("document/history")
public class BizDocumentHistoryController {
    @Resource
    private BizDocumentHistoryService documentHistoryService;

    @GetMapping("get")
    public Result get(@RequestParam("pid") String pid) {
        return Result.success(documentHistoryService.getByPid(pid));
    }

    @PostMapping("search")
    public Result search(@RequestBody DocumentHistorySearch vo) {
        return Result.success(documentHistoryService.search(vo));
    }
}
