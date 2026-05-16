package com.ecom.easybuy.inventory.service;

import java.util.List;
import java.util.UUID;

import com.ecom.easybuy.inventory.dto.AdjustStockRequest;
import com.ecom.easybuy.inventory.dto.CreateInventoryRequest;
import com.ecom.easybuy.inventory.dto.InventoryResponse;
import com.ecom.easybuy.inventory.dto.ReleaseStockRequest;
import com.ecom.easybuy.inventory.dto.ReserveStockRequest;
import com.ecom.easybuy.inventory.dto.UpdateInventoryRequest;

public interface InventoryService {

    InventoryResponse create(CreateInventoryRequest request);

    InventoryResponse update(Long id, UpdateInventoryRequest request);

    InventoryResponse getById(Long id);

    InventoryResponse getBySku(String sku);

    InventoryResponse getByProductId(UUID productId);

    List<InventoryResponse> getAll();

    List<InventoryResponse> getLowStock(int threshold);

    InventoryResponse adjustStock(Long id, AdjustStockRequest request);

    InventoryResponse reserveStock(Long id, ReserveStockRequest request);

    InventoryResponse releaseStock(Long id, ReleaseStockRequest request);

    InventoryResponse reserveStockByProductId(UUID productId, ReserveStockRequest request);

    InventoryResponse releaseStockByProductId(UUID productId, ReleaseStockRequest request);

    void delete(Long id);
}

