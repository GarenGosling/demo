package ogd.berkeleyDB.easyDPL.config;

import lombok.extern.slf4j.Slf4j;
import ogd.berkeleyDB.easyDPL.dplPlus.DplConfig;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${BerkeleyDB.envPath}")
    private String ENV_PATH;

    @Value("${BerkeleyDB.storeName}")
    private String STORE_NAME;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        DplConfig.getInstance().init(ENV_PATH, STORE_NAME);
    }
}
