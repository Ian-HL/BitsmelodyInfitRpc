import Infit.IBIData;
import com.google.gson.Gson;

/**
 * Created by h2ero on 5/14/17.
 */
public class TestJson {
    public static void main(String[] args) {
        Gson gson = new Gson();
        String str = "{\"ibi\":[[1.1]],\"ibiTime\":[1.1],\"ibiTimePos\":[1.1],\"userInfo\":{\"gender\":\"male\",\"age\":\"25\"}}";
        IBIData obj = gson.fromJson(str, IBIData.class);
        System.out.println(obj.getUserInfo().getAge());
        System.out.println(System.getenv("DYLD_LIBRARY_PATH"));
    }
}
