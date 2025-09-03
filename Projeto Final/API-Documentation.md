# API Documentation - Sistema Acadêmico

## Base URL
```
http://localhost:8080/academico
```

## Headers Padrão
```
Content-Type: application/json
Accept: application/json
```

---

## 👨‍🎓 Alunos API

### GET /alunos
Lista todos os alunos com paginação e busca

**Query Parameters:**
- `page` (int, optional): Número da página (default: 0)
- `size` (int, optional): Tamanho da página (default: 10)
- `search` (string, optional): Termo de busca no nome

**Response:**
```json
{
  "content": [
    {
      "idaluno": 1,
      "nome": "Maria Silva",
      "sexo": "Feminino",
      "dt_nasc": "2010-01-03",
      "cursos": [
        {
          "idCurso": 1,
          "nomeCurso": "Banco de Dados"
        }
      ]
    }
  ],
  "totalPages": 1,
  "totalElements": 6,
  "size": 10,
  "number": 0
}
```

### GET /alunos/{id}
Busca aluno por ID

**Path Parameters:**
- `id` (int): ID do aluno

**Response:**
```json
{
  "idaluno": 1,
  "nome": "Maria Silva",
  "sexo": "Feminino",
  "dt_nasc": "2010-01-03",
  "cursos": [
    {
      "idCurso": 1,
      "nomeCurso": "Banco de Dados"
    }
  ]
}
```

### POST /alunos
Cria novo aluno

**Request Body:**
```json
{
  "nome": "João Silva",
  "sexo": "Masculino",
  "dt_nasc": "1995-05-15"
}
```

**Response:** `201 Created`
```json
{
  "idaluno": 7,
  "nome": "João Silva",
  "sexo": "Masculino",
  "dt_nasc": "1995-05-15",
  "cursos": []
}
```

### PUT /alunos/{id}
Atualiza aluno existente

**Path Parameters:**
- `id` (int): ID do aluno

**Request Body:**
```json
{
  "nome": "João Silva Santos",
  "sexo": "Masculino",
  "dt_nasc": "1995-05-15"
}
```

**Response:** `200 OK`
```json
{
  "idaluno": 7,
  "nome": "João Silva Santos",
  "sexo": "Masculino",
  "dt_nasc": "1995-05-15",
  "cursos": []
}
```

### DELETE /alunos/{id}
Exclui aluno

**Path Parameters:**
- `id` (int): ID do aluno

**Response:** `204 No Content`

---

## 📚 Cursos API

### GET /cursos
Lista todos os cursos com paginação e busca

**Query Parameters:**
- `page` (int, optional): Número da página (default: 0)
- `size` (int, optional): Tamanho da página (default: 10)
- `search` (string, optional): Termo de busca no nome

**Response:**
```json
{
  "content": [
    {
      "idCurso": 1,
      "nomeCurso": "Banco de Dados",
      "alunos": [
        {
          "idaluno": 1,
          "nome": "Maria Silva",
          "sexo": "Feminino",
          "dt_nasc": "2010-01-03"
        }
      ]
    }
  ],
  "totalPages": 1,
  "totalElements": 3,
  "size": 10,
  "number": 0
}
```

### GET /cursos/{id}
Busca curso por ID

**Path Parameters:**
- `id` (int): ID do curso

**Response:**
```json
{
  "idCurso": 1,
  "nomeCurso": "Banco de Dados",
  "alunos": [
    {
      "idaluno": 1,
      "nome": "Maria Silva",
      "sexo": "Feminino",
      "dt_nasc": "2010-01-03"
    }
  ]
}
```

### POST /cursos
Cria novo curso

**Request Body:**
```json
{
  "nomeCurso": "Desenvolvimento Web"
}
```

**Response:** `201 Created`
```json
{
  "idCurso": 4,
  "nomeCurso": "Desenvolvimento Web",
  "alunos": []
}
```

### PUT /cursos/{id}
Atualiza curso existente

**Path Parameters:**
- `id` (int): ID do curso

**Request Body:**
```json
{
  "nomeCurso": "Desenvolvimento Web Avançado"
}
```

**Response:** `200 OK`
```json
{
  "idCurso": 4,
  "nomeCurso": "Desenvolvimento Web Avançado",
  "alunos": []
}
```

### DELETE /cursos/{id}
Exclui curso

**Path Parameters:**
- `id` (int): ID do curso

**Response:** `204 No Content`

---

## 🎓 Matrículas API

### GET /matriculas
Lista todas as matrículas do sistema

**Response:**
```json
[
  {
    "idAluno": 1,
    "idCurso": 1,
    "nomeAluno": "Maria Silva",
    "nomeCurso": "Banco de Dados"
  },
  {
    "idAluno": 1,
    "idCurso": 2,
    "nomeAluno": "Maria Silva",
    "nomeCurso": "Programação Web"
  }
]
```

### GET /matriculas/aluno/{idAluno}
Lista matrículas de um aluno específico

**Path Parameters:**
- `idAluno` (int): ID do aluno

**Response:**
```json
[
  {
    "idAluno": 1,
    "idCurso": 1,
    "nomeAluno": "Maria Silva",
    "nomeCurso": "Banco de Dados"
  },
  {
    "idAluno": 1,
    "idCurso": 2,
    "nomeAluno": "Maria Silva",
    "nomeCurso": "Programação Web"
  }
]
```

