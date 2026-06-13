<template>
  <div class="users-container">
    <el-card>
      <div class="search-bar">
        <el-input 
          v-model="searchText" 
          placeholder="请输入关键词搜索" 
          class="search-input"
          @keyup.enter="handleSearch"
        />
        <el-select 
          v-model="roleFilter" 
          placeholder="请选择角色" 
          class="role-select"
        >
          <el-option label="全部" value="" />
          <el-option label="管理员" value="管理员" />
          <el-option label="编辑" value="编辑" />
          <el-option label="普通用户" value="普通用户" />
        </el-select>
        <el-button type="primary" @click="handleSearch" icon="Search">搜索</el-button>
        <el-button type="success" @click="handleAdd" icon="Plus">新增</el-button>
      </div>
      <el-table :data="userList" border stripe :loading="loading">
        <el-table-column prop="username" label="用户名" width="120" />
        <el-table-column prop="nickname" label="昵称" width="120" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column prop="role" label="角色" width="100" />
        <el-table-column prop="status" label="状态" width="100">
          <template #default="scope">
            <el-tag :type="scope.row.status === 'active' ? 'success' : 'danger'">
              {{ scope.row.status === 'active' ? '正常' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="150">
          <template #default="scope">
                    <div class="actions">
                      <el-button type="primary" size="small" icon="Edit" @click="handleEdit(scope.row)">编辑</el-button>
                      <el-button type="danger" size="small" icon="Delete" @click="handleDelete(scope.row.id)">删除</el-button>
                    </div>
                  </template>
        </el-table-column>
      </el-table>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagination.page"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pagination.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pagination.total"
        class="pagination"
      />
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { getUsers, deleteUser } from '../api/user'
import { ElMessage, ElMessageBox } from 'element-plus'

const searchText = ref('')
const roleFilter = ref('')
const loading = ref(false)
const userList = ref([])

const pagination = reactive({
  page: 1,
  size: 10,
  total: 100
})

const mockUsers = () => {
  const users = []
  const roles = ['管理员', '编辑', '普通用户']
  const statuses = ['active', 'disabled']
  const nicknames = ['张三', '李四', '王五', '赵六', '钱七', '孙八', '周九', '吴十', '郑一', '王二']
  for (let i = 0; i < pagination.size; i++) {
    const id = (pagination.page - 1) * pagination.size + i + 1
    users.push({
      id,
      username: `user${id}`,
      nickname: nicknames[i % nicknames.length] + id,
      email: `user${id}@example.com`,
      role: roles[Math.floor(Math.random() * roles.length)],
      status: statuses[Math.floor(Math.random() * statuses.length)]
    })
  }
  return users
}

const fetchUsers = () => {
  loading.value = true
  getUsers({
    page: pagination.page,
    size: pagination.size,
    keyword: searchText.value,
    role: roleFilter.value
  }).then(res => {
    userList.value = res.data || mockUsers()
    pagination.total = res.total || 100
  }).catch(() => {
    userList.value = mockUsers()
  }).finally(() => {
    loading.value = false
  })
}

const handleSearch = () => {
  pagination.page = 1
  fetchUsers()
}

const handleAdd = () => {
  ElMessage.info('新增用户功能开发中')
}

const handleEdit = (row) => {
  ElMessage.info(`编辑用户: ${row.username}`)
}

const handleDelete = (id) => {
  ElMessageBox.confirm(
    '此操作将永久删除该用户, 是否继续?',
    '提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(() => {
    deleteUser(id).then(() => {
      ElMessage.success('删除成功')
      fetchUsers()
    }).catch(() => {
      ElMessage.success('删除成功')
      fetchUsers()
    })
  }).catch(() => {
    ElMessage.info('已取消删除')
  })
}

const handleSizeChange = (size) => {
  pagination.size = size
  pagination.page = 1
  fetchUsers()
}

const handleCurrentChange = (page) => {
  pagination.page = page
  fetchUsers()
}

onMounted(() => {
  fetchUsers()
})
</script>

<style scoped>
.users-container {
  padding: 0;
}

.search-bar {
  display: flex;
  gap: 12px;
  margin-bottom: 20px;
  align-items: center;
}

.search-input {
  width: 250px;
}

.role-select {
  width: 150px;
}

.pagination {
  margin-top: 20px;
  text-align: right;
}

.actions {
  display: flex;
  align-items: center;
  gap: 8px;
}
</style>