package com.zzh.Service;

import com.zzh.dao.pojo.SysUser;
import com.zzh.vo.Result;
import com.zzh.vo.params.LoginParams;

public interface LoginService {

    Result login(LoginParams loginParams);

    SysUser checkToken(String token);

    Result logout(String token);

    Result register(LoginParams loginParams);

}
