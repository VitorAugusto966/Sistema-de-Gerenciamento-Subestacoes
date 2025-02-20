<template>
  <div id="map"></div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'
import L from 'leaflet'
import 'leaflet/dist/leaflet.css'

const map = ref(null)

const initMap = () => {
  if (map.value) {
    map.value.remove()
  }

  const data = sessionStorage.getItem('subestacao')

  if (data) {
    const subestacao = JSON.parse(data)

    if (subestacao.latitude && subestacao.longitude) {
      map.value = L.map('map').setView([subestacao.latitude, subestacao.longitude], 13)

      L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; OpenStreetMap contributors',
      }).addTo(map.value)

      const marker = L.marker([subestacao.latitude, subestacao.longitude]).addTo(map.value)
      marker
        .bindPopup(
          `<b>${subestacao.nome}</b><br>Latitude: ${subestacao.latitude}<br>Longitude: ${subestacao.longitude}`,
        )
        .openPopup()
    } else {
      console.warn('Coordenadas inválidas para exibição do mapa.')
    }
  }
}

onMounted(() => {
  initMap()
})

onBeforeUnmount(() => {
  if (map.value) {
    map.value.remove()
  }
})
</script>

<style>
#map {
  height: 500px;
  width: 100%;
  border-radius: 8px;
  margin-top: 20px;
  box-shadow: 0px 2px 8px rgba(0, 0, 0, 0.1);
}
</style>
