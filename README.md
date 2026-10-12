
# 🧵 API Tapeçaria — Gestão de Clientes, Orçamentos e Serviços

---

## 📌 Sobre o Projeto

Este projeto foi construído em formato tutorial/guiado para que qualquer pessoa consiga entender como estruturar do zero uma API Spring Boot em camadas, aplicando boas práticas de arquitetura REST, persistência com Spring Data JPA, validações de formulário e segurança.

---

## 🚀 Passo 1: Inicializando o Projeto no Spring Initializr

Para recriar a estrutura inicial da API, acesse o site oficial do [Spring Initializr](https://start.spring.io/):

1. **Project:** Maven
2. **Language:** Java
3. **Spring Boot:** `3.x.x` (ou a versão estável mais recente)
4. **Project Metadata:**
* **Group:** `com.tapecaria`
* **Artifact:** `cadastroUsuario`
* **Name:** `cadastroUsuario`
* **Packaging:** Jar
* **Java:** `17`



### 📦 Dependências Necessárias

No painel à direita, clique em **Add Dependencies** e adicione as seguintes bibliotecas:

* **Spring Web (`spring-boot-starter-webmvc`)**: Para criar os endpoints REST e controladores HTTP.
* **Spring Data JPA (`spring-boot-starter-data-jpa`)**: Para mapear entidades relacionais e criar repositórios de dados.
* **H2 Database (`h2`)**: Banco de dados relacional leve em memória, ideal para desenvolvimento local.
* **Lombok (`lombok`)**: Biblioteca para geração automática de Getters, Setters, Construtores e Builder via anotações.
* **Spring Boot DevTools (`spring-boot-devtools`)**: Proporciona reinicialização automática da aplicação ao alterar o código.
* **Validation (`spring-boot-starter-validation`)**: Para validar regras nos DTOs/Entidades (como `@NotNull`, `@Email`, `@NotBlank`).
* **Spring Security (`spring-boot-starter-security`)**: Para controle de autenticação e proteção de rotas.

Após selecionar tudo, clique no botão **GENERATE** (ou pressione `Ctrl + Enter`), extraia o arquivo `.zip` gerado e abra a pasta em sua IDE de preferência.

---

## 🛠️ Dependências do `pom.xml`

Caso queira comparar com o seu arquivo `pom.xml`, o projeto utiliza a seguinte estrutura de dependências:

```xml
<dependencies>
    <!-- Web REST API -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webmvc</artifactId>
    </dependency>

    <!-- Persistência de Dados (JPA) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Banco de Dados em Memória -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Suporte ao Console do H2 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-h2console</artifactId>
    </dependency>

    <!-- Produtividade e Boilerplate -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- Live Reload em Dev -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>

    <!-- Validação de Dados -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Autenticação e Segurança -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Testes Unitários e de Integração -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webmvc-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>

```

---

## 📐 Passo 2: Ordem de Criação dos Pacotes e Arquivos

Para manter uma arquitetura limpa, desacoplada e organizada[cite: 4], crie os pacotes dentro do caminho `src/main/java/com/tapecaria/cadastrousuario/` na seguinte ordem de desenvolvimento[cite: 4]:

> [!TIP]
> Para visualizar o código-fonte de cada etapa, acesse o diretório correspondente em:
> **`src > main > java > com > tapecaria > cadastrousuario > [nome_do_pacote]`**[cite: 4]

### 1️⃣ Pacote `entity`

* **Descrição:** É o coração do modelo de dados[cite: 4]. Aqui são criadas as classes anotadas com `@Entity` que representam as tabelas do banco de dados[cite: 1, 4].
* **Ordem recomendada de criação:**
1. **`Cliente.java`**: Dados do cliente (nome, telefone, endereço, CPF/CNPJ)[cite: 1].
2. **`Usuario.java`**: Usuários do sistema (login, senha, perfil de acesso)[cite: 1].
3. **`Orcamento.java`**: Dados do serviço/orçamento solicitados pelo cliente (descrição do móvel/estofado, tipo de tecido, valor estimado, status)[cite: 1].


* 📁 *Consulte os arquivos na pasta:* **`src > main > java > com > tapecaria > cadastrousuario > entity`**[cite: 4]

---

### 2️⃣ Pacote `repository`

* **Descrição:** Interfaces que estendem o `JpaRepository`[cite: 4]. Elas fornecem automaticamente métodos prontos para salvar, buscar, atualizar e deletar registros no banco de dados sem precisar escrever SQL manual.
* **Classes do pacote:**
1. `ClienteRepository.java`
2. `UsuarioRepository.java`
3. `OrcamentoRepository.java`


* 📁 *Consulte os arquivos na pasta:* **`src > main > java > com > tapecaria > cadastrousuario > repository`**[cite: 4]

---

### 3️⃣ Pacote `service`

* **Descrição:** Camada contendo todas as regras de negócio da aplicação[cite: 4] (ex: verificação de duplicidade de cliente, cálculo de orçamento, validação de regras de desconto, tratamento de exceções).
* **Classes do pacote:**
1. `ClienteService.java`
2. `UsuarioService.java`
3. `OrcamentoService.java`


* 📁 *Consulte os arquivos na pasta:* **`src > main > java > com > tapecaria > cadastrousuario > service`**[cite: 4]

---

### 4️⃣ Pacote `controller`

* **Descrição:** Camada responsável por expor os endpoints REST (HTTP GET, POST, PUT, DELETE)[cite: 4]. O controller recebe as requisições, aciona o `service` correspondente e devolve as respostas em formato JSON.
* **Classes do pacote:**
1. `ClienteController.java`
2. `UsuarioController.java`
3. `OrcamentoController.java`


* 📁 *Consulte os arquivos na pasta:* **`src > main > java > com > tapecaria > cadastrousuario > controller`**[cite: 4]

---

## 🗂️ Estrutura Final de Pastas

```text
src
└── main
    └── java
        └── com
            └── tapecaria
                └── cadastrousuario
                    ├── entity         <-- 1º: Mapeamento de Clientes, Usuários e Orçamentos
                    ├── repository     <-- 2º: Interfaces JPA
                    ├── service        <-- 3º: Regras de negócio
                    └── controller     <-- 4º: Endpoints REST

```

*(Consulte a árvore de arquivos na pasta `src/main` do repositório[cite: 4]).*

---

## 🏃‍♀️ Como Executar a Aplicação Localmente

1. **Clone o repositório:**
```bash
git clone https://github.com/seu-usuario/seu-repositorio.git

```


2. **Entre na pasta do projeto:**
```bash
cd seu-repositorio

```


3. **Execute a aplicação via Maven:**
```bash
./mvnw spring-boot:run

```


4. **Acesse o banco de dados H2 Console:**
* URL: `http://localhost:8080/h2-console`
* JDBC URL: `jdbc:h2:mem:testdb`
* User: `sa`
* Password: *(em branco)*

---


## 🏷️ Guia de Anotações (Annotations), Dependências e Trechos de Código

### 1. Spring Data JPA (`spring-boot-starter-data-jpa`)

> **Pacote:** `jakarta.persistence.*`

* **`@Entity`**
* **Função:** Define que a classe é uma entidade mapeada para uma tabela no banco de dados.
* **Origem:** `Cliente.java` e `Orcamento.java`


```java
@Entity
@Table(name = "tb_clientes")
public class Cliente { ... }

```


* **`@Table`**
* **Função:** Especifica o nome exato da tabela no banco de dados.
* **Origem:** `Cliente.java` (`tb_clientes`) e `Orcamento.java` (`tb_orcamento`)


```java
@Table(name = "tb_orcamento")

```


* **`@Id`**
* **Função:** Marca o atributo que representa a chave primária (*Primary Key*).
* **Origem:** `Cliente.java` e `Orcamento.java`


```java
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private Long id;

```


* **`@GeneratedValue`**
* **Função:** Define a estratégia de geração automática do ID (neste caso, usando `SEQUENCE`).
* **Origem:** `Cliente.java` e `Orcamento.java`


```java
@GeneratedValue(strategy = GenerationType.SEQUENCE)

```


* **`@Column`**
* **Função:** Mapeia configurações da coluna. `nullable = false` aplica a restrição `NOT NULL`.
* **Origem:** `Cliente.java` e `Orcamento.java`


```java
@Column(nullable = false)
private Long id;

```



---

### 2. Lombok (`lombok`)

> **Pacote:** `lombok.*`

* **`@Data`**
* **Função:** Gera automaticamente Getters, Setters, `equals`, `hashCode` e `toString`.
* **Origem:** `Cliente.java` e `Orcamento.java`


```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente { ... }

```


* **`@Getter` e `@Setter**`
* **Função:** Geram individualmente os métodos de leitura e escrita. *(Apesar de redundantes quando usados junto com `@Data`, são funcionais)*.
* **Origem:** `Cliente.java` e `Orcamento.java`


```java
@Getter
@Setter
public class Orcamento { ... }

```


* **`@AllArgsConstructor` e `@NoArgsConstructor**`
* **Função:** Criam, respectivamente, o construtor com todos os parâmetros e o construtor vazio (exigido pelo JPA).
* **Origem:** `Cliente.java` e `Orcamento.java`


```java
@AllArgsConstructor
@NoArgsConstructor

```



---

### 3. Spring Web (`spring-boot-starter-webmvc`)

> **Pacote:** `org.springframework.web.bind.annotation.*`

* **`@RestController`**
* **Função:** Indica que a classe é um controlador REST que retorna respostas diretamente serializadas em JSON.
* **Origem:** `ClienteController.java` e `OrcamentoController.java`


```java
@RestController
@RequestMapping("api/clientes")
public class ClienteController { ... }

```


* **`@RequestMapping`**
* **Função:** Mapeia a URL/rota base do recurso da API.
* **Origem:** `ClienteController.java` (`"api/clientes"`) e `OrcamentoController.java` (`"api/orcamentos"`)


```java
@RequestMapping("api/orcamentos")

```


* **`@CrossOrigin`**
* **Função:** Libera o acesso CORS para requisições vindas de aplicações front-end (ex: React, Angular).
* **Origem:** `ClienteController.java` e `OrcamentoController.java`


```java
@CrossOrigin(origins = "*")

```


* **`@PostMapping`, `@GetMapping`, `@DeleteMapping**`
* **Função:** Mapeiam os verbos HTTP para criação, consulta e deleção.
* **Origem:** `ClienteController.java` e `OrcamentoController.java`


```java
@PostMapping
public Cliente criarCliente(@RequestBody Cliente cliente) { ... }

@GetMapping("/{id}")
public Cliente buscarClientePorId(@PathVariable Long id) { ... }

@DeleteMapping("/{id}")
public void excluirUsuario(@PathVariable Long id) { ... }

```


* **`@RequestBody` e `@PathVariable**`
* **Função:** `@RequestBody` converte o JSON enviado no corpo do HTTP para objeto Java. `@PathVariable` captura parâmetros passados diretamente na URL.
* **Origem:** `ClienteController.java` e `OrcamentoController.java`


```java
public Cliente buscarClientePorId(@PathVariable Long id)
public Cliente criarCliente(@RequestBody Cliente cliente)

```



---

### 4. Spring Framework Core (`spring-boot-starter`)

> **Pacote:** `org.springframework.stereotype.*` e `org.springframework.beans.factory.annotation.*`

* **`@Service`**
* **Função:** Declara a classe como componente de serviço onde residem as regras de negócio.
* **Origem:** `OrcamentoService.java`


```java
@Service
public class OrcamentoService {
    @Autowired
    private OrcamentoRepository repository;
    ...
}

```


* **`@Autowired`**
* **Função:** Injeta automaticamente instâncias gerenciadas pelo Spring (como Repositórios e Serviços).
* **Origem:** `ClienteController.java`, `OrcamentoController.java` e `OrcamentoService.java`


```java
@Autowired
private ClienteRepository clienteRepository;

```

---

## 🧮 Lógica e Regras dos Cálculos de Orçamento

Para garantir **agilidade na navegação do usuário** e **segurança total dos dados financeiros**, o sistema aplica o cálculo do orçamento em duas camadas complementares:

---

### 1. Fórmula Matemática Base

O valor total do orçamento é composto pelo somatório do consumo individual dos materiais (metragem multiplicada pelo preço por metro) acrescido dos custos adicionais/diversos:

$$\text{Valor Total} = (\text{Metro}_{\text{Tecido}} \times \text{Preço}_{\text{Tecido}}) + (\text{Metro}_{\text{Manta}} \times \text{Preço}_{\text{Manta}}) + (\text{Metro}_{\text{Espuma}} \times \text{Preço}_{\text{Espuma}}) + \text{Diversos}$$

---

### ⚛️ 2. Lógica no Front-end (`Orcamento.jsx`)

* **Finalidade (UX):** Atualizar o campo *Valor Total* instantaneamente na tela enquanto o usuário digita no formulário, sem recarregar a página ou disparar chamadas HTTP desnecessárias.
* **Cálculo Derivado:** O valor total é recalculado dinamicamente a cada mudança de estado (`handleChange`), utilizando o operador de fallback (`|| 0`) para evitar erros de sintaxe caso o usuário deixe algum campo em branco ou apague um número.

```javascript
// Exemplo da conversão segura realizada no React
const metroEspuma = parseFloat(formData.metro_espuma) || 0;
const precoEspuma = parseFloat(formData.preco_metro_espuma) || 0;

const metroManta = parseFloat(formData.metro_manta) || 0;
const precoManta = parseFloat(formData.preco_metro_manta) || 0;

const metroTecido = parseFloat(formData.metro_tecido) || 0;
const precoTecido = parseFloat(formData.preco_metro_tecido) || 0;

const diversos = parseFloat(formData.diversos) || 0;

// Somatório final formatado para exibição (2 casas decimais)
const totalCalculado = (metroEspuma * precoEspuma) +
                       (metroManta * precoManta) +
                       (metroTecido * precoTecido) +
                       diversos;

```

---

### ☕ 3. Lógica no Back-end (`OrcamentoService.java`)

* **Finalidade (Segurança e Integridade):** Funciona como a **fonte da verdade**. O servidor ignora ou valida o valor enviado pela requisição para impedir fraudes, alterações no inspetor do navegador (*DevTools*) ou arredondamentos incorretos.
* **Uso de `BigDecimal`:** Todos os valores financeiros no Java utilizam a classe `BigDecimal` para evitar perda de precisão decimal comum em variáveis do tipo `double` ou `float`.
* **Tratamento de Nulos:** Antes de efetuar as multiplicações, a `Service` verifica e substitui qualquer parâmetro `null` por `BigDecimal.ZERO`, protegendo a aplicação contra exceções (`NullPointerException`).

```java
// Método executado pela camada Service antes de salvar no repositório
public void calcularValorTotal() {
    BigDecimal totalTecido = (metragemTecido != null && precoMetroTecido != null)
            ? metragemTecido.multiply(precoMetroTecido) : BigDecimal.ZERO;

    BigDecimal totalManta = (metragemManta != null && precoMetroManta != null)
            ? metragemManta.multiply(precoMetroManta) : BigDecimal.ZERO;

    BigDecimal totalEspuma = (metragemEspuma != null && precoMetroEspuma != null)
            ? metragemEspuma.multiply(precoMetroEspuma) : BigDecimal.ZERO;

    BigDecimal valorDiversos = (frete != null) ? frete : BigDecimal.ZERO;

    this.valorTotal = totalTecido
            .add(totalManta)
            .add(totalEspuma)
            .add(valorDiversos);
}

```

---

> [!NOTE]
> **Defesa em Profundidade:** A combinação dessas duas abordagens garante **alta responsividade para o usuário no navegador** sem comprometer a **segurança financeira da tapeçaria no banco de dados**.
