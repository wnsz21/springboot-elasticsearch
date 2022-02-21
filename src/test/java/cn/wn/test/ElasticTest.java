package cn.wn.test;

import cn.wn.mapper.GoodsMapper;
import cn.wn.pojo.Goods;
import cn.wn.repository.GoodsRepository;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class ElasticTest {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void test(){

        List<Goods> goodsList = goodsMapper.findAll();

        Goods goods = new Goods();
        goods.setName("测试");

        goodsList.add(goods);

        goodsRepository.saveAll(goodsList);

    }


    @Test
    public void test2(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name", "小米"));

        Page<Goods> search = goodsRepository.search(nativeSearchQueryBuilder.build());
        System.out.println("search.getContent() = " + search.getContent());

    }

    @Test
    public void test3(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name", "小米"));

        SearchHits<Goods> goodsSearchHits = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(), Goods.class);
        List<Goods> goodsList = goodsSearchHits.stream().map(hit -> hit.getContent()).collect(Collectors.toList());
        System.out.println("goodsList = " + goodsList);

    }

    @Test
    public void test4(){
        List<Goods> 小米 = goodsRepository.findByNameAndPrice("小米12", 299.0);
        System.out.println("小米 = " + 小米);
    }

    @Test
    public void test5(){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name", "小米"));

    }
}
