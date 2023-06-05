package com.st.service.impl;

import cn.hutool.json.JSONUtil;
import com.st.dto.Result;
import com.st.entity.ShopType;
import com.st.mapper.ShopTypeMapper;
import com.st.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    ShopTypeMapper shopTypeMapper;
    @Override
    public Result queryTypeList() {
        String key = "cache:shopType:";
        // 1.从redis中查询商铺类型缓存
        String shopTypeJson = stringRedisTemplate.opsForValue().get(key);
        // 2.判断是否存在
        if (shopTypeJson != null) {
            // 3.存在，那么直接返回
            List<ShopType> shopTypes = JSONUtil.toList(shopTypeJson, ShopType.class);
            return Result.ok(shopTypes);
        }
        // 4.不存在，查询数据库
        List<ShopType> shopTypes = query().orderByAsc("sort").list();
        // 5.存入redis
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shopTypes));
        // 6.返回结果
        return Result.ok(shopTypes);
    }
}
