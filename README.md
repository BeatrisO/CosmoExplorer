### CosmoExplorer

Aplicativo de estudo desenvolvido para explorar imagens e informações do universo utilizando APIs públicas como NASA APOD e SpaceX API.
O app exibe a imagem astronômica do dia com explicações detalhadas e também permite visualizar informações completas sobre os foguetes da SpaceX.
O projeto foi construído para praticar Jetpack Compose, consumo de APIs REST, navegação entre telas e boas práticas de arquitetura no Android.

### Funcionalidades
* Exibição da imagem do dia fornecida pela NASA APOD
* Informações como título, explicação e data
* Visualização de foguetes da SpaceX
* Detalhes completos de cada foguete: nome, descrição, status operacional, data do primeiro voo, taxa de sucesso, custo por lançamento, link para Wikipedia e imagem do foguete
* Interface construída com Jetpack Compose
* Carregamento de imagens em alta resolução utilizando Coil
* Layout com rolagem vertical
* Abertura de links externos (Wikipedia) via Intent


### Tecnologias Utilizadas
* Kotlin
* Jetpack Compose
* Retrofit
* ViewModel
* Coroutines
* StateFlow
* Coil
* Bottom Navigation
* Material Design 3

### APIs Utilizadas
NASA APOD
* Retorna imagem astronômica do dia, título, data e explicação científica

SpaceX API 
* Retorna lista de foguetes da SpaceX, nome, descrição, status operacional, data do primeiro voo, taxa de sucesso, custo por lançamento, link para Wikipedia e imagem do foguete

### Melhorias Futuras
* Histórico de imagens da NASA
* Busca por data personalizada
* Tema claro/escuro
