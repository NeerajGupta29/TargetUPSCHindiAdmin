package target.upsc.hindiadmin;

public class ProductInfoShort
{
    String name,uri,pid,uploadSamay;

    public ProductInfoShort() {
    }

    public ProductInfoShort(String name, String uri, String uploadSamay) {
        this.name = name;
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
