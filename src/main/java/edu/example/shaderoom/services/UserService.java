package edu.example.shaderoom.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import edu.example.shaderoom.models.Chats;
import edu.example.shaderoom.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    public List<Chats> getChatsByUserId(String objectId) throws ExecutionException, InterruptedException {

        //printing out chats to the screen
        List<Chats> chats = new ArrayList<>();

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //retrieves a reference to the document(row) of the collection (table) with a specific id
        DocumentReference userRef = db.collection("user").document(objectId);
        System.out.println(userRef);

        //ApiFuture allows us to make async calls to the database
        ApiFuture<DocumentSnapshot> future = userRef.get();
        //Retrieve document
        DocumentSnapshot userDoc = future.get();
        System.out.println(userDoc.get("id"));
        System.out.println(userDoc.get("userName"));
        System.out.println(userDoc.get("firstName"));
        System.out.println(userDoc.get("lastName"));
        System.out.println(userDoc.get("email"));
        System.out.println(userDoc.get("password"));
        //Convert JSON into User class object
        User user = userDoc.toObject(User.class);
        System.out.println(user);

        //query for chats by user
        Query chatsQuery = db.collectionGroup("Chats").whereEqualTo("author", userRef);
        ApiFuture<QuerySnapshot> querySnapshot = chatsQuery.get();

        //loop over results and creat chat objects
        for(DocumentSnapshot document : querySnapshot.get().getDocuments())
        {
            chats.add(new Chats(document.getId(), document.getString("title"), document.getString("content"), document.getDate("createdAt"),user)
            );
        }

        return chats;




    }

    public User getUser(String objectId) throws ExecutionException, InterruptedException {

        //database connection object
        Firestore db = FirestoreClient.getFirestore();

        //retrieves a reference to the document(row) of the collection (table) with a specific id
        DocumentReference userRef = db.collection("user").document(objectId);
        System.out.println(userRef);

        //ApiFuture allows us to make async calls to the database
        ApiFuture<DocumentSnapshot> future = userRef.get();
        //Retrieve document
        DocumentSnapshot userDoc = future.get();
        System.out.println(userDoc.get("id"));
        System.out.println(userDoc.get("userName"));
        System.out.println(userDoc.get("firstName"));
        System.out.println(userDoc.get("lastName"));
        System.out.println(userDoc.get("email"));
        System.out.println(userDoc.get("password"));

        return new User(
                userDoc.get("id").toString(),
                userDoc.get("userName").toString(),
                userDoc.get("firstName").toString(),
                userDoc.get("lastName").toString(),
                userDoc.get("email").toString(),
                userDoc.get("password").toString(),
                null
        );
    }
}
