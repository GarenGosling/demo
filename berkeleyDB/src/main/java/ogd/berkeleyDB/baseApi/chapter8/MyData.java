package ogd.berkeleyDB.baseApi.chapter8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 下午3:19
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyData implements Serializable {
    private long longData;
    private double doubleData;
    private String description;
}
