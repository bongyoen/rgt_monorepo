package com.kr.api.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryMap {
    private final Map<String, OrderEntity> map = new HashMap<>();
    private final Map<String, OrderEntity> backupMap = new HashMap<>();

    public void put(String key, OrderEntity value) {
        map.put(key, value);
    }

    public OrderEntity get(String key) {
        return map.get(key);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public List<OrderEntity> getAll() {
        return new ArrayList<>(map.values());
    }

    public void backup() {
        backupMap.clear();
        backupMap.putAll(map);
    }

    public void rollback() {
        map.clear();
        map.putAll(backupMap);
    }

    public void clear() {
        map.clear();
        backupMap.clear();
    }
}
