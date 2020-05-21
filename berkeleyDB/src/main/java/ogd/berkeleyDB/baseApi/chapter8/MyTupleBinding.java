package ogd.berkeleyDB.baseApi.chapter8;

import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.bind.tuple.TupleInput;
import com.sleepycat.bind.tuple.TupleOutput;

/**
 * <p>
 * 功能描述 : 自定义元组绑定
 * </p>
 *
 * @author : Garen Gosling 2020/5/21 下午3:50
 */
public class MyTupleBinding extends TupleBinding {

    // Write a MyData2 object to a TupleOutput
    public void objectToEntry(Object object, TupleOutput to) {

        MyData2 myData = (MyData2)object;

        // Write the data to the TupleOutput (a DatabaseEntry).
        // Order is important. The first data written will be
        // the first bytes used by the default comparison routines.
        to.writeDouble(myData.getDoubleData());
        to.writeLong(myData.getLongData());
        to.writeString(myData.getDescription());
    }

    // Convert a TupleInput to a MyData2 object
    public Object entryToObject(TupleInput ti) {

        // Data must be read in the same order that it was
        // originally written.
        Double theDouble = ti.readDouble();
        long theLong = ti.readLong();
        String theString = ti.readString();

        MyData2 myData = new MyData2();
        myData.setDoubleData(theDouble);
        myData.setLongData(theLong);
        myData.setDescription(theString);

        return myData;
    }
}
