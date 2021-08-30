package edu.wit.service;

import bean.UserAddress;

import java.util.List;

public interface OrderService {
    List<UserAddress> initOrder(String userId);
}
