package com.example.myapplication.mvp.model;

import com.example.myapplication.doman.ApiDoman;
import com.example.myapplication.mvp.contact.LreContact;
import com.example.myapplication.mvp.model.api.Api;
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
import com.example.myapplication.mvp.model.entity.OrderEntity;
import com.example.myapplication.mvp.model.entity.QueryUserEntity;
import com.example.myapplication.mvp.model.entity.RegisterEntity;
import com.example.myapplication.mvp.model.entity.SeeListEntity;
import com.example.myapplication.mvp.model.entity.ShoesEnitty;
import com.example.myapplication.mvp.model.entity.UploadHeadEntity;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;
import okhttp3.MultipartBody;

@ActivityScope
public class LreModel extends BaseModel implements LreContact.LreModel {
    @Inject
    public LreModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity> lreRequest(String params, int type) {
        Observable<BaseEntity> ob = null;
        switch (type){
            case ApiDoman
                    .CATEGORY_GOODS:
                Observable<CategoryGoodsEntity> categoryGoodsEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryGoodsList(params);
                ob = Observable.fromArray(categoryGoodsEntity).flatMap((Function) Functions.identity(),false,1);
                break;
            case ApiDoman
                    .BRAND_LIST:
                Observable<BrandListEntity> brandList = mRepositoryManager.obtainRetrofitService(Api.class).postBrandList(params);
                ob = Observable.fromArray(brandList).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman.CATEGORY_ALL:
                Observable<Category_allEntity> allList = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryAll(params);
                ob = Observable.fromArray(allList).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman.BANNER:
                Observable<BannerEntity> bannerList = mRepositoryManager.obtainRetrofitService(Api.class).getBannerList();
                Observable<BaseEntity> observable = Observable.fromArray(bannerList).flatMap((Function) Functions.identity());
                ob = observable;
                break;
            case ApiDoman.SHOES_LIST:
                Observable<ShoesEnitty> shoneList = mRepositoryManager.obtainRetrofitService(Api.class).postShoneList(params);
                ob = Observable.fromArray(shoneList).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.SEE_LIST:
                Observable<SeeListEntity> seeList = mRepositoryManager.obtainRetrofitService(Api.class).postSeeList(params);
                ob = Observable.fromArray(seeList).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.GOODS_LIST:
                Observable<GoodsListEntity> goodsList = mRepositoryManager.obtainRetrofitService(Api.class).postGoodsList(params);
                ob = Observable.fromArray(goodsList).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.COMMUNITY_LIST:
                Observable<CommunityEntity> communityEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCommunity(params);
                ob = Observable.fromArray(communityEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.LOGIN_RESULT:
                Observable<LoginEntity> loginEntity = mRepositoryManager.obtainRetrofitService(Api.class).postLogin(params);
                ob = Observable.fromArray(loginEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.REGISTER_RESULT:
                Observable<RegisterEntity>  registerEntity = mRepositoryManager.obtainRetrofitService(Api.class).postRegister(params);
                ob = Observable.fromArray(registerEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.ADD_CAR:
                Observable<AddCarEntity>  addCar = mRepositoryManager.obtainRetrofitService(Api.class).postAddCar(params);
                ob = Observable.fromArray(addCar).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.CAR_LIST:
                Observable<CarListEntity> carList = mRepositoryManager.obtainRetrofitService(Api.class).postCarList(params);
                ob = Observable.fromArray(carList).flatMap((Function) Functions.identity());
                break;

            case ApiDoman.QUERY_USER:
                Observable<QueryUserEntity> queryUserEntity = mRepositoryManager.obtainRetrofitService(Api.class).postQueryUser(params);
                ob = Observable.fromArray(queryUserEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.UPLOAD_HEAD:
//                Observable<UploadHeadEntity> uploadHeadEntity = mRepositoryManager.obtainRetrofitService(Api.class).postUploadHead(params);
//                ob = Observable.fromArray(uploadHeadEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.CHANGE_USER:
                Observable<ChangeUserEntity> changeUserEntity = mRepositoryManager.obtainRetrofitService(Api.class).postChangeUser(params);
                ob = Observable.fromArray(changeUserEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.ORDER_LIST:
                Observable<OrderEntity> orderEntity = mRepositoryManager.obtainRetrofitService(Api.class).postOrder(params);
                ob = Observable.fromArray(orderEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman
                    .ADD_ADDRESS:
                Observable<BaseEntity> addressEntityObservable = mRepositoryManager.obtainRetrofitService(Api.class).postAddData(params);
                ob = Observable.fromArray(addressEntityObservable).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .ADDRESSMANAGER:
                Observable<AddressEntity> observables = mRepositoryManager.obtainRetrofitService(Api.class).postAddressData(params);
                ob = Observable.fromArray(observables).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .DELETE_ADDRESS:
                Observable<BaseEntity> dassafa = mRepositoryManager.obtainRetrofitService(Api.class).postDeleteCarData(params);
                ob = Observable.fromArray(dassafa).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .FOOT_PRINT:
                Observable<FootPrintEntity> footPrintEntity = mRepositoryManager.obtainRetrofitService(Api.class).postFootPrint(params);
                ob = Observable.fromArray(footPrintEntity).flatMap((Function) Functions.identity(), false, 1);
                break;

            case ApiDoman
                    .COUPON_LIST:
                Observable<CouponEntity> couponEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCoupon(params);
                ob = Observable.fromArray(couponEntity).flatMap((Function) Functions.identity(), false, 1);
                break;

        }

        return ob;
    }

    @Override
    public Observable<BaseEntity> lreRefreshRequest(String params, int type) {
        Observable<BaseEntity> ob = null;
        switch (type) {
            case ApiDoman
                    .CATEGORY_GOODS:
                Observable<CategoryGoodsEntity> categoryGoodsEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryGoodsList(params);
                ob = Observable.fromArray(categoryGoodsEntity).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman
                    .BRAND_LIST:
                Observable<BrandListEntity> brandList = mRepositoryManager.obtainRetrofitService(Api.class).postBrandList(params);
                ob = Observable.fromArray(brandList).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman.CATEGORY_ALL:
                Observable<Category_allEntity> allList = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryAll(params);
                ob = Observable.fromArray(allList).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman.BANNER:
                Observable<BannerEntity> banner = mRepositoryManager.obtainRetrofitService(Api.class).getBannerList();
                ob = Observable.fromArray(banner).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman.SHOES_LIST:
                Observable<ShoesEnitty> shoneList = mRepositoryManager.obtainRetrofitService(Api.class).postShoneList(params);
                ob = Observable.fromArray(shoneList).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.SEE_LIST:
                Observable<SeeListEntity> seeList = mRepositoryManager.obtainRetrofitService(Api.class).postSeeList(params);
                ob = Observable.fromArray(seeList).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.GOODS_LIST:
                Observable<GoodsListEntity> goodsList = mRepositoryManager.obtainRetrofitService(Api.class).postGoodsList(params);
                ob = Observable.fromArray(goodsList).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.COMMUNITY_LIST:
                Observable<CommunityEntity> communityEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCommunity(params);
                ob = Observable.fromArray(communityEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.LOGIN_RESULT:
                Observable<LoginEntity> loginEntity = mRepositoryManager.obtainRetrofitService(Api.class).postLogin(params);
                ob = Observable.fromArray(loginEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.REGISTER_RESULT:
                Observable<RegisterEntity>  registerEntity = mRepositoryManager.obtainRetrofitService(Api.class).postRegister(params);
                ob = Observable.fromArray(registerEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.ADD_CAR:
                Observable<AddCarEntity>  addCar = mRepositoryManager.obtainRetrofitService(Api.class).postAddCar(params);
                ob = Observable.fromArray(addCar).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.CAR_LIST:
                Observable<CarListEntity> carList = mRepositoryManager.obtainRetrofitService(Api.class).postCarList(params);
                ob = Observable.fromArray(carList).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.QUERY_USER:
                Observable<QueryUserEntity> queryUserEntity = mRepositoryManager.obtainRetrofitService(Api.class).postQueryUser(params);
                ob = Observable.fromArray(queryUserEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.CHANGE_USER:
                Observable<ChangeUserEntity> changeUserEntity = mRepositoryManager.obtainRetrofitService(Api.class).postChangeUser(params);
                ob = Observable.fromArray(changeUserEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.ORDER_LIST:
                Observable<OrderEntity> orderEntity = mRepositoryManager.obtainRetrofitService(Api.class).postOrder(params);
                ob = Observable.fromArray(orderEntity).flatMap((Function) Functions.identity());
                break;
        }
        return ob;
    }

    @Override
    public Observable<BaseEntity> lreLoadRequest(String params, int type) {
        Observable<BaseEntity> ob = null;
        switch (type){
            case ApiDoman
                    .CATEGORY_GOODS:
                Observable<CategoryGoodsEntity> categoryGoodsEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryGoodsList(params);
                ob = Observable.fromArray(categoryGoodsEntity).flatMap((Function) Functions.identity(),false,1);
                break;
            case ApiDoman
                    .BRAND_LIST:
                Observable<BrandListEntity> brandList = mRepositoryManager.obtainRetrofitService(Api.class).postBrandList(params);
                ob = Observable.fromArray(brandList).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman.CATEGORY_ALL:
                Observable<Category_allEntity> allList = mRepositoryManager.obtainRetrofitService(Api.class).postCategoryAll(params);
                ob = Observable.fromArray(allList).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman.BANNER:
                Observable<BannerEntity> banner = mRepositoryManager.obtainRetrofitService(Api.class).getBannerList();
                ob = Observable.fromArray(banner).flatMap((Function) Functions.identity(), false, 1);
                break;
            case ApiDoman.SHOES_LIST:
                Observable<ShoesEnitty> shoneList = mRepositoryManager.obtainRetrofitService(Api.class).postShoneList(params);
                ob = Observable.fromArray(shoneList).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.SEE_LIST:
                Observable<SeeListEntity> seeList = mRepositoryManager.obtainRetrofitService(Api.class).postSeeList(params);
                ob = Observable.fromArray(seeList).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.GOODS_LIST:
                Observable<GoodsListEntity> goodsList = mRepositoryManager.obtainRetrofitService(Api.class).postGoodsList(params);
                ob = Observable.fromArray(goodsList).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.COMMUNITY_LIST:
                Observable<CommunityEntity> communityEntity = mRepositoryManager.obtainRetrofitService(Api.class).postCommunity(params);
                ob = Observable.fromArray(communityEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.LOGIN_RESULT:
                Observable<LoginEntity> loginEntity = mRepositoryManager.obtainRetrofitService(Api.class).postLogin(params);
                ob = Observable.fromArray(loginEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.REGISTER_RESULT:
                Observable<RegisterEntity>  registerEntity = mRepositoryManager.obtainRetrofitService(Api.class).postRegister(params);
                ob = Observable.fromArray(registerEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.ADD_CAR:
                Observable<AddCarEntity>  addCar = mRepositoryManager.obtainRetrofitService(Api.class).postAddCar(params);
                ob = Observable.fromArray(addCar).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.CAR_LIST:
                Observable<CarListEntity> carList = mRepositoryManager.obtainRetrofitService(Api.class).postCarList(params);
                ob = Observable.fromArray(carList).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.QUERY_USER:
                Observable<QueryUserEntity> queryUserEntity = mRepositoryManager.obtainRetrofitService(Api.class).postQueryUser(params);
                ob = Observable.fromArray(queryUserEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.CHANGE_USER:
                Observable<ChangeUserEntity> changeUserEntity = mRepositoryManager.obtainRetrofitService(Api.class).postChangeUser(params);
                ob = Observable.fromArray(changeUserEntity).flatMap((Function) Functions.identity());
                break;
            case ApiDoman.ORDER_LIST:
                Observable<OrderEntity> orderEntity = mRepositoryManager.obtainRetrofitService(Api.class).postOrder(params);
                ob = Observable.fromArray(orderEntity).flatMap((Function) Functions.identity());
                break;
        }

        return ob;
    }

    @Override
    public Observable<UploadHeadEntity> uploadRequest(List<MultipartBody.Part> params, int type) {

        return mRepositoryManager.obtainRetrofitService(Api.class).postHeadImg(params);
    }
}
