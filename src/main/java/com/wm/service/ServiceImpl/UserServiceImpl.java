package com.wm.service.ServiceImpl;

import com.wm.dao.UserDao;
import com.wm.entity.User;
import com.wm.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@CacheConfig(cacheNames = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    //@Cacheable: 插入缓存
    @Override
    @Cacheable(value = "userCache", keyGenerator = "wiselyKeyGenerator")
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Cacheable(value = "user", key = "#id")
    public User findAllById(int id) {
        return userDao.findByid(id);
    }

    @Override
    @CachePut(value = "user" ,key = "#result.id")
    public User updateUserById(User user) {
        return userDao.save(user);
    }

    @Override
    @CacheEvict(value = "user", key = "#id")
    public User deleteUserById(int id) {
        return userDao.deleteById(id);
    }
}
