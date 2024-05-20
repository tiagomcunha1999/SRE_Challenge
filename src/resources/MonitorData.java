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

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MonitorData{" +
                "url='" + url + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}