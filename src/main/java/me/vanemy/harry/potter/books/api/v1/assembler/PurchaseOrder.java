package me.vanemy.harry.potter.books.api.v1.assembler;

import me.vanemy.harry.potter.books.api.v1.model.Order;
import me.vanemy.harry.potter.books.business.repository.PurchaseOrderEntity;
import me.vanemy.harry.potter.books.business.repository.UserEntity;

import java.math.BigDecimal;

public class PurchaseOrder {

    public static PurchaseOrderEntity toModel(UserEntity userEntity, Order order){
        PurchaseOrderEntity purchaseOrderEntity = new PurchaseOrderEntity();
        purchaseOrderEntity.setTotal(new BigDecimal(order.getTotal()));
        purchaseOrderEntity.setUserEntity(userEntity);
        return purchaseOrderEntity;
    }
}
