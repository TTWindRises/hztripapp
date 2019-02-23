package com.baidu.myapp.bean.scenic;

import com.baidu.myapp.util.Debbuger;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/11/1.
 */

public class ScenicProject extends DataSupport implements Serializable{
    @Column(unique = true)
    private int projectID;//项目的ID
    private String projectName;//游玩的项目名称
    private String projectImg;//项目的介绍的图片
    private String projectDescribe;//项目的文字描述
    private String projectPrice;//项目的价格
    private int scenic_id;

    public int getScenic_id() {
        return scenic_id;
    }

    public void setScenic_id(int scenic_id) {
        this.scenic_id = scenic_id;
    }

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

    public void saveAllProject(List<ScenicProject> projects) {
        for (ScenicProject project : projects) {
            if (project.save()) {

            } else {
                Debbuger.LogE("项目信息保存失败");
            }
        }
    }

}
