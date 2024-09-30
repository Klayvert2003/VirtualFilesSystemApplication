# Como utilizar o projeto

## Pré-requisitos
Antes de começar, você precisa ter estes serviços instalados em sua máquina:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Como iniciar a aplicação

Para iniciar a aplicação, basta direcionar-se a pasta 'docker' localizada na raiz do projeto. Para subir os conteineres abra o terminal na pasta e execute o seguinte comando:
```bash
docker-compose up --build
```
Caso apresente erro, significa que você está possui uma versão do Docker que não utiliza o 'docker-compose' e sim 'docker compose', neste caso, utilize o comando:
```bash
docker compose up --build
```

Prontinho! Após o build você tera acesso ao frontend pela url 'http://localhost:4200' e o backend na url 'http://localhost:8080'.

# Informações adicionais
## Banco de dados
Caso haja interesse em acessar o banco de dados, é possível acessar o mesmo com as seguintes credenciais:
```
Nome do Banco: file_system
Usuário: postgres
Senha: postgres
Porta: 5432
```

## Visualização dos endpoints
Para melhor visualização dos endpoints da API, recomendo que utilize o swagger do projeto, presente na url: 'http://localhost:8080/swagger-ui/index.html'

Mas ainda sim, recomendo utilizar o Insomnia(ou a aplicação que for mais familiarizado), ir até a raiz do projeto e acessar a pasta 'documentation' e depois 'insomnia-collection' importarndo então o arquivo 'insomnia-collection.json'. Este arquivo trará todas as requests da aplicação direto para o Insomnia, facilitando assim o uso da API.