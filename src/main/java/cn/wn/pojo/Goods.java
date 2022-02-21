package cn.wn.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.Date;

@Data
@Document(indexName = "goods")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Keyword)
    private String price;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSX")
    private Date shelfDate;
    @Field(type = FieldType.Keyword)
    private String img;
    @Field(type = FieldType.Text, analyzer = "ik_smart")
    private String describe;

}
