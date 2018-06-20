
package Infit;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IBIData {

    @SerializedName("ibi")
    @Expose
    private double[][] ibi = null;
    @SerializedName("ibiTime")
    @Expose
    private double[] ibiTime = null;
    @SerializedName("ibiTimePos")
    @Expose
    private double[] ibiTimePos = null;
    @SerializedName("userInfo")
    @Expose
    private UserInfo userInfo;

    public double[][] getIbi() {
        return ibi;
    }

    public void setIbi(double[][] ibi) {
        this.ibi = ibi;
    }

    public double[] getIbiTime() {
        return ibiTime;
    }

    public void setIbiTime(double[] ibiTime) {
        this.ibiTime = ibiTime;
    }

    public double[] getIbiTimePos() {
        return ibiTimePos;
    }

    public void setIbiTimePos(double[] ibiTimePos) {
        this.ibiTimePos = ibiTimePos;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
