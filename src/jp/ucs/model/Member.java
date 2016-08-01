package jp.ucs.model;

import java.io.Serializable;
/**
 * 会員Ｂｅａｎクラス
 * @author aungkowin
 *
 */
public class Member implements Serializable {
    private String Id;
    private String pass;
    private String depertmentId;
    private String depertmentName;
    private boolean iskaiinn;    
    /**
     * コンストラクター
     */
    public Member() {
    }
    /**
     * コンストラクター
     * @param id
     * @param pass
     */
    public Member(String id, String pass) {
        this.Id = id;
        this.pass = pass;
    }
    /**
     * コンストラクター
     * @param id
     * @param pass
     * @param depertmentId
     * @param depertmentName
     * @param iskaiinn
     */
    public Member(String id, String pass, String depertmentId, String depertmentName, boolean iskaiinn) {
        this.Id = id;
        this.pass = pass;
        this.depertmentId = depertmentId;
        this.depertmentName = depertmentName;
        this.iskaiinn = iskaiinn;
    }
    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getDepertmentId() {
        return depertmentId;
    }
    public void setDepertmentId(String depertmentId) {
        this.depertmentId = depertmentId;
    }
    public String getDepertmentName() {
        return depertmentName;
    }
    public void setDepertmentName(String depertmentName) {
        this.depertmentName = depertmentName;
    }
    public boolean iskaiinn() {
        return iskaiinn;
    }
    public void setIskaiinn(boolean iskaiinn) {
        this.iskaiinn = iskaiinn;
    }
    
    
}
