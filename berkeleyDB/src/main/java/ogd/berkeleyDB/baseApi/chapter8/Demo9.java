package ogd.berkeleyDB.baseApi.chapter8;

import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.je.DatabaseEntry;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 下午3:52
 */
public class Demo9 {

    public static void main(String[] args) {
        TupleBinding keyBinding = new MyTupleBinding();

        MyData2 theKeyData = new MyData2();
        theKeyData.setLongData(123456789L);
        theKeyData.setDoubleData(12345.6789);
        theKeyData.setDescription("My key data");

        DatabaseEntry myKey = new DatabaseEntry();

        try {
            // Store theKeyData in the DatabaseEntry
            keyBinding.objectToEntry(theKeyData, myKey);

            // Database put and get activity omitted for clarity

            // Retrieve the key data
            theKeyData = (MyData2) keyBinding.entryToObject(myKey);
            System.out.println(theKeyData);
        } catch (Exception e) {
            // Exception handling goes here
        }

    }
}
