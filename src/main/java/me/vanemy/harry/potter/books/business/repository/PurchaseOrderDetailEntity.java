package me.vanemy.harry.potter.books.business.repository;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "purchase_order_detail")
public class PurchaseOrderDetailEntity implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private PurchaseOrderEntity order;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @JoinColumn(name = "quantity")
    private int quantity;

}
