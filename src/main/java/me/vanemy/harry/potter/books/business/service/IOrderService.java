package me.vanemy.harry.potter.books.business.service;

import me.vanemy.harry.potter.books.api.v1.model.Order;

public interface IOrderService {

    void createOrder(Order order);
}
