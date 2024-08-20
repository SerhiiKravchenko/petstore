package com.chtrembl.petstore.order.api;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.chtrembl.petstore.order.model.Order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CosmosDbClient implements OrderToDb {

    @Value("${petstore.cosmo.database.url}")
    private String serviceEndpoint;
    @Value("${petstore.cosmo.database.key}")
    private String key;

    @Override
    public String placeOrderToDb(Order order) {
        String databaseName = "petStoreApp";
        String containerName = "Orders";

        CosmosClient client = new CosmosClientBuilder()
                .endpoint(serviceEndpoint)
                .key(key)
                .buildClient();

        client.createDatabaseIfNotExists(databaseName);
        client.getDatabase(databaseName).createContainerIfNotExists(containerName, "/id");

        CosmosDatabase database = client.getDatabase(databaseName);
        CosmosContainer container = database.getContainer(containerName);
        String activityId = container.upsertItem(order).getActivityId();

        client.close();

        return activityId;
    }


}
