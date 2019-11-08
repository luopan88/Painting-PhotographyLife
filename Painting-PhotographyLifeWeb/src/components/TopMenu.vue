<template>
  <div>
    <el-menu
      class="menu"
      mode="horizontal"
      height="40px"
      background-color="rgba(36,33,46,0.48)"
      text-color="#fff"
      active-text-color="#aaa"
    >
      <el-menu-item index="1">首页</el-menu-item>
      <el-menu-item index="2">排行榜</el-menu-item>
      <el-menu-item index="3">广场</el-menu-item>

      <!--    <el-submenu index="2">
        <template slot="title">绘画</template>
        <el-menu-item index="2-1">选项1</el-menu-item>
        <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项1</el-menu-item>
        </el-submenu>
      </el-submenu> -->
      <!-- <el-menu-item index="5" class="right">个人中心</el-menu-item> -->
      <el-menu-item index="4" class="right">
        <el-avatar icon="el-icon-user-solid"></el-avatar>
      </el-menu-item>
    </el-menu>
    <el-input class="center- search" size="mini" suffix-icon="el-icon-search"></el-input>
  </div>
</template>

<script></script>

<style scoped>
.menu {
  border-radius: 0 0 4px 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  border: 0 !important;
  padding: 0 10%;
  min-width: 630px;
}
.el-menu-item {
  height: 50px !important;
  line-height: 50px !important;
  background-color: transparent !important;
}
.el-menu-item:hover {
  background-color: rgba(36, 33, 46, 0.8) !important;
}
.search {
  width: 200px;
  height: 50px;
  line-height: 50px;
  display: block;
  top: 0;
}
</style>
