package com.aicompanion.dto;

import lombok.Data;

/**
 * 用户信息更新 DTO
 */
@Data
public class UserDTO {

    private String nickname;
    private String email;
    private String phone;
    private String avatar;
}
