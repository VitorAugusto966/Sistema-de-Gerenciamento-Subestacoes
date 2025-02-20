<template>
  <header class="header">
    <h1 @click="goHome">Substation Manager <span class="versao">Versão 1.0</span></h1>
    <nav>
      <a href="/home">🏠 Home</a>
      <a href="/subEstacaoList">⚡ Subestações</a>
      <a href="/mapSub">🌍 Mapa</a>
      <button @click="logout" class="logout-btn">🚪 Sair</button>
    </nav>
  </header>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const logout = () => {
  sessionStorage.clear()
  localStorage.clear()
  router.push('/')
}

const goHome = () => {
  router.push('/home')
}
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  background-color: #007bff;
  color: white;
  padding: 15px 20px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.15);
  border-radius: 4px;
  margin-bottom: 3px;
}

.header h1 {
  font-size: 22px;
  font-weight: bold;
  display: flex;
  align-items: center;
  gap: 10px;
}

.versao {
  font-size: 14px;
  font-weight: normal;
  opacity: 0.8;
}

nav {
  display: flex;
  gap: 15px;
}

nav a {
  color: white;
  text-decoration: none;
  font-weight: 600;
  padding: 8px 12px;
  border-radius: 5px;
  transition: background 0.3s ease-in-out;
}

nav a:hover {
  background: rgba(255, 255, 255, 0.2);
}

.logout-btn {
  background: none;
  border: none;
  color: white;
  font-weight: 600;
  cursor: pointer;
  padding: 8px 12px;
  border-radius: 5px;
  transition: background 0.3s ease-in-out;
}

.logout-btn:hover {
  background: rgba(255, 255, 255, 0.2);
}

h1:hover {
  cursor: pointer;
}
</style>
