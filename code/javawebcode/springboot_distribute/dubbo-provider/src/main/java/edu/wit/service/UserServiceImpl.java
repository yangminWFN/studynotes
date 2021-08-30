package edu.wit.service;

import bean.UserAddress;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Service
@Component
public class UserServiceImpl implements UserService {
    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        UserAddress address1 = new UserAddress(1, "湖北省武汉市", "1", "15271824272", "是");
        UserAddress address2 = new UserAddress(2, "湖北省荆门市", "1", "15271824273", "否");
        UserAddress address3 = new UserAddress(3, "湖北省天门市", "1", "15271824274", "否");
        return Arrays.asList(address1, address2, address3);
    }
}
