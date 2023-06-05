package com.wgq.chat.common.utils;

public interface Cache<K, V> {

    V get(K key);
}