### GET /matriculas/curso/{idCurso}
Lista matrículas de um curso específico

**Path Parameters:**
- `idCurso` (int): ID do curso

**Response:**
```json
[
  {
    "idAluno": 1,
    "idCurso": 1,
    "nomeAluno": "Maria Silva",
    "nomeCurso": "Banco de Dados"
  },
  {
    "idAluno": 3,
    "idCurso": 1,
    "nomeAluno": "Pedro Santos",
    "nomeCurso": "Banco de Dados"
  }
]
```

### POST /matriculas
Matricula aluno em curso

**Request Body:**
```json
{
  "idAluno": 1,
  "idCurso": 2
}
```

**Response:** `201 Created`
```json
{
  "idAluno": 1,
  "idCurso": 2,
  "nomeAluno": "Maria Silva",
  "nomeCurso": "Programação Web"
}
```

### DELETE /matriculas/aluno/{idAluno}/curso/{idCurso}
Remove matrícula específica

**Path Parameters:**
- `idAluno` (int): ID do aluno
- `idCurso` (int): ID do curso

**Response:** `204 No Content`

---

## ❌ Códigos de Erro

### 400 Bad Request
**Validação de dados:**
```json
{
  "timestamp": "2025-09-03T22:30:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "O nome do aluno é obrigatório",
  "path": "/academico/alunos"
}
```

### 404 Not Found
**Recurso não encontrado:**
```json
{
  "timestamp": "2025-09-03T22:30:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Aluno não encontrado com ID: 999",
  "path": "/academico/alunos/999"
}
```

### 409 Conflict
**Conflito de dados (ex: matrícula já existe):**
```json
{
  "timestamp": "2025-09-03T22:30:00.000+00:00",
  "status": 409,
  "error": "Conflict",
  "message": "Aluno já está matriculado neste curso",
  "path": "/academico/matriculas"
}
```

### 500 Internal Server Error
**Erro interno do servidor:**
```json
{
  "timestamp": "2025-09-03T22:30:00.000+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "Erro interno do servidor",
  "path": "/academico/alunos"
}
```

---

## 🧪 Exemplos com cURL

### Listar alunos com paginação
```bash
curl "http://localhost:8080/academico/alunos?page=0&size=5&search=Maria"
```

### Criar novo aluno
```bash
curl -X POST "http://localhost:8080/academico/alunos" \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Ana Costa",
    "sexo": "Feminino",
    "dt_nasc": "1998-03-20"
  }'
```

### Matricular aluno em curso
```bash
curl -X POST "http://localhost:8080/academico/matriculas" \
  -H "Content-Type: application/json" \
  -d '{
    "idAluno": 1,
    "idCurso": 3
  }'
```

### Buscar aluno por ID (com cursos)
```bash
curl "http://localhost:8080/academico/alunos/1"
```

### Listar matrículas de um aluno
```bash
curl "http://localhost:8080/academico/matriculas/aluno/1"
```

### Remover matrícula
```bash
curl -X DELETE "http://localhost:8080/academico/matriculas/aluno/1/curso/3"
```

### Atualizar curso
```bash
curl -X PUT "http://localhost:8080/academico/cursos/1" \
  -H "Content-Type: application/json" \
  -d '{
    "nomeCurso": "Banco de Dados Avançado"
  }'
```

### Excluir aluno
```bash
curl -X DELETE "http://localhost:8080/academico/alunos/7"
```

---

## 📋 Regras de Negócio

### Validações de Aluno
- **Nome**: Obrigatório, máximo 80 caracteres, não pode ser vazio
- **Sexo**: Obrigatório, máximo 30 caracteres, não pode ser vazio
- **Data de Nascimento**: Obrigatória, formato válido (YYYY-MM-DD)

### Validações de Curso
- **Nome do Curso**: Obrigatório, máximo 100 caracteres, não pode ser vazio

### Regras de Matrícula
- **Aluno e Curso**: Devem existir no sistema
- **Duplicatas**: Não é permitido matricular o mesmo aluno no mesmo curso duas vezes
- **Integridade**: Exclusão de aluno/curso remove automaticamente suas matrículas

### Relacionamentos
- **Many-to-Many**: Um aluno pode ter múltiplos cursos, um curso pode ter múltiplos alunos
- **Cascata**: Exclusão em cascata mantém integridade referencial
- **Circular References**: Evitadas através de DTOs simples

---

## 🔍 Swagger/OpenAPI

Documentação interativa disponível em:
```
http://localhost:8080/academico/swagger-ui.html
```

Especificação OpenAPI em JSON:
```
http://localhost:8080/academico/v3/api-docs
```

---

## 🚀 Notas de Performance

- **Paginação**: Implementada server-side para otimizar consultas grandes
- **Lazy Loading**: Relacionamentos carregados sob demanda
- **Índices**: Criados automaticamente nas chaves primárias e estrangeiras
- **Connection Pool**: Configurado para ambiente de produção
- **Cache**: Hibernate Second Level Cache habilitado

---

## 🛡️ Segurança

- **CORS**: Configurado para permitir requests do frontend (localhost:4200)
- **Validação**: Bean Validation (JSR-303) em todos os DTOs
- **SQL Injection**: Prevenido através de JPA/Hibernate
- **Headers**: Security headers configurados no Spring Boot
