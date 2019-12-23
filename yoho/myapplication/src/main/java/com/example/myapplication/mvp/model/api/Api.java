package com.example.myapplication.mvp.model.api;

import com.example.myapplication.mvp.model.entity.AddCarEntity;
import com.example.myapplication.mvp.model.entity.AddressEntity;
import com.example.myapplication.mvp.model.entity.BannerEntity;
import com.example.myapplication.mvp.model.entity.BaseEntity;
import com.example.myapplication.mvp.model.entity.BrandListEntity;
import com.example.myapplication.mvp.model.entity.CarListEntity;
import com.example.myapplication.mvp.model.entity.CategoryGoodsEntity;
import com.example.myapplication.mvp.model.entity.Category_allEntity;
import com.example.myapplication.mvp.model.entity.ChangeUserEntity;
import com.example.myapplication.mvp.model.entity.CommunityEntity;
import com.example.myapplication.mvp.model.entity.CouponEntity;
import com.example.myapplication.mvp.model.entity.FootPrintEntity;
import com.example.myapplication.mvp.model.entity.GoodsListEntity;
import com.example.myapplication.mvp.model.entity.LoginEntity;
import com.example.myapplication.mvp.model.entity.MenuEntity;
import com.example.myapplication.mvp.model.entity.OrderEntity;
import com.example.myapplication.mvp.model.entity.QueryUserEntity;
import com.example.myapplication.mvp.model.entity.RecommendEntity;
import com.example.myapplication.mvp.model.entity.RegisterEntity;
import com.example.myapplication.mvp.model.entity.SeeListEntity;
import com.example.myapplication.mvp.model.entity.ShoesEnitty;
import com.example.myapplication.mvp.model.entity.UploadHeadEntity;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * ================================================
 * 存放一些与 API 有关的东西,如请求地址,请求码等
 * <p>
 * Created by MVPArmsTemplate on 11/29/2019 14:09
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface Api {
    String APP_DOMAIN = "http://169.254.105.174/yoho/";

    //首页菜单
    @GET("home_menu.php")
    Observable<MenuEntity> getMenuList();

    //banner
    @GET("home_banner.php")
    Observable<BannerEntity> getBannerList();

    //推荐
    @POST("home_recommend.php")
    @FormUrlEncoded
    Observable<RecommendEntity> postRecommendList(@Field("request")String request);

    //商品列表
    @POST("home_goods.php")
    @FormUrlEncoded
    Observable<GoodsListEntity> postGoodsList(@Field("request")String request);

    //品类商品接口
    @POST("category_goods.php")
    @FormUrlEncoded
    Observable<CategoryGoodsEntity> postCategoryGoodsList(@Field("request")String request);

    //品牌列表接口
    @POST("Brand_list.php")
    @FormUrlEncoded
    Observable<BrandListEntity> postBrandList(@Field("request")String request);

    //品牌列表接口
    @POST("category_all.php")
    @FormUrlEncoded
    Observable<Category_allEntity> postCategoryAll(@Field("request")String request);

    //球鞋交易接口
    @POST("shoes_list.php")
    @FormUrlEncoded
    Observable<ShoesEnitty> postShoneList(@Field("request")String request);

    //逛列表接口
    @POST("see_list.php")
    @FormUrlEncoded
    Observable<SeeListEntity> postSeeList(@Field("request")String request);

    //逛列表接口
    @POST("community.php")
    @FormUrlEncoded
    Observable<CommunityEntity> postCommunity(@Field("request")String request);

    //用户登录接口
    @POST("Login.php")
    @FormUrlEncoded
    Observable<LoginEntity> postLogin(@Field("request")String request);

    //用户注册接口
    @POST("Register.php")
    @FormUrlEncoded
    Observable<RegisterEntity> postRegister(@Field("request")String request);

    //添加购物车接口
    @POST("add_car.php")
    @FormUrlEncoded
    Observable<AddCarEntity> postAddCar(@Field("request")String request);

    //购物车列表接口
    @POST("car_list.php")
    @FormUrlEncoded
    Observable<CarListEntity> postCarList(@Field("request")String request);

    //查询用户信息接口
    @POST("sel_user.php")
    @FormUrlEncoded
    Observable<QueryUserEntity> postQueryUser(@Field("request")String request);

    //上传用户头像接口
//    @POST("upload_head.php")
//    @FormUrlEncoded
//    Observable<UploadHeadEntity> postUploadHead(@Field("request")String request);

    @Multipart
    @POST("upload_head.php")
    Observable<UploadHeadEntity> postHeadImg(@Part List<MultipartBody.Part> list);

    //更改用户信息接口
    @POST("update_user.php")
    @FormUrlEncoded
    Observable<ChangeUserEntity> postChangeUser(@Field("request" )String request);

    //订单
    @POST("create_order.php")
    @FormUrlEncoded
    Observable<OrderEntity> postOrder(@Field("request")String request);

    //收货地址列表
    @POST("address_list.php")
    @FormUrlEncoded
    Observable<AddressEntity> postAddressData(@Field("request") String request);

    //新增收货地址
    @POST("add_address.php")
    @FormUrlEncoded
    Observable<BaseEntity> postAddData(@Field("request") String request);

    //删除地址
    @POST("del_address.php")
    @FormUrlEncoded
    Observable<BaseEntity> postDeleteCarData(@Field("request") String request);


    //足迹
    @POST("footprint.php")
    @FormUrlEncoded
    Observable<FootPrintEntity> postFootPrint(@Field("request") String request);

    //优惠券列表
    @POST("coupon_list.php")
    @FormUrlEncoded
    Observable<CouponEntity> postCoupon(@Field("request") String goods);


}
