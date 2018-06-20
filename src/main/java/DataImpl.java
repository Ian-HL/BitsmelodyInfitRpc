import  Infit.*;

import com.google.gson.Gson;
import com.soarhealth.ecg.hrvlib.HRV;

import org.apache.thrift.TException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
class DataImpl implements Data.Iface {
    public DataImpl() {}
    static HRV hrv_processor;
    static {
        try {
            hrv_processor = new HRV();
        } catch (Exception e) {
            //logger.error("Failed to initialize HRV",e);
        }
    }
    public String Analysis(String param) throws TException {
        Gson gson = new Gson();
        String str = param;
        IBIData obj = gson.fromJson(str, IBIData.class);

        double[][] ibi = obj.getIbi();        
        double[] ibiTime = obj.getIbiTime();
        double[] ibiTimePos = obj.getIbiTimePos();
        Map<String,Object> userInfo = new HashMap<String, Object>();
        userInfo.put("age", obj.getUserInfo().getAge());
        userInfo.put("gender", obj.getUserInfo().getGender());
        int mode = 0;
        Map<String,Object> result = null;
        try {
            System.out.print("Being called doHrvAnalysis()");
            result = hrv_processor.doHrvAnalysis(ibi, ibiTime, ibiTimePos, userInfo, mode);
            System.out.println("hrv data"+result);
        }catch (Exception ex){
            System.out.println("error"+ex.getMessage());
        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Gson json =  new Gson();
//        System.out.println(json.toJson(result));
        return json.toJson(result);
    }
}