package cn.wn.mapper;

import cn.wn.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsMapper {

    @Select("select * from t_goods")
    List<Goods> findAll();

}
