package edu.wit.controller;

import bean.UserAddress;
import edu.wit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order/{id}")
    public List<UserAddress> getOrder(@PathVariable("id") String userId){
        return orderService.initOrder(userId);
    }
}
