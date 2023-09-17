<template>
  <div v-loading="loading" element-loading-text="文件上传中......">
    <!--查询表单-->
    <div class="search-div">
      <el-form label-width="70px" size="small">
        <el-row>
          <el-col :span="24">
            <el-form-item label="影视名称">
              <el-input style="width: 100%" v-model="searchObj.name" placeholder="影视名称"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="display: flex">
          <el-button type="primary" icon="el-icon-search" size="mini" @click="fetchPageList()">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetData">重置</el-button>
        </el-row>
      </el-form>
    </div>

    <!-- 工具条 -->
    <div class="tools-div">
      <el-button type="success" icon="el-icon-plus" size="mini" @click="add">添 加</el-button>
    </div>

    <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="40%">
      <el-form ref="dataForm" :model="sysMovie" label-width="150px" size="small" style="padding-right: 40px">
        <el-form-item label="影视名称">
          <el-input v-model="sysMovie.name" />
        </el-form-item>

        <el-form-item label="所属栏目" prop="cid">
          <el-select v-model="sysMovie.cid" placeholder="请输入影视分类">
            <el-option v-for="item in categoryList" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="关键字" prop="keyword">
          <el-input v-model="sysMovie.keyword"></el-input>
        </el-form-item>

        <el-form-item label="导演" prop="director">
          <el-input v-model="sysMovie.director"></el-input>
        </el-form-item>

        <el-form-item label="演员" prop="actor">
          <el-input v-model="sysMovie.actor"></el-input>
        </el-form-item>

        <el-form-item label="影视图片" prop="image">
          <el-upload class="upload-demo" name="uploadImage" action="http://localhost:8085/admin/system/upload/uploadImage"
            :limit="1" :on-success="handleImageSuccess" :before-upload="handleBeforeUpload" :show-file-list="false">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>

        <el-form-item label="影视文件" prop="playId" >
          <el-upload class="upload-demo" name="uploadVideo" action="http://localhost:8085/admin/system/upload/uploadVideo"
            :limit="1" :on-success="handleVideoSuccess" :before-upload="handleBeforeUpload" :show-file-list="false">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>

        <el-form-item label="影视描述" prop="description">
          <el-input v-model="sysMovie.description"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small" icon="el-icon-refresh-right">取 消</el-button>
        <el-button type="primary" icon="el-icon-check" @click="saveOrUpdate()" size="small">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 表格 -->
    <el-table v-loading="listLoading" :data="list" stripe border style="width: 100%; margin-top: 10px">
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="name" label="影视名称" />
      <el-table-column prop="cid" label="影视类型" :formatter="cidformatter" />
      <el-table-column prop="director" label="导演" width="160" />

      <!-- <el-table-column prop="image" label="剧照">
        <template slot-scope="scope">
          {{scope.row.image}}
          <viewer>
            <img :src="scope.row.image" alt="" style="height: 80px" />
          </viewer>
        </template>
      </el-table-column> -->

      <el-table-column prop="image" label="剧照">
        <template slot-scope="scope">
          <viewer>
            <img :src="scope.row.image" alt="" style="height: 80px" />
          </viewer>
        </template>

      </el-table-column>


      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row.id)" title="修改" />
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeDataById(scope.row.id)" title="删除" />
          <el-button type="warning" icon="el-icon-baseball" size="mini" @click="showPlayPage(scope.row)" title="播放视频" />
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination :current-page="page" :total="total" :page-size="limit" style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper" @current-change="fetchPageList" />


  </div>
</template>

<script>
// 导入
import api from "@/api/movie/movie.js";

// 导入分类
import categoryapi from "@/api/category/category.js";

export default {
  data() {
    return {
      list: [],
      page: 1,
      limit: 5,
      tota: 0,
      searchObj: {},
      listLoading: false,
      dialogVisible: false,
      sysMovie: {},
      // 影视分类列表
      categoryList: [],
      fileurl: "",
      loading: false,
      VideodialogVisible: false
    };
  },
  mounted() {

  },
  created() {
    //this.listLoading=true;
    this.fetchPageList();
    this.findAll();
  },
  methods: {
    cidformatter(row, cloumn) {

      for (let i = 0; i < this.categoryList.length; i++) {
        if (row.cid == this.categoryList[i].id) {
          return this.categoryList[i].name
        }
      }
    },

    
    showPlayPage(row) {
      let id = row.id;
      // this.$router.push({name:'assignMovie',query:{id}});
      this.$router.push('/system/assignVideo?id=' + id)

    },
    findAll() {
      categoryapi.findAll().then((response) => {
        console.log(response);
        this.categoryList = response.data;
        console.log(this.categoryList[0].id);
      });
    },
    edit(id) {
      //1.弹框
      this.dialogVisible = true;
      //2.赋值
      api.getMovieById(id).then((response) => {
        this.sysMovie = response.data;
      });
    },
    //文件开始上传,开始屏幕遮罩
    handleBeforeUpload() {
      this.loading = true;
    },
    //图片上传成功的钩子函数
    handleImageSuccess(res, file) {

      console.log(res);  // 得到的是图片地址
      console.log(file);  // 得到是一个对象  

      this.loading = false;
      if (file.response != "") {
        // this.sysMovie.image = file.response.data;
        this.sysMovie.image = file.response;
        console.log(this.sysMovie.image);

        this.$message({
          type: "info",
          message: "图片上传成功",
          duration: 6000,
        });
      } else {
        this.$message({
          type: "info",
          message: "图片上传失败",
          duration: 6000,
        });
      }
    },
    handleVideoSuccess(res, file) {
      this.loading = false;
      if (file.response != "") {

        console.log(res);  // 得到的是 
        console.log(file);  // 得到是

        this.sysMovie.playId = file.response;
        this.$message({
          type: "info",
          message: "视频上传成功",
          duration: 6000,
        });
      } else {
        this.$message({
          type: "info",
          message: "视频上传失败",
          duration: 6000,
        });
      }
    },

    saveOrUpdate() {
      if (this.sysMovie.id != null) {
        this.updateMovie();
      } else {
        this.addMovie();
      }
    },
    addMovie() {
      api.addMovie(this.sysMovie).then((response) => {
        this.$message({
          type: "success",
          message: "添加成功!",
        });
        //关闭弹框
        this.dialogVisible = false;
        // 刷新列表
        this.fetchPageList();
      });
    },
    updateMovie() {
      api.updateMovie(this.sysMovie).then((response) => {
        this.$message({
          type: "success",
          message: "修改成功!",
        });
        //关闭弹框
        this.dialogVisible = false;
        // 刷新列表
        this.fetchPageList();
      });
    },
    add() {
      this.sysMovie = {};
      this.dialogVisible = true;

    },
    removeDataById(id) {
      this.$confirm("此操作将永久删除该影视, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        api.removeMovieById(id).then((response) => {
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          this.fetchPageList();
        });
      });
    },
    resetData() {
      //1.清空搜索条件
      this.searchObj = {};
      //2.再次刷新列表
      this.fetchPageList();
    },
    fetchPageList(page = 1) {
      this.page = page;
      api
        .getMoviePageInfo(this.page, this.limit, this.searchObj)
        .then((response) => {
          // this.listLoading=false;
          console.log(response);
          this.list = response.data.records;
          this.total = response.data.total;
        });
    },
  },
};
</script>

<style lang="less" scoped></style>