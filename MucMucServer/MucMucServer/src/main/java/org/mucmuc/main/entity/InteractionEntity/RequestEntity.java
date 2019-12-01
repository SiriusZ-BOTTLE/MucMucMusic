package org.mucmuc.main.entity.InteractionEntity;


import java.io.Serializable;

@Deprecated//废弃,不再使用
//请求参数封装实体类
public class RequestEntity implements Serializable {
    private static final long serialVersionUID = 3401781428101852075L;

    /**
     * 请求响应数据
     */
    private String treasureData;

    public String getTreasureData() {
        return treasureData;
    }

    public void setTreasureData(String treasureData) {
        this.treasureData = treasureData;
    }

}
