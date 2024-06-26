package com.mue.music.model;

import com.mue.music.model.enums.AuthProvider;
import com.mue.music.model.enums.Role;
import com.mue.music.model.enums.UserStatus;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.Data;


@Data
public class Principal {
    private UUID id;
    private String email;
    private Timestamp createdAt;
    private Timestamp lastModifiedAt;
    private Role role;
    private UserStatus userStatus;
    private String fullName;
    private AuthProvider provider;
    private String avatar;
}
