package beSen.future.simpleDesign;

/**
 * @author 康盼Java开发工程师
 */
public class RealData implements Data {

    protected final String result;

    public RealData(String para) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ; i < para.length(); i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {

            }
        }
        this.result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
