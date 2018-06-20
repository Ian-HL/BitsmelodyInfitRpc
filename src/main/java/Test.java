import Infit.IBIData;
import com.google.gson.Gson;
import com.soarhealth.ecg.hrvlib.HRV;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by h2ero on 5/14/17.
 */
public class Test {
    private static Logger logger = Logger.getLogger("HRVLib_Test");

    static {
        PropertyConfigurator.configure("log4j.properties");
    }

    static HRV hrv_processor;
    static {
        try {
            hrv_processor = new HRV();
        } catch (Exception e) {
            logger.error("Failed to initialize HRV",e);
        }
    }
    public static void main(String[] args) {

        boolean useRaw = false;
        int mode = 0;
        Map<String,Object> user_info = new HashMap<String, Object>();
        user_info.put("age", "25");
        user_info.put("gender", "male");

        try {
            String fpath = "/Users/h2ero/h2ero/www/infit-java/data/sampledata.ibi";
            Map<String,Object> result = testBitnessIBIFile(fpath,user_info,mode);
            Gson gson = new Gson();
            String json = gson.toJson(result);
            System.out.println(json);
        } catch (Exception ex) {
            logger.error("HRV Analysis failed",ex);
        }
    }

    private static Map<String,Object> testBitnessIBIFile(String fpath, Map<String,Object> user_info, int mode)
    {
        Map<String,Object> result = null;
        try {
            double[][] ibi = GetIBIData(fpath,1150);
            double[] ibiTime = new double[ibi.length];
            double[] ibiTimePos = {1};
            if (ibi[0].length == 1) {
                for (int i = 1; i < ibi.length; i++) {
                    ibiTime[i] = ibi[i-1][0] + ibi[i][0];
                }
            }
            System.out.println(Arrays.deepToString(ibi));


            IBIData ibidata = new IBIData();
            ibidata.setIbi(ibi);
            ibidata.setIbiTime(ibiTime);
            ibidata.setIbiTimePos(ibiTimePos);
            Gson json = new Gson();
            System.out.print(json.toJson(ibidata));
            result = hrv_processor.doHrvAnalysis(ibi, ibiTime, ibiTimePos, user_info, mode);
        } catch (Exception ex) {
            logger.error("Failed to analysis ibi file",ex);
            System.out.println(ex.getMessage());
        }
        return result;
    }
    private static double[][] GetIBIData(String fpath, int max_cnt)
    {
        double[][] ibi = new double[max_cnt][1];
        try {
            BufferedReader br =
                    new BufferedReader(new FileReader(fpath));
            int idx = 0;
            while(true)
            {
                String line = br.readLine();
                if (!line.contains(","))
                {
                    if (line == null || idx >= max_cnt)
                        break;
                    ibi[idx++][0] = Double.valueOf(line);
                }
                else
                {
                    if (line == null || idx >= max_cnt)
                        break;
                    String[] tmp = line.split(",");
                    ibi[idx++][0] = Double.valueOf(tmp[1]);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return ibi;
    }



}
