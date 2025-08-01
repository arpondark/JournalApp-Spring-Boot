package com.arpon.JournalApp.Entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "journalentry")
@Data
public class JournalEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private Date date;




}
