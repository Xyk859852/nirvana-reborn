package com.phoenix.nirvana.common.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Key Value 的键值对
 *
 * @author xuyongkang
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue<K, V> {

    private K key;
    private V value;

}
