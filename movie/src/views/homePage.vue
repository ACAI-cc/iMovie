<template>
    <div style="padding: 0px;">
        <!-- 搜索框 -->
        <div class="top-box clearfix">
           <div class="top">
            <el-menu class="el-menu-demo" mode="horizontal">
                <span style="float: left;font-size: 28px;margin: 10px 0 0px 30px;">
                    多多影视
                </span>

                <!--查询表单-->
                <div class="search-div">
                    <el-form label-width="70px">
                        <el-row>
                            <el-col style="margin: 10px 0 0  0;" :span="20">
                                <el-form-item>
                                    <el-input style="width: 70%" v-model="searchObj.name" placeholder="影视名称"></el-input>
                                    <el-button style="margin: 0 20px;" type="primary" icon="el-icon-search"
                                        @click="searchPageList()">搜索</el-button>

                                </el-form-item>

                            </el-col>
                        </el-row>
                    </el-form>
                </div>


            </el-menu>
           </div>
        </div>

        <!-- 侧边栏 -->
        <div class="left-nav">
            <el-row style="margin-right: 0;">
                <el-col>

                    <el-menu>
                        <el-menu-item >
                            <i class="el-icon-menu"></i>
                           <span slot="title">首页</span>
                            
                        </el-menu-item>

                        <el-menu-item v-for="(item, index) in categoryList" :key="index" @click="categorySearch(item.id)">
                            <span slot="title">{{ item.name }}</span>
                        </el-menu-item>

                    </el-menu>
                </el-col>
            </el-row>
        </div>


        <!-- 轮播图和电影卡片 -->
        <div>
            <!-- 轮播图 -->
            <div class="brand-box" v-show="showBrand">
                <!-- 走马灯轮播图 -->
                <template>
                    <el-carousel trigger="click" height="400px" style="width: 1200px">
                        <el-carousel-item v-for="movie in movieList" :key="movie.id">
                            <el-image style="height: 100%" :src="movie.image"></el-image>
                        </el-carousel-item>
                    </el-carousel>
                </template>

            </div>

            <div class="movie-box">
                <!-- 电影卡片 -->
                <el-row class="car-box">
                    <el-col :span="5" v-for="(movie, index) in movieList" :key="movie.id" :offset="index > 0 ? 1 : 0">
                        <el-card class="card" :body-style="{ padding: '0px' }" style="margin-bottom: 20px">
                            <div class="cardMovie">
                                <router-link target="_blank" :to="{ path: '/moviePlayer', query: { id: movie.id } }">
                                    <img :src="movie.image" class="image" />
                                </router-link>
                            </div>
                            <div style="padding: 4px">
                                <span class="movieName">{{ movie.name }}</span>
                                <div class="bottom clearfix">
                                    <p class="time">上映时间</p>
                                    <time class="time">{{ movie.createTime }}</time>
                                </div>

                                <el-popover placement="bottom-start" :title="movie.name" width="200" trigger="hover"
                                    :content="'💃主演 \n' + movie.actor">
                                    <el-button size="small" slot="reference" class="el-icon-discount"> 查看详情</el-button>
                                </el-popover>
                            </div>
                        </el-card>

                    </el-col>
                </el-row>
            </div>

            <!-- 分页组件 -->
            <el-pagination class="pagination" :current-page="page" :total="total" :page-size="limit"
                style="padding: 30px 0; text-align: center" layout="total, prev, pager, next, jumper"
                @current-change="fetchPageList" />

        </div>
    </div>
</template>

<script>
import api from "@/api/movie.js"
import router from '@/router';

export default {
    name: 'homePage',
    data() {
        return {
            movieList: [],
            page: 1,
            limit: 8,
            tota: 0,
            searchObj: {},
            showBrand:true,

            // 影视分类列表
            categoryList: [],
        }
    },

    created() {
        this.fetchPageList();
        this.showNav()
    },
    mounted() { },
    computed: {},
    methods: {
       
        showNav() {
            api.findAll().then(result => {
                            // console.log(result);
                this.categoryList = result.data.data;
            })
        },
        // secrchInfo(searchobj) {
        //     console.log(searchObj);
        //     this.searchObj = searchobj;
        //     this.fetchPageList();
        // },
        fetchPageList(page = 1) {
            this.page = page;
            api
                .getMoviePageInfo(this.page, this.limit, this.searchObj)
                .then((response) => {
                    // this.listLoading=false;
                    // console.log(response);
                    this.movieList = response.data.data.records;
                    this.total = response.data.data.total;
                    // console.log(this.movieList);
                });
          
        },
        searchPageList(){
            this.showBrand = false;
            this.fetchPageList();
        },
        categorySearch(cid){
            this.searchObj.cid = cid,
            this.showBrand = false;
            this.fetchPageList();
        }
    },
}

</script>
<style scoped>

.top-box{
    position: fixed;
    top: 0px;
    width: 100%;
    z-index: 9999;
    margin-top: 0px;
    padding: 0;
    /* margin-top: 0; */
}

.movie-box{
    /* float: right; */
    z-index: 999;
    margin-top: 75px;
    padding:20px 20px 0px 200px;
    /* border:1px green solid; */


}
.brand-box{
    margin-top: 75px;
}
/* .car-box{

} */

.left-nav {
    position: fixed;
    width: 150px;
    float: left;
    top: 74px;

  z-index: 9999;
  /* border: 1px pink solid; */
}
.brand-box {
    float: right;
    /* border: 1px red solid; */
    /* margin: 20px; */
}

.el-popover {
    white-space: pre-wrap;
}

.el-card:hover {
    margin-top: -10px;
    padding-bottom: 10px;
}

.el-divider__text.is-left {
    left: 150px;
    height: 35px;
}

.el-divider__text {
    background: rgb(190, 246, 170);
}

.movieDivider {
    width: 85%;
    left: 5%;
    text-align: center;
}

.movieDivider span {
    font-size: 20px;
    line-height: 35px;
}

.movieName {
    display: block;
    /* //超出一行省略号 */
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}

.time {
    font-size: 13px;
    color: #999;
}

.bottom {
    margin-top: 13px;
    line-height: 12px;
}

.button {
    padding: 0;
    float: right;
}

.cardMovie {
    display: flex;
    justify-content: center;
    align-items: center;
}

.image {
  height: 200px;
  display: block;
}
.clearfix:before,
.clearfix:after {
    display: table;
    content: "";
}

.clearfix:after {
    clear: both;
}
</style>
