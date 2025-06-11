package com.fast_pos.fast_pos.application.service;

import com.fast_pos.fast_pos.adapter.out.persistence.entity.TenantStoresUsersEntity;
import com.fast_pos.fast_pos.adapter.out.persistence.jpa.JpaTenantStoresUsersRepository;
import com.fast_pos.fast_pos.adapter.out.persistence.mapper.TenantStoresUsersMapper;
import com.fast_pos.fast_pos.domain.model.TenantStoresUsers;
import com.fast_pos.fast_pos.infrastructure.config.security.UserPrincipal;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserDetailService implements UserDetailsService {

    private final TenantStoresUsersMapper tenantStoresUsersMapper;
    private final JpaTenantStoresUsersRepository jpaTenantStoresUsersRepository;

    public UserDetailService(@Lazy JpaTenantStoresUsersRepository jpaTenantStoresUsersRepository, TenantStoresUsersMapper tenantStoresUsersMapper){
        this.jpaTenantStoresUsersRepository=jpaTenantStoresUsersRepository;
        this.tenantStoresUsersMapper=tenantStoresUsersMapper;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        TenantStoresUsersEntity entity = jpaTenantStoresUsersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        TenantStoresUsers domainUser = tenantStoresUsersMapper.toDomain(entity);

        return new UserPrincipal(
                domainUser.getId(),
                domainUser.getEmail(),
                domainUser.getPassword(),
                domainUser.getSchemaName(), // schemaName
                List.of(new SimpleGrantedAuthority("ROLE_" + domainUser.getUserRole().name()))
        );
    }
}
