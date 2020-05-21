package ogd.berkeleyDB.baseApi.chapter8;

import java.nio.charset.StandardCharsets;
import java.util.Comparator;

/**
 * <p>
 * 功能描述 : 自定义比较器
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 下午3:57
 */
public class MyDataComparator implements Comparator<byte[]> {
    public MyDataComparator() {}

    @Override
    public int compare(byte[] o1, byte[] o2) {
        String s1 = new String(o1, StandardCharsets.UTF_8);
        String s2 = new String(o2, StandardCharsets.UTF_8);
        return s1.compareTo(s2);
    }

}
