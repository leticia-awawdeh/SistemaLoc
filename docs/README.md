# SistemaLoc - Guia de Execução

Feito por Letícia Awawdeh

Este documento descreve os passos necessários para executar o projeto *SistemaLoc*, que é um sistema baseado em Java e gerenciado com Maven.

## Pré-requisitos
Antes de começar, certifique-se de ter os seguintes itens instalados em sua máquina:

1. *Java Development Kit (JDK):* versão 8 ou superior.
   - Para verificar a versão do Java instalada, execute:
     sh
     java -version
     
2. *Apache Maven:* versão 3.6.0 ou superior.
   - Para verificar a versão do Maven instalada, execute:
     sh
     mvn -version
     
3. *IDE (opcional):* Recomenda-se o uso do [NetBeans](https://netbeans.apache.org/) ou outra IDE de sua escolha para facilitar o desenvolvimento e execução.

## Configuração Inicial
1. *Clone ou extraia o projeto*:
   - Caso ainda não tenha feito, extraia o arquivo SistemaLoc-master.zip ou clone o repositório caso esteja usando Git.
   
   sh
   git clone <url-do-repositorio>
   cd SistemaLoc-master
   

2. *Verifique as dependências*:
   - No diretório raiz do projeto, execute o comando Maven para baixar as dependências:
     sh
     mvn clean install
     

## Executando o Projeto

### 1. Usando o Maven
Execute o comando abaixo para iniciar o sistema:
sh
mvn exec:java


### 2. Usando uma IDE
- Descompacte o arquivo SistemaLoc-master.zip
- Importe o projeto na sua IDE.
  - Em NetBeans, selecione Arquivo > Abrir Projeto e escolha o diretório SistemaLoc-master.
- Localize a classe principal no diretório (SisLoc.java) src/main/java/SistemaLocacao/UI > SisLoc.java.
- Execute o projeto clicando no botão direito em cima do arquivo e escolha Run File ou aperte shift + F6 .

## Estrutura do Projeto
- src/main/SistemaLocacao: Contém o código-fonte principal separado em Classes e UI (Interface gráfica).
- test: Contém testes unitários das classes e ada Interface
- target: Diretório onde os artefatos compilados são gerados após a construção do projeto.
- pom.xml: Arquivo de configuração do Maven.

---

Para dúvidas ou problemas, consulte a documentação do código ou entre em contato com o desenvolvedor responsável.
