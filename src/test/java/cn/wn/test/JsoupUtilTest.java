package cn.wn.test;

import cn.wn.pojo.Goods;
import cn.wn.util.JsoupUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class JsoupUtilTest {

    @Test
    public void test() throws IOException {
        List<Goods> goodsList = JsoupUtil.parseJd("java");

    }
}
