package com.fast_pos.fast_pos.domain.port.in;

import com.fast_pos.fast_pos.adapter.in.dto.request.RegisterStoreRequest;
import org.springframework.web.multipart.MultipartFile;

public interface RegisterStoreUseCase {
    void registerNewStore(RegisterStoreRequest request, MultipartFile productsFile);
}
