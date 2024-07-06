package com.translator.App.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Rules Lessons")
public class RuleLesson {
    @Id
    private String id;
    private String title;
    private String type;
    private String videoUrl;
    private long creationTime;
    private long modificationTime;

    @Override
    public String toString() {
        return "RuleLesson{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", creationTime=" + creationTime +
                ", modificationTime=" + modificationTime +
                '}';
    }
}
