package com.zhaojy.funny.data.network.api

import com.zhaojy.funny.bean.*
import com.zhaojy.funny.data.bean.Article
import com.zhaojy.funny.data.bean.Collect
import com.zhaojy.funny.data.bean.User
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST




/**
 *@author: zhaojy
 *@data:On 2019/9/21.
 */
interface FunnyService {
    /**
     * 获取植物分类集合数据
     *
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/plant/getPlantClassify")
    fun getPlantClassifyList(): Call<List<ClassifyBean>>

    /**
     * 获取轮播图
     *
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/system/getBannerImg")
    fun getBannerImgList(): Call<List<String>>

    /**
     * 获取轮播图
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("getWanTuList")
    fun getWanTuList(@Body requestBody: RequestBody): Call<List<WanTuBean>>

    /**
     * 获取轮播图
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/plant/getClassifyPlantList")
    fun getClassifyPlantList(@Body requestBody: RequestBody): Call<List<Plant>>

    /**
     * 登录
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/user/login")
    fun login(@Body requestBody: RequestBody): Call<User>

    /**
     * 根据分类id读取文章
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/plant/getArticleList")
    fun getArticleList(@Body requestBody: RequestBody): Call<List<Article>>

    /**
     * 搜索植物
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("searchPlant")
    fun searchPlant(@Body requestBody: RequestBody): Call<List<Plant>>

    /**
     * 搜索文章
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("searchArticle")
    fun searchArticle(@Body requestBody: RequestBody): Call<List<Article>>

    /**
     * 提交浏览记录
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/plant/recordBrowseHistory")
    fun recordHistory(@Body requestBody: RequestBody): Call<String>

    /**
     * 读取浏览记录
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/user/readBrowseHistory")
    fun readHistory(@Body requestBody: RequestBody): Call<List<History>>

    /**
     * 根据id获取文章信息
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("getArticleById")
    fun getArticleById(@Body requestBody: RequestBody): Call<Article>

    /**
     * 根据id获取植物信息
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/plant/getPlantById")
    fun getPlantById(@Body requestBody: RequestBody): Call<Plant>

    /**
     * 收藏
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/plant/collect")
    fun collect(@Body requestBody: RequestBody): Call<Collect>

    /**
     * 取消收藏
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/plant/cancelCollect")
    fun cancelCollect(@Body requestBody: RequestBody): Call<Collect>

    /**
     * 读取用户收藏列表
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/user/readCollection")
    fun readCollect(@Body requestBody: RequestBody): Call<List<Collect>>

    /**
     * 获取用户收藏总数
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/user/getCollectSum")
    fun getCollectSum(@Body requestBody: RequestBody): Call<Count>

    /**
     * 获取收藏信息
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/plant/getCollectInfo")
    fun getCollectInfo(@Body requestBody: RequestBody): Call<Collect>

    /**
     * 获取用户浏览历史总数
     *
     * @param requestBody
     * @return
     */
    @Headers("Content-type:application/json;charset=UTF-8")
    @POST("PlantTuDian/user/getHistorySum")
    fun getHistorySum(@Body requestBody: RequestBody): Call<Count>
}
