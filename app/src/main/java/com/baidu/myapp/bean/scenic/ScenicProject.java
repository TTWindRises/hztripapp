package com.baidu.myapp.bean.scenic;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/11/1.
 */

public class ScenicProject extends DataSupport{
    private int projectID;//项目的ID
    private String projectName;//游玩的项目名称
    private String projectImg;//项目的介绍的图片
    private String projectDescribe;//项目的文字描述
    private String projectPrice;//项目的价格
    private ScenicBean scenicBean;//一个项目组是对应一个景区的

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectImg() {
        return projectImg;
    }

    public void setProjectImg(String projectImg) {
        this.projectImg = projectImg;
    }

    public String getProjectDescribe() {
        return projectDescribe;
    }

    public void setProjectDescribe(String projectDescribe) {
        this.projectDescribe = projectDescribe;
    }

    public String getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(String projectPrice) {
        this.projectPrice = projectPrice;
    }

    public ScenicBean getScenicBean() {
        return scenicBean;
    }

    public void setScenicBean(ScenicBean scenicBean) {
        this.scenicBean = scenicBean;
    }
}
