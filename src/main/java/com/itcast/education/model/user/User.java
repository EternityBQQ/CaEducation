package com.itcast.education.model.user;

import com.itcast.education.model.base.BaseModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User extends BaseModel<User> {
    private String userId;
    private String username;
    private String password;
    private String userRealName;
    private String mobile;
    private String email;

    public User(String userId) {
        this.userId = userId;
    }
}
