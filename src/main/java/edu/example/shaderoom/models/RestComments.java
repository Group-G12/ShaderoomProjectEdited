package edu.example.shaderoom.models;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.util.Date;

public class RestComments extends BaseComments{

    private DocumentReference author;

    public RestComments(String objectId, Number likeCount, String content, Date createdAt, DocumentReference author)
    {
        this.objectId = objectId;
        this.likeCount = likeCount;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
    }

    public RestComments(String objectId, Number likeCount, String content, Date createdAt, String author)
    {
        this.objectId = objectId;
        this.likeCount = likeCount;
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
