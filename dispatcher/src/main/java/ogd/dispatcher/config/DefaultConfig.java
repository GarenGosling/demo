package ogd.dispatcher.config;

import ogd.dispatcher.arithmetic.ArithmeticEnum;
import ogd.dispatcher.model.AiApp;
import ogd.dispatcher.model.Engine;
import ogd.dispatcher.model.Server;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 功能描述 : 默认配置
 * </p>
 *
 * @author : Garen Gosling 2020/5/8 下午2:34
 */
public class DefaultConfig {
    /**
     * <p>
     * 功能描述 : 初始化
     * </p>
     *
     * @author : Garen Gosling   2020/5/8 下午2:16
     *
     * @param
     * @Return void
     **/
    public List<AiApp> initAiAppList() {
        // servers
        Server server1 = createDefaultServer(1, "192.168.24.93", 8091, 40);
        Server server2 = createDefaultServer(2, "192.168.24.93", 8092, 5);
        Server server3 = createDefaultServer(3, "192.168.24.93", 8093, 1);
        Server server4 = createDefaultServer(4, "192.168.24.93", 8094, 40);
        Server server5 = createDefaultServer(5, "192.168.24.93", 8095, 5);
        Server server6 = createDefaultServer(6, "192.168.24.93", 8096, 1);
        Server server7 = createDefaultServer(7, "192.168.24.93", 8097, 40);
        Server server8 = createDefaultServer(8, "192.168.24.93", 8098, 5);
        Server server9 = createDefaultServer(9, "192.168.24.93", 8099, 1);
        // serverLists
        List<Server> serverList1 = new ArrayList<>();
        List<Server> serverList2 = new ArrayList<>();
        List<Server> serverList3 = new ArrayList<>();
        // add values to serverLists
        serverList1.add(server1);
        serverList1.add(server2);
        serverList1.add(server3);
        serverList2.add(server4);
        serverList2.add(server5);
        serverList2.add(server6);
        serverList3.add(server7);
        serverList3.add(server8);
        serverList3.add(server9);
        // engines
        Engine engine1 = createDefaultEngine(1, 1, serverList1);    // 专用引擎
        Engine engine2 = createDefaultEngine(2, 0, serverList2);    // 通用引擎
        Engine engine3 = createDefaultEngine(3, 0, serverList3);    // 通用引擎
        // engineList
        List<Engine> engineList = new ArrayList<>();
        engineList.add(engine1);
        engineList.add(engine2);
        engineList.add(engine3);
        // aiApp
        AiApp aiApp = createDefaultAiApp(1, engineList);
        // aiAppList
        List<AiApp> aiAppList = new ArrayList<>();
        aiAppList.add(aiApp);
        return aiAppList;
    }

    /**
     * <p>
     * 功能描述 : 创建默认 AI 应用
     * </p>
     *
     * @author : Garen Gosling   2020/5/8 上午11:59
     *
     * @param
     * @Return ogd.dispatcher.model.AiApp
     **/
    private AiApp createDefaultAiApp(int id, List<Engine> engineList) {
        AiApp aiApp = new AiApp();
        aiApp.setId(id);
        aiApp.setName("默认应用");
        aiApp.setDescription("开发阶段使用的默认应用");
        aiApp.setArithmetic(ArithmeticEnum.POLLING.name());     // 算法
        aiApp.setEngineList(engineList);
        return aiApp;
    }

    /**
     * <p>
     * 功能描述 : 创建默认引擎
     * </p>
     *
     * @author : Garen Gosling   2020/5/8 下午12:01
     *
     * @param id ID
     * @Return ogd.dispatcher.model.Engine
     **/
    private Engine createDefaultEngine(int id, int type, List<Server> serverList) {
        Engine engine = new Engine();
        engine.setId(id);
        engine.setName("默认引擎" + id);
        engine.setDescription("开发阶段使用的默认引擎" + id);
        engine.setType(type);
        engine.setServerList(serverList);
        return engine;
    }

    /**
     * <p>
     * 功能描述 : 创建默认服务器
     * </p>
     *
     * @author : Garen Gosling   2020/5/8 下午12:03
     *
     * @param id ID
     * @param ip IP
     * @param port 端口
     * @Return ogd.dispatcher.model.Server
     **/
    private Server createDefaultServer(int id, String ip, Integer port, Integer weight) {
        Server server = new Server();
        server.setId(id);
        server.setName("默认服务器" + id);
        server.setIp(ip);
        server.setPort(port);
        server.setWeight(weight);
        return server;
    }
}
