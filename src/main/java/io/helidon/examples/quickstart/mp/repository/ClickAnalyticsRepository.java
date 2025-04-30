package io.helidon.examples.quickstart.mp.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import io.helidon.examples.quickstart.mp.model.ClickAnalytics;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class ClickAnalyticsRepository {

    private final MongoCollection<Document> collection;

    @Inject
    public ClickAnalyticsRepository(
            MongoClient mongoClient,
            @ConfigProperty(name = "mongo.database") String dbName) {
        MongoDatabase database = mongoClient.getDatabase(dbName);
        this.collection = database.getCollection("click_analytics"); // hardcoded collection name
    }

    public void insert(ClickAnalytics analytics) {
        Document doc = new Document()
                .append("linkId", analytics.getLinkId())
                .append("timestamp", analytics.getTimestamp() != null ? analytics.getTimestamp() : new Date())
                .append("userAgent", analytics.getUserAgent())
                .append("ipAddress", analytics.getIpAddress());
        collection.insertOne(doc);
    }

    public List<ClickAnalytics> findAll() {
        List<ClickAnalytics> results = new ArrayList<>();
        for (Document doc : collection.find()) {
            ClickAnalytics analytics = new ClickAnalytics(
                    doc.getString("linkId"),
                    doc.getDate("timestamp"),
                    doc.getString("userAgent"),
                    doc.getString("ipAddress")
            );
            results.add(analytics);
        }
        return results;
    }
}

