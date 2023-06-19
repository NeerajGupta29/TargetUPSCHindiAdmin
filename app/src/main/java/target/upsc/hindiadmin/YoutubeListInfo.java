package target.upsc.hindiadmin;

public class YoutubeListInfo {
    String headLine, videoId, pid, uploadSamay;

    public YoutubeListInfo() {
    }

    public YoutubeListInfo(String headLine, String videoId, String uploadSamay) {
        this.headLine = headLine;
        this.videoId = videoId;
        this.uploadSamay = uploadSamay;

    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUploadSamay() {
        return uploadSamay;
    }

    public void setUploadSamay(String uploadSamay) {
        this.uploadSamay = uploadSamay;
    }
}


