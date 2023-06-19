package target.upsc.hindiadmin;

public class ProductInfo
{
    String name,desc,uri,pid,uploadSamay;

    public ProductInfo() {
    }

    public ProductInfo(String name, String desc, String uri, String uploadSamay) {
        this.name = name;
        this.desc = desc;
        this.uri = uri;
        this.uploadSamay=uploadSamay;

    }

    public String getPid() { return pid; }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUploadSamay() {
        return uploadSamay;
    }

    public void setUploadSamay(String uploadSamay) {
        this.uploadSamay = uploadSamay;
    }
}
