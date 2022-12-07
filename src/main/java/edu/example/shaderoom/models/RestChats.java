package edu.example.shaderoom.models;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.Date;

public class RestChats extends BaseChats{

    private DocumentReference author;

    public RestChats(String objectId, String title, String content, Date createdAt, DocumentReference author)
    {
        this.objectId = objectId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
    }

    public RestChats(String objectId, String title, String content, Date createdAt, String author)
    {
        this.objectId = objectId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.setAuthor(author) ;
    }

    public DocumentReference getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        Firestore db = FirestoreClient.getFirestore();
        this.author = db.collection("User").document(author);
    }

}
