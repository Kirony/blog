package com.zzh.controller;

import com.zzh.Service.LoginService;
import com.zzh.vo.ErrorCode;
import com.zzh.vo.Result;
import com.zzh.vo.params.LoginParams;
import com.zzh.vo.params.PageParams;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParams loginParams) {

        return loginService.login(loginParams);
    }
}
