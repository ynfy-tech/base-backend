package tech.ynfy.frame.constant;

/**
 * 〈〉
 *
 * @author Hsiong
 * @version 1.0.0
 * @since 2022/7/20
 */
public interface RepeatConstant {

    /*************************** 重复提交 ****************************/

    /**
     * 重复时间
     */
    Integer REPEAT_TIME_SECOND = 10;

    /**
     * 重复时间 - Mills
     */
    Integer REPEAT_TIME_MILL = REPEAT_TIME_SECOND * 1000;

    /**
     * RepeatSubmit - key - time
     */
    String REPEAT_TIME_KEY = "repeatTime";

}
