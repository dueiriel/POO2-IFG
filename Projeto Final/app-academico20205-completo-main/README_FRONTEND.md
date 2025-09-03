# Frontend - Sistema Acadêmico

Interface web desenvolvida com Angular para gerenciamento de alunos e cursos.

## 🎨 Tecnologias

- **Angular 20+** - Framework principal
- **TypeScript** - Linguagem de programação
- **Bootstrap 5** - Framework CSS
- **RxJS** - Programação reativa
- **Angular Router** - Navegação
- **Reactive Forms** - Formulários

## 🧪 Como Executar

### Pré-requisitos
- Node.js 18+
- npm

### Desenvolvimento
```bash
npm install
npm start
# Acesse http://localhost:4200
```

### Build para Produção
```bash
npm run build
# Arquivos gerados em dist/
```

## 📱 Funcionalidades Implementadas

### 🎓 Gerenciamento de Alunos
- ✅ **Lista Paginada** - Visualizar todos os alunos
- ✅ **Busca por Nome** - Filtrar alunos
- ✅ **Cadastro** - Criar novos alunos
- ✅ **Edição** - Modificar dados existentes
- ✅ **Exclusão** - Remover com confirmação
- ✅ **Validação** - Campos obrigatórios e formatos

### 📚 Gerenciamento de Cursos
- ✅ **Lista Paginada** - Visualizar todos os cursos
- ✅ **Busca por Nome** - Filtrar cursos
- ✅ **Cadastro** - Criar novos cursos
- ✅ **Edição** - Modificar dados existentes
- ✅ **Exclusão** - Remover com confirmação

## 🏗️ Estrutura dos Componentes

```
src/app/
├── models/
│   ├── aluno.model.ts           # Interface do Aluno
│   ├── curso.model.ts           # Interface do Curso
│   └── page.model.ts            # Interface de Paginação
├── services/
│   ├── aluno.service.ts         # HTTP Service para Alunos
│   └── curso.service.ts         # HTTP Service para Cursos
├── aluno/
│   ├── aluno.component.*        # Lista de Alunos
├── aluno-novo/
│   ├── aluno-novo.component.*   # Cadastro de Aluno
├── aluno-editar/
│   └── aluno-editar.component.* # Edição de Aluno
└── curso/                       # Componentes de Curso
    └── ...
```

## 🛣️ Rotas Configuradas

| Rota | Componente | Descrição |
|------|------------|-----------|
| `/home` | HomeComponent | Página inicial |
| `/aluno` | AlunoComponent | Lista de alunos |
| `/aluno-novo` | AlunoNovoComponent | Cadastro de aluno |
| `/aluno-editar/:id` | AlunoEditarComponent | Edição de aluno |
| `/curso` | CursoComponent | Lista de cursos |
| `/curso-novo` | CursoNovoComponent | Cadastro de curso |
| `/curso-editar/:id` | CursoEditarComponent | Edição de curso |

## 🔗 Integração com Backend

### Configuração da API
```typescript
// services/aluno.service.ts
private baseUrl = 'http://localhost:8080/academico/alunos';
```

### Endpoints Utilizados
- `GET /academico/alunos` - Lista alunos
- `GET /academico/alunos/{id}` - Busca aluno
- `POST /academico/alunos` - Cria aluno
- `PUT /academico/alunos/{id}` - Atualiza aluno
- `DELETE /academico/alunos/{id}` - Exclui aluno

## 📋 Formulários e Validação

### Campos do Aluno
- **Nome** (obrigatório, máx. 80 caracteres)
- **Sexo** (obrigatório, dropdown)
- **Data de Nascimento** (obrigatório, date picker)

### Validações Client-Side
```typescript
// Exemplo de validação
isFormValid(): boolean {
  return this.aluno.nome.trim() !== '' && 
         this.aluno.sexo.trim() !== '' && 
         this.aluno.dt_nasc !== '';
}
```

## 🎨 Interface e UX

### Layout Responsivo
- **Bootstrap Grid** - Layout responsivo
- **Cards** - Organização de conteúdo
- **Tabelas** - Listagem de dados
- **Formulários** - Entrada de dados

### Componentes UI
- **Paginação** - NgBootstrap Pagination
- **Modal de Confirmação** - Angular Material Dialog
- **Notificações** - Alertas de sucesso/erro
- **Loading States** - Indicadores de carregamento

## 🧪 Testando a Interface

### Fluxo de Teste - Aluno
1. **Acessar** `/aluno`
2. **Visualizar** lista de alunos
3. **Buscar** por nome específico
4. **Clicar** em "Novo Aluno"
5. **Preencher** formulário de cadastro
6. **Salvar** e verificar retorno à lista
7. **Editar** aluno existente
8. **Excluir** com confirmação

### Validações
- ✅ Campos obrigatórios bloqueiam envio
- ✅ Mensagens de erro aparecem
- ✅ Navegação funciona corretamente
- ✅ Dados são persistidos no backend

## 🚀 Deploy

### Build de Produção
```bash
npm run build --configuration production
```

### Configuração de Ambiente
```typescript
// src/environments/environment.prod.ts
export const environment = {
  production: true,
  apiUrl: 'https://api.exemplo.com/academico'
};
```

### Servir Arquivos
```bash
# Com servidor simples
npx http-server dist/academico

# Com nginx
cp -r dist/academico/* /var/www/html/
```

## 📄 Licença

Este projeto foi desenvolvido como parte do curso de Programação Orientada a Objetos 2.

---

**Projeto gerado com [Angular CLI](https://github.com/angular/angular-cli) versão 20.2.1**
