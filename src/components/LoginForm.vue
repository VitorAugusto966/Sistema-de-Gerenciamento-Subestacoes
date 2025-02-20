<template>
  <div class="login-container">
    <h2>Login</h2>
    <input type="email" v-model="email" placeholder="Email" required />
    <input type="password" v-model="password" placeholder="Senha" required />
    <button @click="login" id="entrar">Entrar</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from 'vue-toastification'
import '@/styles/login.css'

const email = ref('')
const password = ref('')
const router = useRouter()
const toast = useToast()

const login = () => {
  toast.success('Login efetuado com sucesso!')
  router.push('/home')
}
</script>
