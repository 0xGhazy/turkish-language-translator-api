package com.translator.App.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.HashMap;

@Data
@Document(collection="Lessons")
public class Lesson {
    @Id
    private String id;
    private String title;
    private Integer level;
    private String lessonType;
    private String videoUrl;
    private ArrayList<String> trStatements;
    private ArrayList<String> arStatements;
    private ArrayList<String> enStatements;
    private HashMap<String, ArrayList<String>> statementsWords;
    private HashMap<String, HashMap<String, String>> words;
    private long creationTime;
    private long modificationTime;

    @Override
    public String toString() {
        return "Lesson{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", level=" + level +
                ", lessonType='" + lessonType + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", trStatements=" + trStatements +
                ", arStatements=" + arStatements +
                ", enStatements=" + enStatements +
                ", statementsWords=" + statementsWords +
                ", words=" + words +
                ", creationTime=" + creationTime +
                ", modificationTime=" + modificationTime +
                '}';
    }
}
