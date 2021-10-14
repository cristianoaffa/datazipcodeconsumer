# DataZipCodeConsumer

Esse projeto tem como objetivo validar o endereço de um consumidor através da entrada como parâmetro do valor do CEP e retornar os dados do endereço. 

## 🚀 Estrutura

Este projeto foi feito na linguagem JAVA com o framework SpringBoot. Escolhi a IDE Eclipse para fazer o desenvolvimento.
Para começar basta importá-lo na IDE que desejar como um projeto Maven.

Foi utilizado o banco de dados MongoDB para a construção de uma massa de testes para validação do projeto.

Foi adotado a organização em Controller, Service e Repository para melhor entendimento do fluxo e camadas do processo.

Também foi adicionada a configuração do "Swagger" para simular o endpoint da Controller.
Após iniciar o projeto na IDE pode se acessar o "Swagger" pelo endereço: "http://localhost:8080/swagger-ui.html"

### 📋 Criação da Massa de dados no MongoDB

Para realizar essa criação na sua máquina local siga os passos abaixo:
	1 - Instalar o MongoDB Server;
	2 - Instalar o cliente Robo3T para utiliizarmos em nossas simulações;
	3 - Criar uma nova conexão através do Robo3T, com o endereço: "localhost" e porta: "27017"(padrão);
	4 - Copiar o script "2021_10_11_inserts_zipcode_info.js" que se encontra na pasta "scripts" dentro do projeto e colar no diretório onde se encontra o executável do Robo3T;
	5 - Abrir o Shell de execução de scripts clicando na opção: "Open Shell" sobre a conexão criada;
	6 - Executar o comando: 
			load("2021_10_11_inserts_zipcode_info.js");

Após esses passos será criado o documento: "zipCode_info" com a collection: "dataZipCode" contendo a massa de CEPs e seus dados correspondentes.
 
### 🔧 Testes

Foram realizados testes na Controller, Service e Repository para garantir o funcionamento da API.
Realizei o "Mock" do Service e Repository para a validação nos testes.
Utilizei Junit e Mockito para realização dos testes.