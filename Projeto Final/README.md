# Sistema Acadêmico - Full Stack

Sistema completo de gerenciamento acadêmico desenvolvido com **Spring Boot** (backend) e **Angular** (frontend), utilizando **PostgreSQL** como banco de dados.

## 📋 Funcionalidades

### Gerenciamento de Cursos
- ✅ Listar cursos com paginação
- ✅ Buscar cursos por nome
- ✅ Criar novos cursos
- ✅ Editar cursos existentes
- ✅ Excluir cursos
- ✅ Visualizar alunos matriculados

### Gerenciamento de Alunos
- ✅ Listar alunos com paginação
- ✅ Buscar alunos por nome
- ✅ Criar novos alunos
- ✅ Editar alunos existentes
- ✅ Excluir alunos
- ✅ Validação de dados (nome, sexo, data de nascimento)
- ✅ Visualizar cursos matriculados

### Gerenciamento de Matrículas (Relacionamento N:N)
- ✅ Matricular aluno em curso
- ✅ Remover matrícula de aluno em curso
- ✅ Listar todas as matrículas do sistema
- ✅ Listar matrículas por aluno específico
- ✅ Listar matrículas por curso específico
- ✅ Interface gráfica para gerenciar matrículas
- ✅ Validação de relacionamentos (evita duplicatas)

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java 17+**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **PostgreSQL**
- **MapStruct** (mapeamento DTO/Entity)
- **Lombok** (redução de boilerplate)
- **Swagger/OpenAPI** (documentação da API)
- **Maven** (gerenciamento de dependências)

### Frontend
- **Angular 20+**
- **TypeScript**
- **Angular Material**
- **Bootstrap 5**
- **RxJS**

### Banco de Dados
- **PostgreSQL 16+**

## 🚀 Como Executar

### Pré-requisitos

> **🐧 Ambiente Testado**: Este projeto foi desenvolvido e testado em **Fedora 40** 
> (estava tudo funcionando perfeitamente na hora de escrever estas instruções! 🎯)

**Versões utilizadas:**
- Java 17+ (OpenJDK 17.0.12)
- Node.js 18+ (18.20.4)
- PostgreSQL 16+ (16.4)
- Git 2.45+

**Verificar se já estão instalados:**
```bash
java -version && node -v && psql --version && git --version
```

### 1. Configuração do Banco de Dados

#### Instalar PostgreSQL no Fedora
```bash
# Instalar PostgreSQL (no Fedora que está funcionando aqui!)
sudo dnf install postgresql postgresql-server postgresql-contrib

# Inicializar banco de dados (necessário apenas na primeira vez)
sudo postgresql-setup --initdb

# Habilitar e iniciar o serviço
sudo systemctl enable --now postgresql

# Verificar se está rodando
sudo systemctl status postgresql

# Configurar senha do usuário postgres
sudo -u postgres psql -c "ALTER USER postgres PASSWORD 'aluno';"

# Criar banco de dados
sudo -u postgres createdb jpa

# Executar script DDL/DML
sudo -u postgres psql -d jpa -f "Banco JPA (DDL e DML)"
```

### 2. Executar o Backend

```bash
cd backend-academico2025-completo-main
./mvnw spring-boot:run
```

O backend estará disponível em: `http://localhost:8080/academico`

### 3. Executar o Frontend

```bash
cd app-academico20205-completo-main
npm install
npm start
```

O frontend estará disponível em: `http://localhost:4200`

## 📊 Estrutura do Banco de Dados

```sql
-- Tabela de Alunos
CREATE TABLE aluno (
    idaluno SERIAL PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    sexo VARCHAR(30) NOT NULL,
    dt_nasc DATE NOT NULL
);

-- Tabela de Cursos
CREATE TABLE curso (
    idcurso SERIAL PRIMARY KEY,
    nomecurso VARCHAR(100) NOT NULL
);

-- Tabela de Relacionamento N:N (Matrículas)
CREATE TABLE aluno_curso (
    idcurso INTEGER REFERENCES curso(idcurso) ON DELETE CASCADE,
    idaluno INTEGER REFERENCES aluno(idaluno) ON DELETE CASCADE,
    PRIMARY KEY (idcurso, idaluno)
);
```

### Relacionamento Many-to-Many
- **Um aluno** pode estar matriculado em **vários cursos**
- **Um curso** pode ter **vários alunos** matriculados
- A tabela `aluno_curso` representa as **matrículas** no sistema
- Chave primária composta evita matrículas duplicadas
- Exclusão em cascata mantém integridade referencial

## 🌐 API Endpoints

### Alunos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/academico/alunos` | Lista todos os alunos |
| GET | `/academico/alunos/{id}` | Busca aluno por ID |
| GET | `/academico/alunos?search=nome` | Busca alunos por nome |
| POST | `/academico/alunos` | Cria novo aluno |
| PUT | `/academico/alunos/{id}` | Atualiza aluno |
| DELETE | `/academico/alunos/{id}` | Exclui aluno |

