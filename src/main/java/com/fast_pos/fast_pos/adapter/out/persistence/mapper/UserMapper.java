package com.fast_pos.fast_pos.adapter.out.persistence.mapper;

import com.fast_pos.fast_pos.adapter.out.persistence.entity.UserEntity;
import com.fast_pos.fast_pos.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public  UserEntity toEntity(User user){
        return new UserEntity(
                user.getId(),
                user.getTenantStoreName(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }

    public  User toDomain(UserEntity entity){
        return new User(
                entity.getId(),
                entity.getTenantStoreName(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole()
        );
    }
}
