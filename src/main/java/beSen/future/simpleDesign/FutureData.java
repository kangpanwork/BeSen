package beSen.future.simpleDesign;

/**
 * @author 康盼Java开发工程师
 */
public class FutureData implements Data {

    protected RealData realDate = null;
    protected boolean isReady = false;
    public synchronized void setRealData(RealData realData) {
        if (this.isReady) {
            return;
        }
        this.realDate = realData;
        this.isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while(!isReady) {
            try {
                wait();
            } catch(InterruptedException e) {}
        }
        return realDate.getResult();

    }



}
