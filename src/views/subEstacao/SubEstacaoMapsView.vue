<template>
  <Header />
  <div class="map-container">
    <div id="map"></div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import L from 'leaflet'
import 'leaflet/dist/leaflet.css'
import Header from '@/components/Header.vue'

const subestacoes = ref([])

onMounted(async () => {
  try {
    const response = await fetch('http://localhost:9099/subEstacao')
    if (!response.ok) throw new Error('Erro ao buscar subestações')
    subestacoes.value = await response.json()

    const map = L.map('map').setView([-23.5505, -46.6333], 10)

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors',
    }).addTo(map)

    subestacoes.value.forEach((estacao) => {
      if (estacao.latitude && estacao.longitude) {
        L.marker([estacao.latitude, estacao.longitude])
          .addTo(map)
          .bindPopup(`<b>${estacao.nome}</b><br>Código: ${estacao.codigo}`)
      }
    })
  } catch (error) {
    console.error(error)
  }
})

onMounted(() => {
  document.title = 'Mapa de SubEstações'
})
</script>

<style scoped>
.map-container {
  width: 100%;
  height: 100vh;
}

#map {
  width: 100%;
  height: 90%;
}
</style>
