# ⚡ Sistema de Gerenciamento de Subestações

Este projeto é um sistema completo para cadastro, edição, listagem e exclusão de subestações elétricas, incluindo redes de média tensão. O sistema foi desenvolvido como parte de um **teste técnico** e utiliza **Spring Boot** no backend e **Vue.js** no frontend, com **Leaflet** para exibição das subestações em um mapa interativo.

## 🚀 Tecnologias Utilizadas

### 🔙 Backend
- ☕ **Java** com **Spring Boot**
- 🗄 **Spring Data JPA** para persistência
- 🛢 **MySQL** como banco de dados
- 🌐 **Spring Web** para a API REST

### 🎨 Frontend
- 🖥 **Vue.js 3**
- 🚏 **Vue Router** para navegação
- 🗺 **Leaflet** para mapas interativos
- 🔔 **Vue Toastification** para notificações (toast)
- 🎨 **CSS** para estilização

## ⚡ Funcionalidades
- 📜 **Listagem de Subestações**
- ✏️ **Cadastro de novas Subestações**
- 🔄 **Edição de Subestações**
- ❌ **Exclusão de Subestações**
- 🔗 **Associação de redes de média tensão (MT) a subestações**
- 🗺 **Exibição de Subestações em um mapa interativo**
- 🔔 **Notificações via toast em vez de alertas nativos**

## 📡 Endpoints da API

### ⚡ SubEstações (`/subEstacao`)
- **GET** `/subEstacao` - 📜 Lista todas as subestações
- **GET** `/subEstacao/{id}` - 🔍 Busca uma subestação pelo ID
- **POST** `/subEstacao` - ➕ Cria uma nova subestação
- **PUT** `/subEstacao/{id}` - ✏️ Atualiza uma subestação existente
- **DELETE** `/subEstacao/{id}` - 🗑 Exclui uma subestação

### 🔌 Redes de MT (`/redesMT`)
- **GET** `/redesMT` - 📜 Lista todas as redes de média tensão
- **GET** `/redesMT/{id}` - 🔍 Busca uma rede pelo ID
- **POST** `/redesMT` - ➕ Cria uma nova rede de média tensão
- **DELETE** `/redesMT/{id}` - 🗑 Exclui uma rede de média tensão

## ▶️ Como Rodar o Projeto

### 🔙 Backend (Spring Boot)
1. 🛠 Configure o banco de dados MySQL e atualize `application.properties`
2. ▶️ Rode o backend com:
   ```sh
   mvn spring-boot:run
   ```

### 🎨 Frontend (Vue.js)
1. 📦 Instale as dependências:
   ```sh
   npm install
   ```
2. ▶️ Inicie o servidor de desenvolvimento:
   ```sh
   npm run dev
   ```

## 🎯 Considerações Finais
Este projeto foi desenvolvido para demonstrar competências técnicas em **Java, Spring Boot, Vue.js** e **Leaflet**, incluindo boas práticas de desenvolvimento backend e frontend.
