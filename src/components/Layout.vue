<template>
  <el-container class="layout-container">
    <el-aside width="200px" class="aside">
      <div class="logo">Vue Admin</div>
      <el-menu :default-active="activeMenu" mode="vertical" class="menu" router>
        <el-menu-item index="/home">
          <el-icon><HomeFilled /></el-icon>
          <span>首页</span>
        </el-menu-item>
        <el-menu-item index="/users">
          <el-icon><User /></el-icon>
          <span>用户管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="header">
        <span class="title">管理后台</span>
        <el-button link @click="handleLogout" class="logout-btn">退出登录</el-button>
      </el-header>
      <el-main class="main">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { HomeFilled, User } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

const router = useRouter()
const route = useRoute()

const activeMenu = computed(() => route.path)

const handleLogout = () => {
  localStorage.removeItem('token')
  ElMessage.success('退出成功')
  router.push('/login')
}
</script>

<style scoped>
.layout-container {
  height: 100vh;
}

.aside {
  background: #2a3542;
  color: #fff;
}

.logo {
  height: 60px;
  line-height: 60px;
  text-align: center;
  font-size: 18px;
  font-weight: bold;
  background: #1f2937;
}

.menu {
  border-right: none;
  background: transparent;
}

.menu :deep(.el-menu-item) {
  color: #fff;
}

.menu :deep(.el-menu-item:hover) {
  background: #3a4553;
}

.menu :deep(.el-menu-item.is-active) {
  background: #409eff;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  background: #fff;
  border-bottom: 1px solid #e4e7ed;
}

.title {
  font-size: 18px;
  font-weight: bold;
  color: #303133;
}

.logout-btn {
  color: #909399;
}

.logout-btn:hover {
  color: #409eff;
}

.main {
  padding: 20px;
  background: #f5f7fa;
}
</style>