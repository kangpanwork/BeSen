package beSen.test.test;

import beSen.bean.beanPostProcessor.Injected;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author 康盼Java开发工程师
 */
@Component
@RequestMapping("/v")
public class ControllerService extends  DefaultService implements IControllerService {

    @Injected
    private IVersionService versionService;

    @Override
    public void batchAgreeOrRejectOption(String option) {
        // 一些必填参数校验
        batchAgreeAndReject();
        // 组装参数返回前端
    }

    @GetMapping(path = "/getVersion")
    @ResponseBody
    public String getVersion() {
        return versionService.getVersion();
    }
}
