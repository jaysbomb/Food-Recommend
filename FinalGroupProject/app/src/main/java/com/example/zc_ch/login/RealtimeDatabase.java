package com.example.zc_ch.login;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by zc_ch on 3/30/2017.
 */



public class RealtimeDatabase {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    user test;
    public void writeNewUser(user theuser) {
//        user User = new user(email,height,weight);
        DatabaseReference mDatabase = database.getReference("user");
        mDatabase.child("users").child(theuser.email).setValue(theuser);
    }

    public void writeNewData(Data thedata){
        DatabaseReference mDatabase = database.getReference("data");
        mDatabase.child("datas").child(thedata.name).setValue(thedata);
    }

    public void writeNewTag(Tag thetag){
        DatabaseReference mDatabase = database.getReference("tag");
        mDatabase.child("tags").child(thetag.email).setValue(thetag);
    }

    public void writeHistory(History thehistory){
        DatabaseReference mDatabase = database.getReference("history");
        mDatabase.child(thehistory.email).child(String.valueOf(thehistory.date)).setValue(thehistory);
    }

    public void queryTheUser(final String email) {
        DatabaseReference mDatabase = database.getReference("user");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {



            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                user post = dataSnapshot.getValue(users.class);
                user post = dataSnapshot.child("users").child(email).getValue(user.class);
//                System.out.println("The ccczzzz: " +  post.username);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void QueryTheTag(final String username) {
        DatabaseReference mDatabase = database.getReference("user");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user post = dataSnapshot.child("users").child(username).getValue(user.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }



}

