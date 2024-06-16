package com.ruoyi.web.controller.resturant;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.resturant.domain.ResShop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ResShopControllerTest {
    @Autowired
    private ResShopController resShopController;
    //
    @Test
    public void testlist(){
        AjaxResult result = resShopController.list("铺");
        List<ResShop> list = (List<ResShop>)result.get("data");
        Assertions.assertTrue(result.isSuccess());
        Assertions.assertEquals(2,list.size());

    }
    @Test
    public void testgetInfo() {
        AjaxResult result = resShopController.getInfo(2L);
        Assertions.assertTrue(result.isSuccess());

        ResShop resShop = (ResShop)result.get("data");
        Assertions.assertEquals("海底捞火锅", resShop.getName());
    }
}
