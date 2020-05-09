package ogd.dispatcher.applicationRunner;

import lombok.extern.slf4j.Slf4j;
import ogd.dispatcher.config.DispatcherConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 功能描述 : 项目启动成功后执行
 * </p>
 *
 * @author : Garen Gosling 2020/4/3 下午2:58
 */
@Slf4j
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // TODO 同步配置实现以后，这里要先拉一下配置，然后set配置列表
        DispatcherConfig.getInstance().setAiAppList(null);
    }

}
