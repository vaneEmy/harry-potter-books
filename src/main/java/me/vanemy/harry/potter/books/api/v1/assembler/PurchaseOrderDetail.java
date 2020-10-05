package me.vanemy.harry.potter.books.api.v1.assembler;

import me.vanemy.harry.potter.books.business.repository.BookEntity;
import me.vanemy.harry.potter.books.business.repository.PurchaseOrderDetailEntity;
import me.vanemy.harry.potter.books.business.repository.PurchaseOrderEntity;

public class PurchaseOrderDetail {

    public static PurchaseOrderDetailEntity toEntity(BookEntity bookEntity, PurchaseOrderEntity purchaseOrderEntity, String quantity){
        PurchaseOrderDetailEntity purchaseOrderDetailEntity = new PurchaseOrderDetailEntity();
        purchaseOrderDetailEntity.setBook(bookEntity);
        purchaseOrderDetailEntity.setOrder(purchaseOrderEntity);
        purchaseOrderDetailEntity.setQuantity(Integer.parseInt(quantity));
        return purchaseOrderDetailEntity;
    }
}
