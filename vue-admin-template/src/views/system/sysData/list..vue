<template>
    <div>
      <el-row>
        <el-col :span="12">  <div id="regesiter_num" :style="{ height: '300px' }"></div></el-col>
        <el-col :span="12">  <div id="login_num" :style="{ height: '300px' }"></div></el-col>
  </el-row>
  
  <el-row>
        <el-col :span="12">  <div id="video_view_num" :style="{ height: '300px' }"></div></el-col>
        <el-col :span="12">  <div id="pic_view_num" :style="{ height: '300px' }"></div></el-col>
  </el-row>
  
    
    </div>
  </template>

  <script>
import * as echarts from "echarts";

export default {
  data() {
    return {};
  },
  mounted() {
    this.initCharts("regesiter_num", "注册人数统计");
    this.initCharts("login_num", "登录人数统计");
    this.initCharts("video_view_num", "视频访问人数统计");
    this.initCharts("pic_view_num", "图片访问人数统计");
  },
  created() {},
  methods: {
    initCharts(type, title) {
      //   alert(1);
      // 基于准备好的dom，初始化echarts实例
      // 基于准备好的dom，初始化echarts实例
      // 基于准备好的dom，初始化echarts实例
      var myChart = echarts.init(document.getElementById(type));

      myChart.setOption({
        title: {
          text: title,
        },
        tooltip: {},
        xAxis: {
          data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"],
        },
        yAxis: {},
        series: [
          {
            name: "销量",
            type: "line",
            data: [5, 20, 36, 10, 10, 20],
          },
        ],
      });
    },
  },
};
</script>