### Cursos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/academico/cursos` | Lista todos os cursos |
| GET | `/academico/cursos/{id}` | Busca curso por ID |
| GET | `/academico/cursos?search=nome` | Busca cursos por nome |
| POST | `/academico/cursos` | Cria novo curso |
| PUT | `/academico/cursos/{id}` | Atualiza curso |
| DELETE | `/academico/cursos/{id}` | Exclui curso |

### Matrículas (Relacionamento N:N)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/academico/matriculas` | Lista todas as matrículas |
| GET | `/academico/matriculas/aluno/{id}` | Lista matrículas do aluno |
| GET | `/academico/matriculas/curso/{id}` | Lista matrículas do curso |
| POST | `/academico/matriculas` | Matricula aluno em curso |
| DELETE | `/academico/matriculas/aluno/{idAluno}/curso/{idCurso}` | Remove matrícula |

## 📁 Estrutura do Projeto

```
sistema-academico/
├── backend-academico2025-completo-main/     # Backend Spring Boot
│   ├── src/main/java/br/edu/ifgoias/academico/
│   │   ├── dto/                             # Data Transfer Objects
│   │   │   ├── AlunoDTO.java               # DTO principal com relacionamentos
│   │   │   ├── AlunoSimpleDTO.java         # DTO simples (sem relacionamentos)
│   │   │   ├── CursoDTO.java               # DTO principal com relacionamentos
│   │   │   ├── CursoSimpleDTO.java         # DTO simples (sem relacionamentos)
│   │   │   ├── MatriculaDTO.java           # DTO para matrículas
│   │   │   └── PageDTO.java                # DTO para paginação
│   │   ├── entities/                        # Entidades JPA
│   │   │   ├── Aluno.java                  # Entidade com @ManyToMany
│   │   │   └── Curso.java                  # Entidade com @ManyToMany
│   │   ├── repositories/                    # Repositórios Spring Data
│   │   │   ├── AlunoRepository.java
│   │   │   └── CursoRepository.java
│   │   ├── services/                        # Lógica de negócio
│   │   │   ├── AlunoService.java
│   │   │   ├── CursoService.java
│   │   │   └── MatriculaService.java       # Gerencia relacionamentos
│   │   └── resources/                       # Controllers REST
│   │       ├── AlunoResource.java
│   │       ├── CursoResource.java
│   │       └── MatriculaResource.java      # API para matrículas
│   └── src/main/resources/
│       └── application.properties           # Configurações
├── app-academico20205-completo-main/        # Frontend Angular
│   ├── src/app/
│   │   ├── models/                          # Interfaces TypeScript
│   │   │   ├── aluno.model.ts              # Model com relacionamentos
│   │   │   ├── curso.model.ts              # Model com relacionamentos
│   │   │   └── matricula.model.ts          # Model para matrículas
│   │   ├── services/                        # Serviços HTTP
│   │   │   ├── aluno.service.ts
│   │   │   ├── curso.service.ts
│   │   │   └── matricula.service.ts        # Serviço para matrículas
│   │   ├── aluno/                           # Componentes de Aluno
│   │   ├── curso/                           # Componentes de Curso
│   │   ├── matriculas/                      # Componentes de Matrícula
│   │   │   ├── matriculas.component.*       # Gerenciar matrículas
│   │   │   └── matriculas-geral.component.* # Listagem geral
│   │   └── shared/                          # Componentes compartilhados
│   └── src/assets/                          # Recursos estáticos
└── Banco JPA (DDL e DML)                    # Script do banco de dados
```

## 🧪 Testes da API

### Exemplo: Criar Aluno
```bash
curl -X POST "http://localhost:8080/academico/alunos" \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "sexo": "Masculino",
    "dt_nasc": "1995-05-15"
  }'
```

### Exemplo: Listar Alunos
```bash
curl "http://localhost:8080/academico/alunos?page=0&size=10&search=João"
```

### Exemplo: Matricular Aluno em Curso
```bash
curl -X POST "http://localhost:8080/academico/matriculas" \
  -H "Content-Type: application/json" \
  -d '{
    "idAluno": 1,
    "idCurso": 2
  }'
```

### Exemplo: Listar Matrículas de um Aluno
```bash
curl "http://localhost:8080/academico/matriculas/aluno/1"
```

### Exemplo: Remover Matrícula
```bash
curl -X DELETE "http://localhost:8080/academico/matriculas/aluno/1/curso/2"
```

## 📖 Documentação da API

Após iniciar o backend, acesse:
- **Swagger UI**: `http://localhost:8080/academico/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/academico/v3/api-docs`

## 🔧 Configurações

### Backend (application.properties)
```properties
# Configuração do banco
spring.datasource.url=jdbc:postgresql://localhost:5432/jpa
spring.datasource.username=postgres
spring.datasource.password=aluno

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Contexto da aplicação
server.servlet.context-path=/academico
```

### Frontend (Angular)
- Base URL da API: `http://localhost:8080/academico`
- CORS habilitado para `http://localhost:4200`

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 🙏 Agradecimentos

- Prof. Dory pelo projeto base
- Documentação oficial das tecnologias utilizadas
