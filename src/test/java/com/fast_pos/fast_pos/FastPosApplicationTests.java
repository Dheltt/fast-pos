package com.fast_pos.fast_pos;

import com.fast_pos.fast_pos.adapter.out.persistence.adapter.TenantManagementAdapter;
import com.fast_pos.fast_pos.domain.port.out.TenantManagementRepositoryPort;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class FastPosApplicationTests {

	@Test
	void contextLoads() {
	}
	@Bean
	public TenantManagementRepositoryPort tenantManagementRepository(JdbcTemplate jdbcTemplate) {
		return new TenantManagementAdapter(jdbcTemplate);
	}

	//@Bean
	/*public StoreRegistrationServiceTest storeRegistrationService(TenantManagementRepositoryPort tenantManagementRepository) {
		return new StoreRegistrationServiceTest(tenantManagementRepository);
	}*/

}
