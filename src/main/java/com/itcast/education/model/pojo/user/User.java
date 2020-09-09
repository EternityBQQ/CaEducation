package com.itcast.education.model.pojo.user;

import com.itcast.education.model.pojo.base.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User extends BaseModel<User> {
    private String userId;
    private String username;
    private String password;
    private String realPassword;
    private String userRealName;
    private Integer iconMediaId;
    private String salt;
    private String mobile;
    private String email;

    public User(String username) {
        this.username = username;
    }
}
