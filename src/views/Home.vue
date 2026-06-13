<template>
  <div class="home-container">
    <el-card class="welcome-card">
      <div class="welcome-content">
        <h2>欢迎来到管理后台</h2>
        <p>这是一个基于 Vue3 + Element Plus 的管理系统</p>
      </div>
      <div class="stats">
        <el-statistic title="用户总数" :value="1280" suffix="人" />
        <el-statistic title="今日访问" :value="256" suffix="次" />
        <el-statistic title="在线用户" :value="32" suffix="人" />
      </div>
    </el-card>
  </div>
</template>

<script setup>
</script>

<style scoped>
.home-container {
  padding: 20px;
}

.welcome-card {
  min-height: 300px;
}

.welcome-content {
  text-align: center;
  padding: 40px 0;
}

.welcome-content h2 {
  font-size: 28px;
  color: #303133;
  margin-bottom: 10px;
}

.welcome-content p {
  color: #606266;
  font-size: 16px;
}

.stats {
  display: flex;
  justify-content: center;
  gap: 80px;
  margin-top: 30px;
}

.stats :deep(.el-statistic) {
  text-align: center;
}

.stats :deep(.el-statistic__value) {
  font-size: 36px;
  color: #409eff;
}

.stats :deep(.el-statistic__label) {
  font-size: 14px;
  color: #909399;
}
</style>