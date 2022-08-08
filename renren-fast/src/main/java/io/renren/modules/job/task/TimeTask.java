package io.renren.modules.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ Author     ：苏俊滨
 * @ Date       ：Created in 0:54 2022/8/8
 * @ Description：${description}
 **/
@Component("timeTask")
public class TimeTask implements ITask {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void run(String params) {
        logger.debug("TestTask定时任务正在执行，参数为：{}", params);
    }
}
