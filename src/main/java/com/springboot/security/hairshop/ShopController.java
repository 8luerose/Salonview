package com.springboot.security.hairshop;

import com.springboot.security.hairshop.model.GetShopMainRes;
import com.springboot.security.hairshop.model.GetShopRes;
import com.springboot.security.hairshop.model.GetShopSearchRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private final ShopProvider shopProvider;
    @Autowired
    private final ShopService shopService;

    public ShopController(ShopProvider shopProvider, ShopService shopService){
        this.shopProvider = shopProvider;
        this.shopService = shopService;
    }

    //헤어샵 이름으로 검색 시 이름 포함된 헤어샵 리스트로 출력
    @GetMapping("/searchShopNames")  //ex http://localhost:8080/shops/searchShopNames?shopName=이철
    @ResponseBody
    public List<GetShopRes> getShops(@RequestParam("shopName")String shopName) {

        List<GetShopRes> shopRes = shopProvider.getShop(shopName);
        return shopRes;
    }

    //하나 선택 된 헤어샵 id 정보
    @GetMapping("/searchShopNames/{shopId}")
    public String getOneShop(@PathVariable("shopId") int shopId){
        GetShopRes oneShopRes=shopProvider.getOneShop(shopId);
        return oneShopRes.getShop_name();

    }

    //헤어샵 메인 화면 진입 시 살롱뷰 에디터 픽 출력
    @GetMapping("")
    public List<GetShopMainRes> getMainShops(){
        List<GetShopMainRes> SalonviewEditerPick = shopProvider.getMainShop();
        return SalonviewEditerPick;
    }

    /*
    //(성공)헤어샵 지역 검색 후 리스트 조회 1차시도
    @GetMapping("/searchShopList") // ..http://localhost:8080/shops/searchShopList?searchRegion=광진구
    public List<GetShopSearchRes> getShopSearchLists(@RequestParam("searchRegion")String searchRegion) {
        List<GetShopSearchRes> searchShop = shopProvider.getShopSearchList(searchRegion);
        return searchShop;
    }
     */


  /*
  (성공)헤어샵 리스트 조회 + type(염색, 펌..) 2차시도
  @GetMapping("/searchShopList/{type}")
        // ..http://localhost:8080/shops/searchShopList/0?searchRegion=광진구
          public List<GetShopSearchRes> getShopSearchLists(@PathVariable("type") int type,
                                                           @RequestParam("searchRegion")String searchRegion) {
      List<GetShopSearchRes> searchShop = shopProvider.getShopSearchList(searchRegion, type);
      return searchShop;
  }*/


    @GetMapping("/searchShopList/{type}")       //작성 중
    // ..http://localhost:8080/shops/searchShopList/0?searchRegion=광진구?page=1
    public List<GetShopSearchRes> getShopSearchLists(@PathVariable("type") int type,
                                                     @RequestParam("searchRegion")String searchRegion,
                                                     @RequestParam("page")int page) {
        List<GetShopSearchRes> searchShop = shopProvider.getShopSearchList(searchRegion, type, page);
        return searchShop;
    }





       //헤어샵 지역 검색 후 리스트 조회와 type(염색 펌 커트 기타시술 바버샵) 그리고 page
      /*  @GetMapping("/searchShopList/{type}")
        // ..http://localhost:8080/shops/searchShopList?searchRegion=광진구/1?page=0
          public List<GetShopSearchRes> getShopSearchLists(@RequestParam("searchRegion")String searchRegion,
         @PathVariable("type") int type, @RequestParam("page")int page) {
            List<GetShopSearchRes> searchShop = shopProvider.getShopSearchList(searchRegion,type,page);
              return searchShop;
        }*/


}
