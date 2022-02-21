package cn.wn.service.impl;

import cn.wn.pojo.Goods;
import cn.wn.repository.GoodsRepository;
import cn.wn.service.GoodsService;
import cn.wn.util.JsoupUtil;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.NoSuchIndexException;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void parseJd(String keyword) throws IOException {
        List<Goods> goodsList = JsoupUtil.parseJd(keyword);
        goodsRepository.saveAll(goodsList);
    }

    @Override
    public List<Goods> getData(String keyword, Integer pageNum, Integer pageSize) throws IOException {

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", keyword))
                .withPageable(PageRequest.of(pageNum, pageSize))
                .withHighlightBuilder(new HighlightBuilder().preTags("<font color='red'>").field("name").postTags("</font>"));

        SearchHits<Goods> searchHits = null;
        try {
            searchHits = elasticsearchRestTemplate.search(builder.build(), Goods.class);
        } catch (NoSuchIndexException e) {
            elasticsearchRestTemplate.indexOps(Goods.class);
        }
        if (Objects.isNull(searchHits) || searchHits.getTotalHits() == 0) {
            parseJd(keyword);
            searchHits = elasticsearchRestTemplate.search(builder.build(), Goods.class);
        }
        List<Goods> collect = searchHits.stream().map(hit -> {
            Goods goods = hit.getContent();
            List<String> highlightFields = hit.getHighlightField("name");
            if (!CollectionUtils.isEmpty(highlightFields)) goods.setName(highlightFields.get(0));
            return goods;
        }).collect(Collectors.toList());

        return collect;
    }
}
