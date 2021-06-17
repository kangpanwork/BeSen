package beSen.timerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/servlet/service/*")
public class TimerHandler extends HttpServlet {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    {
        String path = req.getRequestURI();
        String[] splitPath = path.split("/");
        if (splitPath.length == 4) {
            String bean = splitPath[3];
            ITimer timer = (ITimer) applicationContext.getBean("ITimer." + bean);
            Map<String, String> parameter = new HashMap<>();
            Map<String, String[]> map = req.getParameterMap();
            for(Map.Entry<String,String[]> re : map.entrySet()) {
                parameter.put(re.getKey(),re.getValue()[0]);
            }
            timer.runTask(parameter);
        } else {
            throw new IllegalArgumentException("the request url must like /servlet/service/taskName?key=value");
        }
        try {
            resp.getWriter().write("the task run success...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
