package ogd.http.httpClient;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/5/5 上午11:07
 */
class Base {
    private static String BASE_URL = "http://localhost:8080/httpHello/";
    static String PATH_GET = BASE_URL + "get";
    static String PATH_POST = BASE_URL + "post";
    static String PATH_GET_WITH_PARAMS = BASE_URL + "getWithParams";
    static String PATH_POST_WITH_PARAMS = BASE_URL + "postWithParams";
    static String PATH_POST_WITH_ENTITY_PARAM = BASE_URL + "postWithEntityParam";
    static String PATH_POST_WITH_PARAMS_AND_ENTITY_PARAM = BASE_URL + "postWithParamsAndEntityParam";
    static String PATH_FILE = BASE_URL + "file";
}
