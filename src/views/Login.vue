<template>
  <div class="login-container">
    <div class="login-form">
      <div class="logo-wrapper">
        <el-icon class="logo-icon"><UserFilled /></el-icon>
        <h2 class="title">系统登录</h2>
      </div>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="0" class="form">
        <el-form-item prop="username">
          <el-input 
            v-model="form.username" 
            placeholder="请输入用户名"
            prefix-icon="User"
            size="large"
          />
        </el-form-item>
        <el-form-item prop="password">
          <el-input 
            v-model="form.password" 
            type="password" 
            placeholder="请输入密码"
            prefix-icon="Lock"
            size="large"
          />
        </el-form-item>
        <el-form-item class="remember-item">
          <el-checkbox v-model="form.remember">记住我</el-checkbox>
        </el-form-item>
        <el-form-item>
          <el-button 
            type="primary" 
            @click="handleLogin" 
            class="login-btn"
            :loading="loading"
            size="large"
          >
            登录
          </el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { UserFilled } from '@element-plus/icons-vue'

const router = useRouter()
const formRef = ref(null)
const loading = ref(false)

const form = reactive({
  username: '',
  password: '',
  remember: false
})

const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 3, max: 20, message: '用户名长度在3到20个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 30, message: '密码长度在6到30个字符', trigger: 'blur' }
  ]
}

const handleLogin = async () => {
  try {
    const valid = await formRef.value.validate()
    if (!valid) return

    loading.value = true

    const mockResponse = {
      code: 200,
      message: '登录成功',
      data: {
        token: 'mock-token-12345'
      }
    }
    localStorage.setItem('token', mockResponse.data.token)
    if (form.remember) {
      localStorage.setItem('username', form.username)
      localStorage.setItem('remember', 'true')
    } else {
      localStorage.removeItem('username')
      localStorage.removeItem('remember')
    }
    ElMessage.success('登录成功')
    router.push('/home')
  } catch (error) {
    console.error('Login error:', error)
    ElMessage.error('登录失败，请检查用户名或密码')
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  const remember = localStorage.getItem('remember')
  if (remember === 'true') {
    form.username = localStorage.getItem('username') || ''
    form.remember = true
  }
})
</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.login-form {
  background: #fff;
  padding: 48px 40px;
  border-radius: 16px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.15);
  width: 420px;
}

.logo-wrapper {
  text-align: center;
  margin-bottom: 32px;
}

.logo-icon {
  font-size: 48px;
  color: #409eff;
  margin-bottom: 12px;
}

.title {
  font-size: 24px;
  font-weight: bold;
  color: #303133;
  margin: 0;
}

.form :deep(.el-input__wrapper) {
  border-radius: 8px;
}

.remember-item {
  margin-bottom: 20px;
}

.login-btn {
  width: 100%;
  height: 44px;
  font-size: 16px;
  border-radius: 8px;
}
</style>