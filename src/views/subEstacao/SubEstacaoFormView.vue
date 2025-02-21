<template>
  <Header />
  <div id="form-sub">
    <div class="form-section">
      <h3>SubEstação</h3>
      <div class="form-grid">
        <div class="input-group">
          <label for="codigo">Código</label>
          <input id="codigo" class="input-form" v-model="novaSubestacao.codigo" placeholder="Código" maxlength="3" />
        </div>
        <div class="input-group">
          <label for="nome">Nome</label>
          <input id="nome" class="input-form" v-model="novaSubestacao.nome" placeholder="Nome" />
        </div>
        <div class="input-group">
          <label for="latitude">Latitude</label>
          <input id="latitude" class="input-form" v-model="novaSubestacao.latitude" placeholder="Latitude"
            type="number" />
        </div>
        <div class="input-group">
          <label for="longitude">Longitude</label>
          <input id="longitude" class="input-form" v-model="novaSubestacao.longitude" placeholder="Longitude"
            type="number" />
        </div>
      </div>
    </div>

    <div class="form-section">
      <h3>Rede MT</h3>
      <div class="form-grid">
        <div class="input-group">
          <label for="codigo-rede">Código</label>
          <input id="codigo-rede" class="input-form" v-model="rede.codigo" placeholder="Código" maxlength="5" />
        </div>
        <div class="input-group">
          <label for="nome-rede">Nome</label>
          <input id="nome-rede" class="input-form" v-model="rede.nome" placeholder="Nome" />
        </div>
        <div class="input-group">
          <label for="tensao-rede">Tensão (kV)</label>
          <input id="tensao-rede" class="input-form" v-model="rede.tensao" placeholder="Tensão" type="number" />
        </div>
        <button class="btn-add" @click="adicionarRede">Adicionar</button>
      </div>

      <div class="tabela-container">
        <h3>Redes MT adicionadas</h3>
        <table>
          <thead>
            <tr>
              <th>Código</th>
              <th>Nome</th>
              <th>Tensão (kV)</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr v-if="redes.length === 0">
              <td colspan="4" class="mensagem-vazia">Nenhuma rede adicionada</td>
            </tr>
            <tr v-for="(r, index) in redes" :key="index">
              <td>{{ r.codigo }}</td>
              <td>{{ r.nome }}</td>
              <td>{{ r.tensao }}</td>
              <td>
                <button class="btn-excluir" @click="removerRede(index)">Excluir</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div class="buttons-container">
      <button class="btn-cancelar" @click="router.push('/subEstacaoList')">Cancelar</button>
      <button class="btn-incluir" @click="salvarSubestacao">Incluir</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from 'vue-toastification'
import '@/styles/subEstacaoForm.css'
import Header from '@/components/Header.vue'

const router = useRouter()
const toast = useToast()

const novaSubestacao = ref({
  codigo: '',
  nome: '',
  latitude: '',
  longitude: '',
  redesMT: [],
})

const rede = ref({
  codigo: '',
  nome: '',
  tensao: '',
})

const redes = ref([])

const adicionarRede = () => {
  if (!rede.value.codigo || !rede.value.nome || !rede.value.tensao) {
    toast.error('Preencha todos os campos para adicionar uma rede!')
    return
  }
  redes.value.push({ ...rede.value })
  rede.value.codigo = ''
  rede.value.nome = ''
  rede.value.tensao = ''
  toast.success('Rede adicionada com sucesso!')
}

const removerRede = (index) => {
  redes.value.splice(index, 1)
  toast.info('Rede removida!')
}

const salvarSubestacao = async () => {
  try {
    if (novaSubestacao.value.codigo.length > 3) {
      toast.warning('O código deve ter no máximo 3 caracteres!')
      return
    }
    novaSubestacao.value.redesMT = redes.value
    const response = await fetch('http://localhost:9099/subEstacao', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(novaSubestacao.value),
    })
    if (!response.ok) throw new Error(`Erro HTTP: ${response.status}`)

    toast.success('Subestação salva com sucesso!')
    router.push('/subEstacaoList')
  } catch (error) {
    toast.error('Erro ao salvar a subestação!')
  }
}

onMounted(() => {
  document.title = 'SubEstações - Cadastro'
})
</script>
