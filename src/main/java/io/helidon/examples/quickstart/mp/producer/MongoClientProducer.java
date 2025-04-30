package io.helidon.examples.quickstart.mp.producer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class MongoClientProducer {

    @Produces
    @Singleton
    public MongoClient createMongoClient(@ConfigProperty(name = "mongo.connection-string") String connectionString) {
        return MongoClients.create(connectionString);
    }
}
