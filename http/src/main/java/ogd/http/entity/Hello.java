package ogd.http.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 功能描述 : 对象
 * </p>
 *
 * @author : Garen Gosling 2020/5/5 下午3:57
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Hello {
    private Integer id;
    private String message;
}
