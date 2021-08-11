package beSen.test.test;

import org.springframework.stereotype.Component;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class VersionServiceImplV2 implements IVersionService {
    @Override
    public String getVersion() {
        return "V2";
    }
}
