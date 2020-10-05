package me.vanemy.harry.potter.books.business.service;

import me.vanemy.harry.potter.books.api.v1.model.Order;
import me.vanemy.harry.potter.books.business.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void createOrder(Order order) {

    }
}
