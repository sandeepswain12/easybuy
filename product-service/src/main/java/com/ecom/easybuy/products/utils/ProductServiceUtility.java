package com.ecom.easybuy.products.utils;

import com.ecom.easybuy.products.dto.PagedResponse;
import com.ecom.easybuy.products.exception.InvalidRequestException;
import com.ecom.easybuy.products.exception.ResourceNotFoundException;
import com.ecom.easybuy.products.service.ImageStorageService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductServiceUtility {

    private final ModelMapper mapper;
    private final ImageStorageService imageStorageService;

    public ProductServiceUtility(ModelMapper mapper, ImageStorageService imageStorageService) {
        this.mapper = mapper;
        this.imageStorageService = imageStorageService;
    }

    public <S, T> T map(S source, Class<T> targetClass) {
        return mapper.map(source, targetClass);
    }

    public <S, T> PagedResponse<T> toPagedResponse(Page<S> page, Class<T> dtoClass) {
        Page<T> mapped = page.map(entity -> map(entity, dtoClass));
        return new PagedResponse<>(
                mapped.getContent(),
                mapped.getNumber(),
                mapped.getSize(),
                mapped.getTotalElements(),
                mapped.getTotalPages(),
                mapped.getNumberOfElements(),
                mapped.isFirst(),
                mapped.isLast()
        );
    }

    public <T, ID> T findEntity(JpaRepository<T, ID> repository, ID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found: " + id));
    }

    public List<String> uploadImages(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            throw new InvalidRequestException("At least one image is required");
        }
        List<String> uploadedUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            uploadedUrls.add(imageStorageService.upload(file));
        }
        return uploadedUrls;
    }
}
