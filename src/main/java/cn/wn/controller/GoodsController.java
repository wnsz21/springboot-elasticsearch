package cn.wn.controller;

import cn.wn.pojo.Goods;
import cn.wn.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/parse/{keyword}")
    public Boolean parseJd(@PathVariable("keyword") String keyword) throws IOException {
        goodsService.parseJd(keyword);
        return true;
    }

    @GetMapping("/data/{keyword}/{pageNum}/{pageSize}")
    public List<Goods> data(
            @PathVariable("keyword") String keyword,
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize
    ) throws IOException {

        List<Goods> data = goodsService.getData(keyword, pageNum-1, pageSize);

        return data;
    }
    
}
