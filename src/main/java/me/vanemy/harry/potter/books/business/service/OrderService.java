package me.vanemy.harry.potter.books.business.service;

import me.vanemy.harry.potter.books.api.v1.assembler.PurchaseOrder;
import me.vanemy.harry.potter.books.api.v1.assembler.PurchaseOrderDetail;
import me.vanemy.harry.potter.books.api.v1.model.BookOrder;
import me.vanemy.harry.potter.books.api.v1.model.Order;
import me.vanemy.harry.potter.books.business.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    @Override
    public void createOrder(Order order) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(order.getUsername());

        optionalUserEntity.ifPresent(userEntity -> {
            PurchaseOrderEntity purchaseOrderEntity = PurchaseOrder.toModel(userEntity,order);

            for(BookOrder bookOrder: order.getBooks()){
                Optional<BookEntity> optionalBookEntity = bookRepository.findById(Integer.parseInt(bookOrder.getBookIdentifier()));
                optionalBookEntity.ifPresent(bookEntity -> {
                    final PurchaseOrderDetailEntity purchaseOrderDetailEntity = PurchaseOrderDetail.toEntity(bookEntity, purchaseOrderEntity, bookOrder.getQuantity());
                    purchaseOrderEntity.getPurchaseOrderDetailEntities().add(purchaseOrderDetailEntity);
                });
            }
            this.orderRepository.save(purchaseOrderEntity);
            updateBookCatalog(order.getBooks());
        });

    }


    /**
     * Update quantity value in books
     * @param orderList a list of books
     */
    @Transactional
    private void updateBookCatalog(List<BookOrder> orderList){
        for(BookOrder bookOrder: orderList){
            Optional<BookEntity> optionalBookEntity = bookRepository.findById(Integer.parseInt(bookOrder.getBookIdentifier()));
            optionalBookEntity.ifPresent(bookEntity -> {
                int quantity = bookEntity.getQuantity();

                bookEntity.setQuantity(quantity - Integer.parseInt(bookOrder.getQuantity()));
                bookRepository.save(bookEntity);
            });
        }
    }


}
