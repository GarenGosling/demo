package ogd.dispatcher.arithmetic;

import ogd.dispatcher.model.Engine;
import ogd.dispatcher.model.Server;

import java.util.List;

/**
 * <p>
 * 功能描述 : 算法接口
 * </p>
 *
 * @author : Garen Gosling 2020/5/9 上午10:00
 */
public interface IArithmetic {
    /**
     * <p>
     * 功能描述 : 算法
     * </p>
     *
     * @author : Garen Gosling   2020/5/9 上午11:29
     *
     * @param engine 引擎
     * @Return ogd.dispatcher.model.Server
     **/
    Server arithmetic(Engine engine);
}
