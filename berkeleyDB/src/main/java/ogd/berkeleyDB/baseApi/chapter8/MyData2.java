package ogd.berkeleyDB.baseApi.chapter8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 下午3:49
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyData2 {
    private long longData;
    private Double doubleData;
    private String description;
}
