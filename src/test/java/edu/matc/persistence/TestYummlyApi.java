package edu.matc.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yummly.recipes.Criteria;
import com.yummly.recipes.MatchesItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestYummlyApi {

    private final Logger logger = LogManager.getLogger(this.getClass());
    //Pass in the client.target into the method and save the url in a properties file
    @Test
    public void testYummlyApiJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.yummly.com/v1/api/recipes" +
                        "?_app_id=f65e0890&_app_key=f73e0521794d8722e37af638cdd2e052&q=curry");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        Criteria criteria = mapper.readValue(response, Criteria.class);
        MatchesItem matchesItem = new MatchesItem();
        logger.info("Found Criteria from Q: " + criteria.getMatches().get(0).getRecipeName());


        assertEquals("Curry", criteria.getMatches().get(0).getRecipeName());
    }
}
