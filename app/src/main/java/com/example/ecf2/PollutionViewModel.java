package com.example.ecf2;

import android.arch.lifecycle.ViewModel;

import java.util.Date;

public class PollutionViewModel extends ViewModel {
    private Integer pm25;
    private Integer pm10;
    private String aqius;
    private String aqicn;
    private String mainus;
    private String maincn;
    private Date lastUpdate;

    public Integer getPm25() {
        return pm25;
    }

    public void setPm25(Integer pm25) {
        this.pm25 = pm25;
    }

    public Integer getPm10() {
        return pm10;
    }

    public void setPm10(Integer pm10) {
        this.pm10 = pm10;
    }

    public String getAqius() {
        return aqius;
    }

    public void setAqius(String aqius) {
        this.aqius = aqius;
    }

    public String getAqicn() {
        return aqicn;
    }

    public void setAqicn(String aqicn) {
        this.aqicn = aqicn;
    }

    public String getMainus() {
        return mainus;
    }

    public void setMainus(String mainus) {
        this.mainus = mainus;
    }

    public String getMaincn() {
        return maincn;
    }

    public void setMaincn(String maincn) {
        this.maincn = maincn;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
