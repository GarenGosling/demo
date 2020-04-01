package org.garen.demo.zookeeper.component.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * 功能描述 : 计数器信息
 * </p>
 *
 * @author : Garen Gosling 2020/3/31 下午5:51
 */
@AllArgsConstructor
@Data
public class CounterInfo {
    private String name;
    private String desc;
    private Long value;
}
