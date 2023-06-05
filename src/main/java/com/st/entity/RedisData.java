package com.st.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author: TIKI
 * @Project: dianping -RedisData
 * @Pcakage: com.st.entity.RedisData
 * @Date: 2023年06月05日 15:47
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisData {
    private LocalDateTime expireTime;
    private  Object data;
}