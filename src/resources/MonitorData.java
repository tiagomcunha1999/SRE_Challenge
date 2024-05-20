package resources;

import java.io.Serializable;

public class MonitorData implements Serializable {
    private String url;
    private String status;

    public MonitorData(String url, String status) {
        this.url = url;
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public String getStatus() {
        return status;
    }
}