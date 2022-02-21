package cn.wn.repository;

import cn.wn.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface GoodsRepository extends ElasticsearchRepository<Goods, Integer>{

    List<Goods> findByNameAndPrice(String name, Double price);

}
