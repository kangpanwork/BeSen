package beSen.timerTask;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * http://localhost:8080/servlet/service/BsTimer?name=kangpan
 */
@Component("ITimer.BsTimer")
public class BsTimer implements ITimer{

    @Override
    public void runTask(Map<String, String> map) {
        map.forEach((k,v) -> System.out.println(k + ":" + v));
        System.out.println("do something...");
    }
}
