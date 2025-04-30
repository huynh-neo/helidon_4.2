package io.helidon.examples.quickstart.mp.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import io.helidon.examples.quickstart.mp.model.ClickAnalytics;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ClickAnalyticsRepository {

    // private final MongoCollection<Document> collection;

    // @Inject
    // public ClickAnalyticsRepository(MongoClient mongoClient) {
    //     MongoDatabase database = mongoClient.getDatabase("analyticsdb");
    //     this.collection = database.getCollection("click_analytics");
    // }

    // public void insert(ClickAnalytics analytics) {
    //     Document doc = new Document("linkId", analytics.getLinkId())
    //             .append("clickedAt", analytics.getClickedAt())
    //             .append("userAgent", analytics.getUserAgent());
    //     collection.insertOne(doc);
    // }

    // public List<ClickAnalytics> findAll() {
    //     List<ClickAnalytics> analyticsList = new ArrayList<>();
    //     for (Document doc : collection.find()) {
    //         ClickAnalytics analytics = new ClickAnalytics(
    //                 doc.getLong("linkId"),
    //                 doc.getDate("clickedAt").toInstant(),
    //                 doc.getString("userAgent")
    //         );
    //         analyticsList.add(analytics);
    //     }
    //     return analyticsList;
    // }
}

