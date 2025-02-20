<template>
  <Header />
  <div class="home-container">
    <h1>Bem-vindo ao Sistema de Gerenciamento de Subestações</h1>

    <div class="dashboard">
      <div class="card">
        <h3>Total de Subestações</h3>
        <p>{{ totalSubestacoes }}</p>
      </div>
      <div class="card">
        <h3>Status da Rede</h3>
        <p :class="statusClass">{{ statusRede }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import SubEstacaoMap from '@/components/SubEstacaoMap.vue'
import Header from '@/components/Header.vue'

const router = useRouter()
const totalSubestacoes = ref(0)
const statusRede = ref('Normal')

const carregarDados = async () => {
  try {
    const response = await fetch('http://localhost:9099/subEstacao')
    const data = await response.json()
    totalSubestacoes.value = data.length
  } catch (error) {
    console.error('Erro ao carregar dados:', error)
  }
}

onMounted(carregarDados)
onMounted(() => {
  document.title = 'Home'
})
</script>

<style scoped>
.home-container {
  text-align: center;
  padding: 20px;
  height: 90%;
}

.dashboard {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-bottom: 20px;
  margin-top: 20px;
}

.card {
  background: white;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 200px;
}
</style>
