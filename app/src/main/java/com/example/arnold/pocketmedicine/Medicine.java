package com.example.arnold.pocketmedicine;

/**
 * Created by Arnold on 4/23/2015.
 */
public class Medicine
{
    private long id;
    private String genericName;
    private String tradeName;
    private String classification;
    private String highRisk;
    private String authorizedForCommunityAdmin;
    private String authorizedForFirstDose;
    private String RPNAppropriate;
    private String approvedRoute;
    private String specialInstructions;

    public long getId() {
        return id;
    }

    public String getGenericName() {
        return genericName;
    }

    public String getTradeName() {
        return tradeName;
    }

    public String getClassification() {
        return classification;
    }

    public String getHighRisk() {
        return highRisk;
    }

    public String getAuthorizedForCommunityAdmin() {
        return authorizedForCommunityAdmin;
    }

    public String getAuthorizedForFirstDose() {
        return authorizedForFirstDose;
    }

    public String getRPNAppropriate() {
        return RPNAppropriate;
    }

    public String getApprovedRoute() {
        return approvedRoute;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setHighRisk(String highRisk) {
        this.highRisk = highRisk;
    }

    public void setAuthorizedForCommunityAdmin(String authorizedForCommunityAdmin) {
        this.authorizedForCommunityAdmin = authorizedForCommunityAdmin;
    }

    public void setAuthorizedForFirstDose(String authorizedForFirstDose) {
        this.authorizedForFirstDose = authorizedForFirstDose;
    }

    public void setRPNAppropriate(String RPNAppropriate) {
        this.RPNAppropriate = RPNAppropriate;
    }

    public void setApprovedRoute(String approvedRoute) {
        this.approvedRoute = approvedRoute;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Medicine(long id, String genericName, String tradeName, String classification, String highRisk, String authorizedForCommunityAdmin, String authorizedForFirstDose, String RPNAppropriate, String approvedRoute, String specialInstructions) {
        this.id = id;
        this.genericName = genericName;
        this.tradeName = tradeName;
        this.classification = classification;
        this.highRisk = highRisk;
        this.authorizedForCommunityAdmin = authorizedForCommunityAdmin;
        this.authorizedForFirstDose = authorizedForFirstDose;
        this.RPNAppropriate = RPNAppropriate;
        this.approvedRoute = approvedRoute;
        this.specialInstructions = specialInstructions;
    }

    public Medicine()
    {
        id = 0;
        genericName = "";
        tradeName = "";
        classification = "";
        highRisk = "";
        authorizedForCommunityAdmin = "";
        authorizedForFirstDose = "";
        RPNAppropriate = "";
        approvedRoute = "";
        specialInstructions = "";
    }

    @Override
    public String toString() {
        return genericName;
    }
}