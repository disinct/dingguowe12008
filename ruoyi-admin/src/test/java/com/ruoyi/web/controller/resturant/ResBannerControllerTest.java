package com.ruoyi.web.controller.resturant;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.resturant.domain.ResBanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
public class ResBannerControllerTest {
    @Autowired
    private ResBannerController resBannerController;
    //
    @Test
    public void testlist(){
        AjaxResult result = resBannerController.list("铺");
        List<ResBanner> list = (List<ResBanner>)result.get("data");
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(1,list.size());

    }
    @Test
    public void testgetInfo() {
        AjaxResult result = resBannerController.getInfo(1L);
        Assertions.assertTrue(result.isSuccess());

        ResBanner resBanner = (ResBanner)result.get("data");
        Assertions.assertEquals("首页轮播图-店铺1", resBanner.getName());
    }
}
