package com.fast_pos.fast_pos.infrastructure.database;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
@Component
public class TenantFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(TenantFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String tenant = request.getHeader("X-Tenant-Schema"); // ejemplo
            log.debug("Received tenant header: {}", tenant);
            if (tenant != null && !tenant.isBlank()) {
                TenantContext.setTenantSchema(tenant);
            } else {
                log.debug("No tenant header found, defaulting to public");
            }
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear(); // Limpia al final para evitar fugas
        }
    }
}
