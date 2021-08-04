package beSen.test.test;

import org.springframework.stereotype.Component;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class ControllerService extends  DefaultService implements IControllerService {


    @Override
    public void batchAgreeOrRejectOption(String option) {
        // 一些必填参数校验
        agreeOrRejectOption(option);
        // 组装参数返回前端
    }
}
