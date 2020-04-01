package org.garen.demo.zookeeper.component.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/3/31 上午11:50
 */
@ToString
@AllArgsConstructor
@Data
public class CounterResult {
    private Boolean succeeded;
    private Long preValue;
    private Long postValue;
}
