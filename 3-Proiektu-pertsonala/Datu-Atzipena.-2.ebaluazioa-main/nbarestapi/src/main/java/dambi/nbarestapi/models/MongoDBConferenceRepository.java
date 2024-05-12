package dambi.nbarestapi.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import jakarta.annotation.PostConstruct;

import static com.mongodb.client.model.Filters.eq;
@Repository
public class MongoDBConferenceRepository implements ConferenceRepository{
     @Autowired
    private MongoClient client;
    private MongoCollection<Conference> conferenceCollection;

    @PostConstruct
    void init() {
        conferenceCollection = client.getDatabase("NBA").getCollection("Conferences", Conference.class);
    }
    
    public List<Conference> findAllConferences() {
        return conferenceCollection.find().into(new ArrayList<>());
    }

    public Conference findConferenceByName(String conferenceName) {
        return conferenceCollection.find(eq("conferenceName", conferenceName)).first();
    }

    public Conference saveConference(Conference conference) {
        conferenceCollection.insertOne(conference);
        return conference;
    }

    public long deleteConference(String conferenceName) {
        return conferenceCollection.deleteMany(eq("conferenceName", conferenceName)).getDeletedCount();
    }
}
