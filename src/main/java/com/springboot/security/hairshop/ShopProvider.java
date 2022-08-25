package com.springboot.security.hairshop;

import com.springboot.security.hairshop.model.GetShopMainRes;
import com.springboot.security.hairshop.model.GetShopRes;
import com.springboot.security.hairshop.model.GetShopSearchRes;
import com.springboot.security.user.UserDao;
import com.springboot.security.user.model.GetUserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopProvider {
    @Autowired
    private final ShopDao shopDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ShopProvider(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    //
    public List<GetShopRes> getShop(String shopName){
        List<GetShopRes> shopRes = shopDao.shopListRes(shopName);

        return shopRes;
    }

    public GetShopRes getOneShop(int shopId){
        GetShopRes shopOneRes = shopDao.shopRes(shopId);
        return shopOneRes;
    }

    public List<GetShopMainRes> getMainShop(){
        List<GetShopMainRes> SalonviewEditerPick=shopDao.SalonviewEditerPickList();
        return SalonviewEditerPick;
    }

    /*
    (성공)헤어샵 지역 검색 후 리스트 조회 1차시도
    public  List<GetShopSearchRes> getShopSearchList(String searchRegion){
        List<GetShopSearchRes> searchShop=shopDao.searchShopList(searchRegion);
        return searchShop;
    }
     */

    /*
    (성공)헤어샵 리스트 조회 + type(염색, 펌..) 2차시도
    public  List<GetShopSearchRes> getShopSearchList(String searchRegion,int type){
             List<GetShopSearchRes> searchShop=shopDao.searchShopList(searchRegion,type);
             return searchShop;
     }*/


    //작성중
    public  List<GetShopSearchRes> getShopSearchList(String searchRegion,int type,int page){
        List<GetShopSearchRes> searchShop=shopDao.searchShopList(searchRegion,type,page);
        return searchShop;
    }



    /* public  List<GetShopSearchRes> getShopSearchList(String searchRegion,int type,int page){
             List<GetShopSearchRes> searchShop=shopDao.searchShopList(searchRegion,type,page);
             return searchShop;
     }*/

}


