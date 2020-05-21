package ogd.berkeleyDB.baseApi.chapter8;

import com.sleepycat.je.DatabaseEntry;

import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 功能描述 : 使用数据库记录
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 上午10:19
 */
public class Demo1 {

    public static void main(String[] args) {

        String aKey = "key";
        String aData = "data";

        try {
            DatabaseEntry theKey = new DatabaseEntry(aKey.getBytes(StandardCharsets.UTF_8));
            DatabaseEntry theData = new DatabaseEntry(aData.getBytes(StandardCharsets.UTF_8));

            byte[] myKey = theKey.getData();
            byte[] myData = theData.getData();

            String key = new String(myKey, StandardCharsets.UTF_8);
            String data = new String(myData, StandardCharsets.UTF_8);

            System.out.println(key + " - " + data);
        } catch (Exception e) {
            // 异常处理转到这里
        }

        // 存储记录将在本章后面描述
    }
}
