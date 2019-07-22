package spring.adog.dto;

import spring.adog.model.User;

public class QuestionDTO {
    private Long ID;
    private String TITLE;
    private String DESCRIPTION;
    private long GMTCREATE;
    private long GMTMODIFIED;
    private Long CREATORID;
    private Integer COMMENT_COUNT;
    private Integer VIEW_COUNT;
    private Integer LIKE_COUNT;
    private String TAG;
    private User USER;


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getCREATORID() {
        return CREATORID;
    }

    public void setCREATORID(Long CREATORID) {
        this.CREATORID = CREATORID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public long getGMTCREATE() {
        return GMTCREATE;
    }

    public void setGMTCREATE(long GMTCREATE) {
        this.GMTCREATE = GMTCREATE;
    }

    public long getGMTMODIFIED() {
        return GMTMODIFIED;
    }

    public void setGMTMODIFIED(long GMTMODIFIED) {
        this.GMTMODIFIED = GMTMODIFIED;
    }

    public Integer getCOMMENT_COUNT() {
        return COMMENT_COUNT;
    }

    public void setCOMMENT_COUNT(Integer COMMENT_COUNT) {
        this.COMMENT_COUNT = COMMENT_COUNT;
    }

    public Integer getVIEW_COUNT() {
        return VIEW_COUNT;
    }

    public void setVIEW_COUNT(Integer VIEW_COUNT) {
        this.VIEW_COUNT = VIEW_COUNT;
    }

    public Integer getLIKE_COUNT() {
        return LIKE_COUNT;
    }

    public void setLIKE_COUNT(Integer LIKE_COUNT) {
        this.LIKE_COUNT = LIKE_COUNT;
    }

    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public User getUSER() {
        return USER;
    }

    public void setUSER(User USER) {
        this.USER = USER;
    }
}
