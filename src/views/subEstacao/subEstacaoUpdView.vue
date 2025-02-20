<template>
  <Header />
  <div id="form-sub-upd">
    <div class="form-section-upd">
      <h3>SubEstação - Alteração</h3>
      <div class="form-grid-upd">
        <input
          class="input-form"
          v-model="subestacao.codigo"
          placeholder="Código"
          maxlength="3"
          disabled
        />
        <input class="input-form" v-model="subestacao.nome" placeholder="Nome" />
        <input
          class="input-form"
          v-model="subestacao.latitude"
          placeholder="Latitude"
          type="number"
        />
        <input
          class="input-form"
          v-model="subestacao.longitude"
          placeholder="Longitude"
          type="number"
        />
      </div>
    </div>
    <div class="form-section-upd">
      <h3>Rede MT</h3>
      <div class="form-grid-upd">
        <input class="input-form" v-model="rede.codigo" placeholder="Código" maxlength="5" />
        <input class="input-form" v-model="rede.nome" placeholder="Nome" />
        <button class="btn-add" @click="adicionarRede">Adicionar</button>
      </div>

      <div class="tabela-container-upd">
        <h3>Redes MT adicionadas</h3>
        <table>
          <thead>
            <tr>
              <th>Código</th>
              <th>Nome</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr v-if="redes.length === 0">
              <td colspan="3" class="mensagem-vazia">Nenhuma rede adicionada</td>
            </tr>
            <tr v-for="(r, index) in redes" :key="index">
              <td>{{ r.codigo }}</td>
              <td>
                <input
                  v-if="editandoIndex === index"
                  v-model="redeEditando.nome"
                  class="input-form"
                />
                <span v-else>{{ r.nome }}</span>
              </td>
              <td>
                <button
                  class="btn-editar"
                  v-if="editandoIndex === index"
                  @click="salvarEdicao(index)"
                >
                  Salvar
                </button>
                <button class="btn-editar" v-else @click="alterarRede(index)">Alterar</button>
                <button class="btn-excluir" @click="removerRede(index)">Excluir</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="buttons-container">
      <button class="btn-cancelar" @click="cancelar">Cancelar</button>
      <button class="btn-incluir" @click="atualizarSubestacao">Concluir edição</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useToast } from 'vue-toastification'
import Header from '@/components/Header.vue'
import '@/styles/subEstacaoUpd.css'

const router = useRouter()
const toast = useToast()
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
  const data = sessionStorage.getItem('subestacao')
  if (data) {
    subestacao.value = JSON.parse(data)
    redes.value = subestacao.value.redesMT || []
  }
})

const rede = ref({
  codigo: '',
  nome: '',
})

const adicionarRede = () => {
  if (rede.value.codigo && rede.value.nome) {
    redes.value.push({ ...rede.value })
    rede.value.codigo = ''
    rede.value.nome = ''
    toast.success('Rede adicionada com sucesso!')
  } else {
    toast.error('Preencha todos os campos!')
  }
}

const cancelar = () => {
  router.push('/subEstacaoList')
}

const removerRede = (index) => {
  redes.value.splice(index, 1)
  toast.info('Rede removida!')
}

const alterarRede = (index) => {
  editandoIndex.value = index
  redeEditando.value = { nome: redes.value[index].nome }
}

const salvarEdicao = (index) => {
  redes.value[index].nome = redeEditando.value.nome
  editandoIndex.value = null
  toast.success('Rede alterada com sucesso!')
}

const atualizarSubestacao = async () => {
  try {
    subestacao.value.redesMT = redes.value

    const response = await fetch(`http://localhost:9099/subEstacao/${subestacao.value.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(subestacao.value),
    })

    if (!response.ok) {
      throw new Error(`Erro HTTP: ${response.status}`)
    }

    toast.success('Subestação atualizada com sucesso!')
    router.push('/subEstacaoList')
  } catch (error) {
    console.error('Erro ao atualizar:', error)
    toast.error('Erro ao atualizar a subestação!')
  }
}

onMounted(() => {
  document.title = 'SubEstações - Alterar'
})
</script>
