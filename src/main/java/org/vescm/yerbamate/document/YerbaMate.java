package org.vescm.yerbamate.document;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.vescm.yerbamate.enums.YerbaMateType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "YerbaMate")
public class YerbaMate {
    @Id
    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "type")
    private YerbaMateType type;

    @DynamoDBAttribute(attributeName = "price")
    private float price;

    @DynamoDBAttribute(attributeName = "stock")
    private long stock;
}
