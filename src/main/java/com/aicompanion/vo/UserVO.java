package com.aicompanion.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户视图对象（不含密码）
 */
@Data
public class UserVO {

    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private String avatar;
    private String role;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
