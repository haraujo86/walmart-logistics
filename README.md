# Sobre o sistema #
O sistema tem como objetivo a inclusão de mapas para posterior calculo de menor rota e custo de um determinado trajeto.

# Tecnologias #

* JDK 1.7
* Gradle 2.1
* Spring 4.0.7.RELEASE
* Hibernate 4.3.6
* Jetty 8.1.15.v20140411
* HSQLDB 2.0.0

Foi optado pela ferramenta de build Gradle 2.1 (http://www.gradle.org/) para toda organização de dependência e build do projeto.

Foi utilizado como base o projeto Spring Boot (http://projects.spring.io/spring-boot/) para criação de um projeto com base no Spring e que rode de maneira *stand-alone*. Com isso, já é possível toda a base do projeto baseada no Spring 4 e com todo recurso para disponibilizar um serviço REST para interação com o sistema.

Para a camada de dados, foi utilizado o Hibernate 4 (http://hibernate.org/) com intuito de ter uma API de persistência de dados que facilite toda interação ORM com o banco de dados HSQLDB 2.0.0 utilizado em memória.

Como o projeto é *stand-alone*, tem-se a possibilidade de rodar o projeto com servidor de aplicações Jetty 8 que vem *embeded* na estrutura da aplicação.

# Instalando a aplicação #

Obtendo o código fonte do BitBucket:

```
#!java
git clone git@bitbucket.org:haraujo86/walmart-logistics.git
```

Fazendo a build e obtendo dependências do projeto. Essa task já inclui checagem de análise estática de código, testes unitários e compilação da aplicação:

```
#!java
gradle build
```

Para rodar a aplicação com o Jetty embeded na aplicação:

```
#!java
gradle run
```

Caso queira rodar os testes separadamente:

```
#!java
gradle tests
```

Caso queira rodar as checagens da aplicação separadamente:

```
#!java
gradle check
```

Alternativa para rodar a aplicação, você poderá ir até a pasta ./build/libs e executar o jar existente nessa pasta:

```
#!java
java -jar desafio-walmart-1.0.0.jar
```


# Criando um mapa #
Estrutura de um POST para criar um mapa com rotas para posterior consulta de menor rotas e preço do trajeto:

*Endereço*:  http://localhost:8090/map/create

```
#!json

{
    "name":":map_name",
    "routes": [
                {"origin":":city_origin", "destination":":city_destination", "distance":":distance_value"},
                {"origin":":city_origin", "destination":":city_destination", "distance":":distance_value"},
                {"origin":":city_origin", "destination":":city_destination", "distance":":distance_value"},
                {"origin":":city_origin", "destination":":city_destination", "distance":":distance_value"},
                {"origin":":city_origin", "destination":":city_destination", "distance":":distance_value"},
                {"origin":":city_origin", "destination":":city_destination", "distance":":distance_value"}
              ]
}

```

Explicação dos parâmetros

* name= [Obrigatório] Nome do mapa. Exemplo:mapa1, mapa_SP
* origin = [Obrigatório] Nome da cidade de origem. Exemplos: São Paulo, Araraquara, Campinas, Rio de Janeiro....
* destination = [Obrigatório] Nome da cidade de destino (vizinha). Exemplos: São Carlos, Santos, Niterói....
* distance = [Obrigatório] Distância entre as cidades de origem e destino. Para criar a rota. Valores decimais permitidos.

*Obs: O uso de um ponto (.) para separar a parte inteira da parte decimal está como padrão.*

# Calculando menor rota e o custo do trajeto #

Calcular a menor rota e o custo entre duas cidades de um mapa. Para isso é necessário especificar na chamada o mapa, ponto de origem, ponto de destino, média de consumo do veículo e preço do combustível utilizado.

Estrutura de um GET para consultar um mapa com rota tendo origem e destino. 


```
#!java

http://localhost:8090/map/calculate/?map=<map_name>&origin=<city_origin>&destination=<city_destination>&oilAverageConsumption=<oil_consumption>&oilPrice=<oil_price>
```

Explicação dos parâmetros

* map = [Obrigatório] Nome do mapa. Exemplo: SP, RJ, Mapa1,etc.
* origin = [Obrigatório] Nome da cidade de origem. Exemplos: São Paulo, Araraquara, Campinas, Rio de Janeiro....
* destination = [Obrigatório] Nome da cidade de destino (vizinha). Exemplos: São Carlos, Santos, Niterói....
* oilAverageConsumption= [Obrigatório][Maior igual a 0] Consumo do veículo. Exemplo  10.1 km/l. Permite valores decimais.
* oilPrice = [Obrigatório][Maior igual a 0] Preço do valor do combustível. Exemplo: 2.50 reais por litro. Permite valores decimais.

Permite valores decimais.

*Obs: O uso de um ponto (.) para separar a parte inteira da parte decimal está como padrão.*