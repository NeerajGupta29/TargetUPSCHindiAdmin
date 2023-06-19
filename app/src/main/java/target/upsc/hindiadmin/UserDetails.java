package target.upsc.hindiadmin;

public class UserDetails {
    String uname,email,aim,phoneno,uid;

    public UserDetails() {

    }

    public UserDetails(String uname, String email, String aim, String phoneno) {
        this.uname = uname;
        this.email = email;
        this.aim = aim;
        this.phoneno = phoneno;

    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
