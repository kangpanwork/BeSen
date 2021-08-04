package beSen.test.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 康盼Java开发工程师
 */
@Component
public class DefaultService extends AbstractService {

    protected Map<String,String> cache = new HashMap<>();

    @Autowired
    private ICellService cellService;

    @Autowired
    private ILogCellService logCellService;

    @Override
    public void batchAgreeAndReject() {
        agreeOrRejectOption("");
    }

    @Override
    public void agreeAndReject() {
        cellService.agreeAndReject();
    }

    @Override
    public boolean isAllCommit() {
        String str =  cellService.queryCountAndPreNodeInfo();
        cache.put("queryCountAndPreNodeInfo",str);
        String[] arr = str.split(",");
        return  arr[0].equals(arr[1]) && "FINISH".equals(arr[2]);
    }

    @Override
    public Object procedureRecord() {
        return cellService.procedureRecord();
    }


    @Override
    public void openPreviousNode() {
        cellService.updateNode();
    }

    @Override
    public void closeCurrentNode() {
        cellService.updateNode();
    }


    @Override
    public void closeCurrentNodeAndOpenNextNodeForYun() {
        // 行云处理
    }

    @Override
    protected boolean isFirstCommit() {
        String str =  cache.get("queryCountAndPreNodeInfo");
        String[] arr = str.split(",");
        return  "0".equals(arr[1]);
    }

    @Override
    public void initData() {
        cellService.queryInitStartData();
        cellService.batchUpdate();
        cellService.defaultGeneratorData();
    }

    @Override
    protected void saveRecord(Object obj) {
        logCellService.saveLog();
    }
}
