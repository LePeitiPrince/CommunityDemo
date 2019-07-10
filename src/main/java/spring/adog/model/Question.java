package spring.adog.model;

public class Question {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.ID
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    private Integer ID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.TITLE
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    private String TITLE;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.GMTCREATE
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    private Long GMTCREATE;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.GMTMODIFIED
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    private Long GMTMODIFIED;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.CREATORID
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    private Integer CREATORID;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    private Integer COMMENT_COUNT;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.VIEW_COUNT
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    private Integer VIEW_COUNT;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.LIKE_COUNT
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    private Integer LIKE_COUNT;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.TAG
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    private String TAG;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.DESCRIPTION
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    private String DESCRIPTION;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.ID
     *
     * @return the value of QUESTION.ID
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public Integer getID() {
        return ID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.ID
     *
     * @param ID the value for QUESTION.ID
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.TITLE
     *
     * @return the value of QUESTION.TITLE
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public String getTITLE() {
        return TITLE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.TITLE
     *
     * @param TITLE the value for QUESTION.TITLE
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public void setTITLE(String TITLE) {
        this.TITLE = TITLE == null ? null : TITLE.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.GMTCREATE
     *
     * @return the value of QUESTION.GMTCREATE
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public Long getGMTCREATE() {
        return GMTCREATE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.GMTCREATE
     *
     * @param GMTCREATE the value for QUESTION.GMTCREATE
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public void setGMTCREATE(Long GMTCREATE) {
        this.GMTCREATE = GMTCREATE;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.GMTMODIFIED
     *
     * @return the value of QUESTION.GMTMODIFIED
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public Long getGMTMODIFIED() {
        return GMTMODIFIED;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.GMTMODIFIED
     *
     * @param GMTMODIFIED the value for QUESTION.GMTMODIFIED
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public void setGMTMODIFIED(Long GMTMODIFIED) {
        this.GMTMODIFIED = GMTMODIFIED;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.CREATORID
     *
     * @return the value of QUESTION.CREATORID
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public Integer getCREATORID() {
        return CREATORID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.CREATORID
     *
     * @param CREATORID the value for QUESTION.CREATORID
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public void setCREATORID(Integer CREATORID) {
        this.CREATORID = CREATORID;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.COMMENT_COUNT
     *
     * @return the value of QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public Integer getCOMMENT_COUNT() {
        return COMMENT_COUNT;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.COMMENT_COUNT
     *
     * @param COMMENT_COUNT the value for QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public void setCOMMENT_COUNT(Integer COMMENT_COUNT) {
        this.COMMENT_COUNT = COMMENT_COUNT;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.VIEW_COUNT
     *
     * @return the value of QUESTION.VIEW_COUNT
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public Integer getVIEW_COUNT() {
        return VIEW_COUNT;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.VIEW_COUNT
     *
     * @param VIEW_COUNT the value for QUESTION.VIEW_COUNT
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public void setVIEW_COUNT(Integer VIEW_COUNT) {
        this.VIEW_COUNT = VIEW_COUNT;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.LIKE_COUNT
     *
     * @return the value of QUESTION.LIKE_COUNT
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public Integer getLIKE_COUNT() {
        return LIKE_COUNT;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.LIKE_COUNT
     *
     * @param LIKE_COUNT the value for QUESTION.LIKE_COUNT
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public void setLIKE_COUNT(Integer LIKE_COUNT) {
        this.LIKE_COUNT = LIKE_COUNT;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.TAG
     *
     * @return the value of QUESTION.TAG
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public String getTAG() {
        return TAG;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.TAG
     *
     * @param TAG the value for QUESTION.TAG
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public void setTAG(String TAG) {
        this.TAG = TAG == null ? null : TAG.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.DESCRIPTION
     *
     * @return the value of QUESTION.DESCRIPTION
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.DESCRIPTION
     *
     * @param DESCRIPTION the value for QUESTION.DESCRIPTION
     *
     * @mbg.generated Wed Jul 10 19:22:24 CST 2019
     */
    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION == null ? null : DESCRIPTION.trim();
    }
}