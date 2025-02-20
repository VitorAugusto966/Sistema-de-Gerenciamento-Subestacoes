<template>
  <Header />
  <div id="form-sub-map">
    <div class="form-section-map">
      <h3>SubEstação - Alteração</h3>
      <div class="form-grid-map">
        <div class="input-group">
          <label for="codigo">Código</label>
          <input
            id="codigo"
            class="input-form"
            v-model="subestacao.codigo"
            placeholder="Código"
            maxlength="3"
            disabled
          />
        </div>
        <div class="input-group">
          <label for="nome">Nome</label>
          <input
            id="nome"
            class="input-form"
            v-model="subestacao.nome"
            placeholder="Nome"
            disabled
          />
        </div>
        <div class="input-group">
          <label for="latitude">Latitude</label>
          <input
            id="latitude"
            class="input-form"
            v-model="subestacao.latitude"
            placeholder="Latitude"
            type="number"
            disabled
          />
        </div>
        <div class="input-group">
          <label for="longitude">Longitude</label>
          <input
            id="longitude"
            class="input-form"
            v-model="subestacao.longitude"
            placeholder="Longitude"
            type="number"
            disabled
          />
        </div>
      </div>
    </div>
  </div>
  <SubEstacaoMap />
  <button id="btn-adicionar" @click="voltar">Voltar</button>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import SubEstacaoMap from '@/components/SubEstacaoMap.vue'
import Header from '@/components/Header.vue'
import '@/styles/subEstacaoMap.css'
const router = useRouter()

const subestacao = ref({
  id: '',
  codigo: '',
  nome: '',
  latitude: '',
  longitude: '',
  redesMT: [],
})

const redes = ref([])
const editandoIndex = ref(null)
const redeEditando = ref({ nome: '' })

onMounted(() => {
  document.title = 'SubEstações - Mapa'
  const data = sessionStorage.getItem('subestacao')
  if (data) {
    subestacao.value = JSON.parse(data)
    redes.value = subestacao.value.redesMT || []
  }
})

const voltar = () => {
  router.push({ path: '/subestacaoList' })
}
</script>
