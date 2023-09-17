<template>
    <div class="app-container">
 
      <div class="search-div">
       <el-form label-width="70px" size="small">
         <el-row>
           <el-col :span="8">
             <el-form-item label="关 键 字">
               <el-input style="width: 95%" v-model="searchObj.operName" placeholder="操作用户"></el-input>
             </el-form-item>
           </el-col>
           <el-col :span="8">
             <el-form-item label="操作时间">
               <el-date-picker
                 v-model="createTimes"
                 type="datetimerange"
                 range-separator="至"
                 start-placeholder="开始时间"
                 end-placeholder="结束时间"
                 value-format="yyyy-MM-dd HH:mm:ss"
                 style="margin-right: 10px;width: 100%;"
               />
             </el-form-item>
           </el-col>
         </el-row>
         <el-row style="display:flex">
           <el-button type="primary" icon="el-icon-search" size="mini"  @click="fetchData()">搜索</el-button>
           <el-button icon="el-icon-refresh" size="mini" @click="resetData">重置</el-button>
         </el-row>
       </el-form>
     </div>
     
     <!-- 列表 -->
     <el-table
       v-loading="listLoading"
       :data="list"
       stripe
       border
       style="width: 100%;margin-top: 10px;">
 
       <el-table-column
         label="序号"
         width="70"
         align="center">
         <template slot-scope="scope">
           {{ (page - 1) * limit + scope.$index + 1 }}
         </template>
       </el-table-column>
 
       <el-table-column prop="operName" label="操作人员" width="180"/>
       <el-table-column prop="businessType" label="业务类型" width="110"/>
       <el-table-column prop="status" label="操作状态">
          <template slot-scope="scope">
              <span v-if="scope.row.status==1">正常</span>
               <span v-if="scope.row.status==0">异常</span>
          </template>
       </el-table-column>
        <el-table-column prop="title" label="模块标题" width="180"/>
       <el-table-column prop="operTime" label="操作时间" />
 
       <el-table-column label="操作"  align="center" fixed="right">
         <template slot-scope="scope">
           <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeDataById(scope.row.id)" title="删除" />
         </template>
       </el-table-column>
     </el-table>
 
         <!-- 分页组件 -->
     <el-pagination
       :current-page="page"
       :total="total"
       :page-size="limit"
       style="padding: 30px 0; text-align: center;"
       layout="total, prev, pager, next, jumper"
       @current-change="fetchData"
     />
 
   </div>
 </template>
 
 <script>
 
 import api from '@/api/operlog/operlog.js'
 
 export default {
  data(){
    return{
       listLoading: false, // 数据是否正在加载
       list: [], // banner列表
       total: 0, // 数据库中的总记录数
       page: 1, // 默认页码
       limit: 5, // 每页记录数
       searchObj: {}, // 查询表单对象
       // 处理时间范围条件的
       createTimes: [],
       dialogVisible: false,
       sysUser: {},
       saveBtnDisabled: false,
 
       dialogRoleVisible: false,
       allRoles: [], // 所有角色列表
       userRoleIds: [], // 用户的角色ID的列表
       isIndeterminate: false, // 是否是不确定的
       checkAll: false // 是否全选
    }
  },
  created(){
     this.fetchData();
  },
  methods:{
   // 修改状态
   switchStatus(row){
     // 如果 可用  设置为 不可用  如果不可用  设置为 可用
     row.status=row.status === 1 ? 0 : 1;
     api.updateStatus(row.id,row.status).then(response=>{
       //a.给出提示
       this.$message.success(response.message||'操作成功');
       //b.再次刷新列表
       this.fetchData();
     })
   },
   resetData(){
     // 1.清空条件搜索
     this.searchObj={};
     //2.清空日期条件
     this.createTimes=[];
     //3.刷新数据列表
     this.fetchData();
   },
     fetchData(page=1){
         this.page = page;
          if(this.createTimes && this.createTimes.length ==2) {
          this.searchObj.createTimeBegin = this.createTimes[0];
          this.searchObj.createTimeEnd = this.createTimes[1];
          }
 
         api.getOperLogPageInfo(this.page, this.limit, this.searchObj).then(
         response => {
           //this.list = response.data.list
           this.list = response.data.records;
           this.total = response.data.total;
           // 数据加载并绑定成功
           this.listLoading = false;
         }
       )   
     },
           // 删除操作
 removeDataById(id) {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        api.removeId(id)
          .then((response) => {
          // 提示
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          // 刷新页面
          this.fetchData();
        });
      });
    },

    
  }
 }
 </script>
 
 <style>
 
 </style>