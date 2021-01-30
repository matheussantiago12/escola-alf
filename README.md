# Escola Alf

## Tecnologias utilizadas
 - Spring Boot (Java)
 - O dados estão sendo salvos em um bando de dados em memória (H2) para facilitar o setup da aplicação, porém não alterou em nada o código.

Para rodar a aplicação, clone ou faça o download do repositório e utilize o comando mvn install (Maven).

## Testando a aplicação

### Primeiramente vamos cadastrar dois gabaritos. Essa é a prova que o professor elaborou, então precisamos passar a resposta de cada questão e o seu peso.

**POST | localhost:8080/gabarito**

`{
    "questoes": [
        {
            "resposta": "a",
            "peso": 4
        },
        {
            "resposta": "b",
            "peso": 3
        },
        {
            "resposta": "c",
            "peso": 3
        }
    ]
}`

`{
    "questoes": [
        {
            "resposta": "a",
            "peso": 4
        },
        {
            "resposta": "b",
            "peso": 2
        },
        {
            "resposta": "c",
            "peso": 4
        }
    ]
}`

**Atenção: O peso total de todas as questões precisa ser exatamente 10.**

### Agora iremos cadastrar as provas entregues pelos alunos.

**POST | localhost:8080/prova**

`{
    "prova": {
        "aluno": "Lucas",
        "gabaritoId": 1
    },
    "respostas": ["a", "b", "c"]
}`

`{
    "prova": {
        "aluno": "Lucas",
        "gabaritoId": 2
    },
    "respostas": ["a", "b", "a"]
}`

`{
    "prova": {
        "aluno": "Matheus",
        "gabaritoId": 1
    },
    "respostas": ["b", "c", "a"]
}`

`{
    "prova": {
        "aluno": "Matheus",
        "gabaritoId": 2
    },
    "respostas": ["a", "b", "b"]
}`

**Após cada inserção, a API retorna a nota que o aluno tirou na prova.**

### Para checar a média dos alunos, precisamos fazer a requisição abaixo: 

**GET | localhost:8080/boletim/**

A API irá retornar o nome de cada aluno, seguido por sua nota média entre todas as provas.

### Para buscarmos apenas os alunos aprovados, fazemos a seguinte requisição:

**GET | localhost:8080/boletim/aprovados**

A API irá retornar um vetor com os nomes dos alunos aprovados.
