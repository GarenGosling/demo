package ogd.http.entity;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/5/5 上午11:07
 */
public class Base {
    private static String BASE_URL = "http://localhost:8080/httpHello/";
    public static String PATH_GET = BASE_URL + "get";
    public static String PATH_POST = BASE_URL + "post";
    public static String PATH_GET_WITH_PARAMS = BASE_URL + "getWithParams";
    public static String PATH_POST_WITH_PARAMS = BASE_URL + "postWithParams";
    public static String PATH_POST_WITH_ENTITY_PARAM = BASE_URL + "postWithEntityParam";
    public static String PATH_POST_WITH_PARAMS_AND_ENTITY_PARAM = BASE_URL + "postWithParamsAndEntityParam";
    public static String PATH_FILE = BASE_URL + "file";
}
