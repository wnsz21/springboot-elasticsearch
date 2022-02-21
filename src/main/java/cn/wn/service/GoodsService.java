package cn.wn.service;

import cn.wn.pojo.Goods;

import java.io.IOException;
import java.util.List;

public interface GoodsService {

    void parseJd(String keyword) throws IOException;

    List<Goods> getData(String keyword, Integer pageNum, Integer pageSize) throws IOException;

}
