<template>
  <el-container>
    <el-header height="60px">
      <el-menu
        :default-active="activeIndex2"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"

        active-text-color="#ffd04b">
        <el-menu-item index="1"> 未结案件</el-menu-item>
        <el-menu-item index="2"> 全部案件</el-menu-item>
        <el-menu-item index="3"> 已结案件</el-menu-item>

        <el-submenu index="4">
          <template slot="title">常用工具</template>
          <el-menu-item index="4-1">选项1</el-menu-item>
          <el-menu-item index="4-2">选项2</el-menu-item>
          <el-menu-item index="4-3">选项3</el-menu-item>
          <el-submenu index="4-4">
            <template slot="title">选项4</template>
            <el-menu-item index="4-4-1">选项1</el-menu-item>
            <el-menu-item index="4-4-2">选项2</el-menu-item>
            <el-menu-item index="4-4-3">选项3</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-menu-item index="5" disabled>消息中心</el-menu-item>
        <el-menu-item index="6" style="text-align: right; font-size: 12px"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
      </el-menu>
    </el-header>
    <el-main>
      <cases-list ></cases-list>
    </el-main>


  </el-container>



</template>

<script>
  import CasesList from './CasesList';

  export default {
    components: {CasesList},
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'

      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
        CasesList.methods.handleect(key);
      }
    }
  }
</script>

<style scoped>


</style>
