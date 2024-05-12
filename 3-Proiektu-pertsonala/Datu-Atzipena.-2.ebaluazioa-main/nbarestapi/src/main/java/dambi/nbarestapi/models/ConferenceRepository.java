package dambi.nbarestapi.models;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository {
    
    List<Conference> findAllConferences();
    Conference findConferenceByName(String conferenceName);
    Conference saveConference(Conference conference);
    long deleteConference(String conferenceName);

}
