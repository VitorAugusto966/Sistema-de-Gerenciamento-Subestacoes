<template>
  <div class="container-list">
    <h1>Lista de SubEstações</h1>
    <table>
      <thead>
        <tr>
          <th>Código</th>
          <th>Nome</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-if="subestacoes.length === 0">
          <td colspan="3" id="msg-vazia">SubEstações vazia</td>
        </tr>
        <tr v-for="sub in subestacoes" :key="sub.id">
          <td style="font-weight: bold">{{ sub.codigo }}</td>
          <td style="font-weight: bold">{{ sub.nome || 'Sem Nome' }}</td>
          <td>
            <button class="edit" @click="editar(sub)">✏️ Editar</button>
            <button class="delete" @click="confirmarExclusao(sub.id, sub.codigo)">
              🗑️ Excluir
            </button>
            <button class="map" @click="exibirNoMapa(sub)">🌍 Exibir</button>
          </td>
        </tr>
      </tbody>
    </table>

    <router-link to="/subEstacaoForm" class="add-button">Incluir SubEstação</router-link>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import '@/styles/subEstacaoList.css'
import { useRouter } from 'vue-router'
import { useToast } from 'vue-toastification'

const router = useRouter()
const toast = useToast()

const subestacoes = ref([])

const carregarSubestacoes = async () => {
  try {
    const response = await fetch('http://localhost:9099/subEstacao', {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
    })

    if (!response.ok) {
      throw new Error(`Erro HTTP: ${response.status}`)
    }

    const data = await response.json()
    console.log('Resposta da API:', data)

    if (Array.isArray(data)) {
      subestacoes.value = data
    } else {
      console.error('Formato de resposta inesperado:', data)
    }
  } catch (error) {
    console.error('Erro ao buscar subestações:', error)
    toast.error('Erro ao carregar as subestações!')
  }
}

const editar = (sub) => {
  console.log(sub)
  sessionStorage.setItem('subestacao', JSON.stringify(sub))
  router.push({ path: '/subestacaoUpd' })
}

const confirmarExclusao = (id, codigo) => {
  toast.warning(`Clique novamente para excluir a Subestação ${codigo}`, {
    timeout: 5000,
    closeOnClick: true,
    onClick: () => excluir(id, codigo),
  })
}

const excluir = async (id, codigo) => {
  try {
    const response = await fetch(`http://localhost:9099/subEstacao/${id}`, {
      method: 'DELETE',
    })

    if (!response.ok) {
      throw new Error(`Erro HTTP: ${response.status}`)
    }

    subestacoes.value = subestacoes.value.filter((sub) => sub.id !== id)

    toast.success(`Subestação ${codigo} excluída com sucesso!`)
  } catch (error) {
    console.error('Erro ao excluir:', error)
    toast.error('Erro ao excluir a subestação!')
  }
}

const exibirNoMapa = (sub) => {
  sessionStorage.setItem('subestacao', JSON.stringify(sub))
  router.push({ path: '/subestacaoMap' })
}

onMounted(carregarSubestacoes)
onMounted(() => {
  document.title = 'SubEstações'
})
</script>
