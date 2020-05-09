package ogd.dispatcher.core;

import lombok.extern.slf4j.Slf4j;
import ogd.dispatcher.arithmetic.IArithmetic;
import ogd.dispatcher.arithmetic.PollingImpl;
import ogd.dispatcher.arithmetic.RandomImpl;
import ogd.dispatcher.arithmetic.WeightRandomImpl;
import ogd.dispatcher.config.DispatcherConfig;
import ogd.dispatcher.model.AiApp;
import ogd.dispatcher.model.Answer;
import ogd.dispatcher.model.Engine;
import ogd.dispatcher.model.Server;
import ogd.dispatcher.response.DataResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>
 * 功能描述 : 调度器
 * </p>
 *
 * @author : Garen Gosling 2020/5/8 下午6:01
 */
@Slf4j
@Component
public class Dispatcher {

    @Value("${dispatcher.arithmetic}")
    String DISPATCHER_ARITHMETIC;

    @Resource
    RestTemplate restTemplate;

    /**
     * <p>
     * 功能描述 : 调度方法
     * </p>
     *
     * @author : Garen Gosling   2020/5/8 下午6:08
     *
     * @param aiAppId 应用ID
     * @Return void
     **/
    public Answer execute(int aiAppId, String question) {
        // 算法
        IArithmetic iArithmetic = getArithmetic();
        // 应用
        AiApp aiApp = getById(aiAppId);
        // 引擎列表
        List<Engine> engineList = aiApp.getEngineList();
        // 答案列表
        List<Answer> answerList = new ArrayList<>();
        try{
            // 线程池
            ExecutorService service = Executors.newFixedThreadPool(engineList.size());
            for (final Engine engine : engineList) {
                // 通过某个算法，获取服务器
                final Server server = iArithmetic.arithmetic(engine);
                // 引擎
                Future<String> future = service.submit(() -> {
                    // 调用接口（ TODO 现在是专用的，以后要改成通用的 ）
                    String url = "http://" + server.getIp() + ":" + server.getPort() + "/ask/answer?question=" + question;
                    DataResult dataResult = restTemplate.postForObject(url, null, DataResult.class);
                    if (dataResult == null) {
                        log.error("dataResult is null!, url: {}", url);
                        return null;
                    } else if (dataResult.getCode() != 10200) {
                        log.error("dataResult is not 10200, url: {}, message: {}, data: {}", url, dataResult.getMessage(), dataResult.getData());
                        return null;
                    } else {
                        return (String) dataResult.getData();
                    }
                });
                // 答案
                answerList.add(new Answer(aiApp.getId(), aiApp.getName(), engine.getId(), engine.getName(), engine.getType(), server.getId(), server.getName(), server.getIp(), server.getPort(), DISPATCHER_ARITHMETIC, future.get()));
            }
            service.shutdown(); // shutdown方法：平滑的关闭ExecutorService，当此方法被调用时，ExecutorService停止接收新的任务并且等待已经提交的任务（包含提交正在执行和提交未执行）执行完成。当所有提交任务执行完毕，线程池即被关闭。
            service.awaitTermination(1, TimeUnit.MINUTES);  // awaitTermination方法：接收人timeout和TimeUnit两个参数，用于设定超时时间及单位。当等待超过设定时间时，会监测ExecutorService是否已经关闭，若关闭则返回true，否则返回false。一般情况下会和shutdown方法组合使用。
            // 选择答案
            log.info("answerList: {}", answerList);
            return chooseAnswer(answerList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>
     * 功能描述 : 选择答案
     *          专用引擎优先级最高，如果答案不为空就是这个答案；如果专用引擎答案为空，就随便选一个不为空的答案
     * </p>
     *
     * @author : Garen Gosling   2020/5/9 上午10:23
     *
     * @param answerList 答案列表
     * @Return ogd.dispatcher.model.Answer
     **/
    private Answer chooseAnswer(List<Answer> answerList){
        if(CollectionUtils.isEmpty(answerList)) return null;
        Answer answer = null;
        for(Answer a : answerList){
            // 专用引擎优先级最高
            if(a.getEngineType() == 1 && !StringUtils.isEmpty(a.getAnswer())){
                answer = a;
                break;
            }
            // 其他引擎的答案，随便给一个不是空的就好了
            if(!StringUtils.isEmpty(a.getAnswer())){
                answer = a;
                break;
            }
        }
        return answer;
    }

    /**
     * <p>
     * 功能描述 : 通过应用ID获取应用对象
     * </p>
     *
     * @author : Garen Gosling   2020/5/8 下午6:07
     *
     * @param aiAppId 应用ID
     * @Return ogd.dispatcher.model.AiApp
     **/
    private AiApp getById(int aiAppId) {
        List<AiApp> aiAppList = DispatcherConfig.getInstance().getAiAppList();
        for(AiApp aiApp : aiAppList) {
            if(aiAppId == aiApp.getId()) {
                return aiApp;
            }
        }
        throw new RuntimeException("没有与之对应的应用，aiAppId: " + aiAppId);
    }

    /**
     * <p>
     * 功能描述 : 算法枚举
     * </p>
     *
     * @author : Garen Gosling 2020/5/8 下午6:01
     */
    private enum ArithmeticEnum {
        /**
         * 随机
         */
        RANDOM,
        /**
         * 加权随机
         */
        WEIGHT_RANDOM,
        /**
         * 轮询
         */
        POLLING
    }

    /**
     * <p>
     * 功能描述 : 通过配置，获取算法接口的实现
     * </p>
     *
     * @author : Garen Gosling   2020/5/9 上午11:13
     *
     * @param
     * @Return ogd.dispatcher.arithmetic.IArithmetic
     **/
    private IArithmetic getArithmetic() {
        ArithmeticEnum arithmeticEnum = ArithmeticEnum.valueOf(DISPATCHER_ARITHMETIC);
        IArithmetic iArithmetic = null;
        switch (arithmeticEnum) {
            case RANDOM:
                iArithmetic = new RandomImpl();
                break;
            case WEIGHT_RANDOM:
                iArithmetic = new WeightRandomImpl();
                break;
            case POLLING:
                iArithmetic = new PollingImpl();
                break;
            default:
                break;
        }
        return iArithmetic;
    }
}
