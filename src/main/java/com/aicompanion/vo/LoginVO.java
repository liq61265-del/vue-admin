package com.aicompanion.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录响应视图对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO {

    private String token;
    private UserVO user;
}
