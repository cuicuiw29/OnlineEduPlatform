package com.cuicui.pgd.service.edu.controller.admin;


import com.cuicui.pgd.common.base.result.R;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {
//    登录
    @PostMapping("login")
    public R login(){
                return R.ok().data("token","admin");
    }

//    获取用户信息
    @GetMapping("info")
    public R info(){
        return R.ok().data("name","admincuicuiw!")
                .data("roles","[admin]")
                .data("avatar","https://tva4.sinaimg.cn/crop.0.0.600.600.180/b58f48c4jw8fclvmbxs45j20go0k076k.jpg?KID=imgbed,tva&Expires=1649422600&ssig=6eNgKyflsj");
    }

//    登出
    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
