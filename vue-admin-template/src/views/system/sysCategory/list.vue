<template>
  <div>
    <!--查询表单-->
    <div class="search-div">
      <el-form label-width="70px" size="small">
        <el-row>
          <el-col :span="24">
            <el-form-item label="分类名称">
              <el-input style="width: 100%" v-model="searchObj.name" placeholder="分类名称"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="display: flex">
          <el-button type="primary" icon="el-icon-search" size="mini" @click="fetchData(page)">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetData">重置</el-button>
        </el-row>
      </el-form>
    </div>

    <!-- 工具条 -->
    <div class="tools-div">
      <el-button type="success" icon="el-icon-plus" size="mini" @click="add">添 加</el-button>
      <el-button class="btn-add" size="mini" @click="batchRemove()">批量删除</el-button>
    </div>
    <!-- 添加 or 修改 弹窗 -->
    <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="40%">
      <el-form ref="dataForm" :model="syscategory" label-width="150px" size="small" style="padding-right: 40px">
        <el-form-item label="分类名称">
          <el-input v-model="syscategory.name" />
        </el-form-item>

      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small" icon="el-icon-refresh-right">取 消</el-button>
        <el-button type="primary" icon="el-icon-check" @click="saveOrUpdate()" size="small">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 表格 -->
    <el-table v-loading="listLoading" :data="list" stripe border style="width: 100%; margin-top: 10px"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" />
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="name" label="分类名称" />
      <el-table-column prop="createTime" label="创建时间" />
      <el-table-column prop="updateTime" label="修改时间"/>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row.id)" title="修改" />
          <!-- scope.row.id ：scope当前槽内的信息对象。 row.id获取当前行的id -->
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeDataById(scope.row.id)" title="删除" />
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination :current-page="page" :total="total" :page-size="limit" style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper" @current-change="fetchData" />
  </div>
</template>

<script>
import api from "@/api/category/category.js";
export default {
  name: "",
  data() {
    return {
      listLoading: true, //  加载中的提示语.....
      list: [],
      total: 0,
      page: 1,
      limit: 3,
      searchObj: {},
      //封装表单中role角色
      syscategory: {},
      dialogVisible: false, // 给个默认初始化值 隐藏弹框
    };
  },
  created() {
    this.fetchData(this.page);
  },

  methods: {
    // 分页查询
    fetchData(page) {
      this.page = page;
      // console.log("role");
      //  console.log(this.searchObj);
      api
        .getPageList(this.page, this.limit, this.searchObj)
        .then((response) => {
          // console.log(response);
          this.listLoading = false;
          this.list = response.data.records;
          this.total = response.data.total;
        });
    },
    // 打开弹框
    add() {
      // 将 控制弹框的 属性设置为 true
      this.dialogVisible = true;
      // 清空表单
      this.syscategory = {};
    },

    // 根据id去回显数据
    edit(id) {
      //1.弹框
      this.dialogVisible = true;
      //2.赋值
      api.getCategoryById(id).then((response) => {
        this.syscategory = response.data;
      });
    },
    // 添加或者修改 具体功能
    saveOrUpdate() {
      if (this.syscategory.id != null) {
        this.updateCategory();
      } else {
        this.addCategory();
      }
    },
    // 实现添加功能
    addCategory() {
      api.saveCategory(this.syscategory).then((response) => {
        //给出提示框
        this.$message({
          type: "success",
          message: "添加角色成功!",
        });
        // 关闭添加弹框
        this.dialogVisible = false;
        // 再次刷新数据
        this.fetchData(this.page);
      });
    },
    updateCategory() {
      api.updateCategory(this.syscategory).then((response) => {
        this.$message({
          type: "success",
          message: "修改成功!",
        });
        // 关闭弹框
        this.dialogVisible = false;
        // 刷新数据
        this.fetchData(this.page);
      });
    },
    // 删除操作
    removeDataById(id) {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        api.removeId(id).then((response) => {
          // 提示
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          // 刷新页面
          this.fetchData(this.page);
        });
      });
    },

    // 批量删除
    batchRemove() {
      // 判断是否有选中select
      if (this.selectValueData.length == 0) {
        this.$message.warning("请选择要删除的记录！");
        return;
      }
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        var ids = [];
        for (var i = 0; i < this.selectValueData.length; i++) {
          var obj = this.selectValueData[i];
          // 获取id值
          var id = obj.id;
          // 将id放进到数组中
          ids.push(id);
        }
        api.bactchremoveId(ids).then((response) => {
          // 提示
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          // 刷新页面
          this.fetchData(this.page);
        });
      });
    },


    // 重置
    resetData() {
      // 清空表单
      this.searchObj = {};
      // 再次刷新列表数据
      this.fetchData(this.page);
    },
    // 复选框
    handleSelectionChange(selectValue) {
      // console.log(selectValue);
      this.selectValueData = selectValue;
    },
  },
};
</script>
<style scoped lang='less'></style>
