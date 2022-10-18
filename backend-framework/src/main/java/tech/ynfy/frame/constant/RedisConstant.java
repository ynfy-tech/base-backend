package tech.ynfy.frame.constant;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2022/7/20
 */
public interface RedisConstant {

    /**
     * 防重提交 redis key
     */
    String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 测试缓存key
     */
    String TEST_DEMO_CACHE = "test:demo";

    /*************************** 重复提交 ****************************/

    /**
     * 重复时间
     */
    Integer REPEAT_TIME_SECOND = 10;

    /**
     * 重复时间 - Mills
     */
    Integer REPEAT_TIME_MILL = REPEAT_TIME_SECOND * 1000;



}
