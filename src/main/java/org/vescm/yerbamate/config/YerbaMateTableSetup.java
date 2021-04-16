package org.vescm.yerbamate.config;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.vescm.yerbamate.enums.YerbaMateType;

import java.util.Collections;

@Configuration
public class YerbaMateTableSetup {
    private final DynamoDB dynamoDB;
    private final String tableName;

    public YerbaMateTableSetup(final DynamoDbConfig dbConfig) {
        this.dynamoDB = new DynamoDB(dbConfig.amazonDynamoDB(dbConfig.awsCredentialsProvider()));
        this.tableName = "YerbaMate";
        setup();
    }

    public void setup() {
        try {
            if (!tableExists()) {
                Table table = dynamoDB.createTable(tableName,
                        Collections.singletonList(
                                new KeySchemaElement("id", KeyType.HASH)),
                        Collections.singletonList(
                                new AttributeDefinition("id", ScalarAttributeType.S)),
                        new ProvisionedThroughput(5L, 5L));
                table.waitForActive();
                System.out.println("Success creating table: " + table.getDescription().getTableStatus());
                dummyLoad();
            }
        } catch (Exception e) {
            System.err.println("Cannot create table " + tableName);
            e.printStackTrace();
        }
    }

    private boolean tableExists() {
        try {
            dynamoDB.getTable(tableName).describe();
        } catch (ResourceNotFoundException e) {
            return false;
        }
        return true;
    }

    private void dummyLoad() {
        Table table = dynamoDB.getTable(tableName);

        Item baldo = new Item()
                .withPrimaryKey("id", "1")
                .withString("name", "Baldo")
                .withString("type", YerbaMateType.URUGUAYAN.name())
                .withNumber("price", 18.5F)
                .withInt("stock", 33);

        Item taragui = new Item()
                .withPrimaryKey("id", "2")
                .withString("name", "Taragui")
                .withString("type", YerbaMateType.ARGENTINIAN.name())
                .withNumber("price", 25.0F)
                .withInt("stock", 20);

        Item barao = new Item()
                .withPrimaryKey("id", "3")
                .withString("name", "Barão")
                .withString("type", YerbaMateType.CHIMARRAO.name())
                .withNumber("price", 22.0F)
                .withInt("stock", 45);

        Item kurupi = new Item()
                .withPrimaryKey("id", "4")
                .withString("name", "Kurupí")
                .withString("type", YerbaMateType.TERERE.name())
                .withNumber("price", 21.9F)
                .withInt("stock", 70);

        Item caaYari = new Item()
                .withPrimaryKey("id", "5")
                .withString("name", "Caá Yari")
                .withString("type", YerbaMateType.BLEND.name())
                .withNumber("price", 25.0F)
                .withInt("stock", 15);

        PutItemOutcome outcome1 = table.putItem(baldo);
        PutItemOutcome outcome2 = table.putItem(taragui);
        PutItemOutcome outcome3 = table.putItem(barao);
        PutItemOutcome outcome4 = table.putItem(kurupi);
        PutItemOutcome outcome5 = table.putItem(caaYari);
    }
}
