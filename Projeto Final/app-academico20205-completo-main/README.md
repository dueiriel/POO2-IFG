# Frontend - Sistema Acadêmico

Interface desenvolvida com Angular 20 para gerenciamento de alunos, cursos e matrículas.

## 🚀 Tecnologias Utilizadas

- **Angular 20+** - Framework principal
- **TypeScript 5+** - Linguagem de programação
- **Bootstrap 5** - Framework CSS
- **Angular Material** - Componentes UI
- **RxJS** - Programação reativa
- **Angular Standalone Components** - Arquitetura moderna

## 📁 Estrutura do Projeto

```
src/
├── app/
│   ├── models/                          # Interfaces TypeScript
│   │   ├── aluno.model.ts              # Interface Aluno com relacionamentos
│   │   ├── curso.model.ts              # Interface Curso com relacionamentos
│   │   ├── matricula.model.ts          # Interface Matrícula
│   │   └── page.model.ts               # Interface para paginação
│   ├── services/                        # Serviços HTTP
│   │   ├── aluno.service.ts            # Serviço para operações de Aluno
│   │   ├── curso.service.ts            # Serviço para operações de Curso
│   │   └── matricula.service.ts        # Serviço para operações de Matrícula
│   ├── home/                           # Componente página inicial
│   │   ├── home.component.ts
│   │   ├── home.component.html
│   │   └── home.component.css
│   ├── aluno/                          # Componentes de Aluno
│   │   ├── aluno.component.ts          # Listagem de alunos
│   │   ├── aluno.component.html
│   │   └── aluno.component.css
│   ├── aluno-novo/                     # Cadastro de novo aluno
│   │   ├── aluno-novo.component.ts
│   │   ├── aluno-novo.component.html
│   │   └── aluno-novo.component.css
│   ├── aluno-editar/                   # Edição de aluno
│   │   ├── aluno-editar.component.ts
│   │   ├── aluno-editar.component.html
│   │   └── aluno-editar.component.css
│   ├── curso/                          # Componentes de Curso
│   │   ├── curso.component.ts          # Listagem de cursos
│   │   ├── curso.component.html
│   │   └── curso.component.css
│   ├── curso-novo/                     # Cadastro de novo curso
│   │   ├── curso-novo.component.ts
│   │   ├── curso-novo.component.html
│   │   └── curso-novo.component.css
│   ├── curso-editar/                   # Edição de curso
│   │   ├── curso-editar.component.ts
│   │   ├── curso-editar.component.html
│   │   └── curso-editar.component.css
│   ├── matriculas/                     # Componentes de Matrícula
│   │   ├── matriculas.component.ts     # Gerenciar matrículas
│   │   ├── matriculas.component.html
│   │   └── matriculas.component.css
│   ├── matriculas-geral/               # Listagem geral de matrículas
│   │   ├── matriculas-geral.component.ts
│   │   ├── matriculas-geral.component.html
│   │   └── matriculas-geral.component.css
│   ├── confirm-dialog/                 # Diálogo de confirmação
│   │   ├── confirm-dialog.component.ts
│   │   ├── confirm-dialog.component.html
│   │   └── confirm-dialog.component.css
│   ├── app.component.ts                # Componente raiz
│   ├── app.component.html              # Template principal
│   ├── app.component.css               # Estilos globais
│   ├── app.config.ts                   # Configurações da aplicação
│   └── app.routes.ts                   # Configuração de rotas
├── styles.css                          # Estilos globais
├── main.ts                             # Ponto de entrada
└── index.html                          # HTML principal
```

## 🔗 Modelos (Interfaces)

### Aluno
```typescript
export interface Aluno {
  idaluno?: number;
  nome: string;
  sexo: string;
  dt_nasc: string;
  cursos?: Curso[];  // Relacionamento Many-to-Many
}
```

### Curso
```typescript
export interface Curso {
  idCurso?: number;
  nomeCurso: string;
  alunos?: Aluno[];  // Relacionamento Many-to-Many
}
```

### Matrícula
```typescript
export interface Matricula {
  idAluno: number;
  idCurso: number;
  nomeAluno?: string;
  nomeCurso?: string;
}
```

## 🛠️ Serviços HTTP

### AlunoService
```typescript
@Injectable({
  providedIn: 'root'
})
export class AlunoService {
  private apiUrl = 'http://localhost:8080/academico/alunos';

  // Listar com paginação e busca
  getAlunos(page: number, size: number, search?: string): Observable<Page<Aluno>>

  // Buscar por ID
  getAlunoById(id: number): Observable<Aluno>

  // Criar aluno
  createAluno(aluno: Aluno): Observable<Aluno>

  // Atualizar aluno
  updateAluno(id: number, aluno: Aluno): Observable<Aluno>

  // Excluir aluno
  deleteAluno(id: number): Observable<void>
}
```

### MatriculaService
```typescript
@Injectable({
  providedIn: 'root'
})
export class MatriculaService {
  private apiUrl = 'http://localhost:8080/academico/matriculas';

  // Listar todas as matrículas
  getMatriculas(): Observable<Matricula[]>

  // Listar matrículas por aluno
  getMatriculasByAluno(idAluno: number): Observable<Matricula[]>

  // Listar matrículas por curso
  getMatriculasByCurso(idCurso: number): Observable<Matricula[]>

  // Matricular aluno em curso
  matricularAluno(matricula: Matricula): Observable<Matricula>

  // Remover matrícula
  removerMatricula(idAluno: number, idCurso: number): Observable<void>
}
```

## 🧭 Rotas da Aplicação

```typescript
export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'alunos', component: AlunoComponent },
  { path: 'alunos/novo', component: AlunoNovoComponent },
  { path: 'alunos/editar/:id', component: AlunoEditarComponent },
  { path: 'cursos', component: CursoComponent },
  { path: 'cursos/novo', component: CursoNovoComponent },
  { path: 'cursos/editar/:id', component: CursoEditarComponent },
  { path: 'matriculas', component: MatriculasComponent },
  { path: 'matriculas-geral', component: MatriculasGeralComponent },
  { path: '**', redirectTo: '' }
];
```

## 🎨 Funcionalidades da Interface

### Listagem de Alunos
- ✅ Tabela responsiva com Bootstrap
- ✅ Paginação server-side
- ✅ Busca por nome em tempo real
- ✅ Ações: visualizar, editar, excluir
- ✅ Exibição dos cursos matriculados
- ✅ Confirmação antes de excluir

### Cadastro/Edição de Alunos
- ✅ Formulário reativo com validações
- ✅ Campos obrigatórios destacados
- ✅ Validação de data de nascimento
- ✅ Máscaras de entrada
- ✅ Mensagens de erro contextuais

### Gerenciamento de Matrículas
- ✅ Interface para matricular aluno em curso
- ✅ Interface para remover matrículas
- ✅ Listagem geral de todas as matrículas
- ✅ Filtros por aluno e curso
- ✅ Evita matrículas duplicadas
- ✅ Feedback visual das operações

### Navegação
- ✅ Menu responsivo com Bootstrap
- ✅ Breadcrumbs para navegação
- ✅ Links ativos destacados
- ✅ Ícones intuitivos
- ✅ Design consistente

## 📱 Responsividade

O sistema é totalmente responsivo usando Bootstrap 5:

- **Desktop**: Layout completo com sidebar
- **Tablet**: Menu colapsável, tabelas responsivas
- **Mobile**: Design mobile-first, navegação otimizada

## 🔧 Configurações

### Environment
```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/academico'
};
```

### Interceptors HTTP
- Tratamento global de erros
- Loading states automáticos
- Headers padrão (Content-Type)

## 🎯 Validações

### Formulário de Aluno
- **Nome**: Obrigatório, máximo 80 caracteres
- **Sexo**: Obrigatório, máximo 30 caracteres
- **Data de Nascimento**: Obrigatória, formato válido

### Formulário de Curso
- **Nome do Curso**: Obrigatório, máximo 100 caracteres

### Matrícula
- **Aluno**: Seleção obrigatória
- **Curso**: Seleção obrigatória
- **Duplicatas**: Validação no backend

## 🚀 Executando o Projeto

### Instalação
```bash
npm install
```

### Desenvolvimento
```bash
npm start
# ou
ng serve
```
Aplicação disponível em: `http://localhost:4200`

### Build de Produção
```bash
npm run build
# ou
ng build --configuration production
```

### Testes
```bash
# Testes unitários
npm test

# Testes end-to-end
npm run e2e
```

## 📦 Dependências Principais

```json
{
  "dependencies": {
    "@angular/core": "^20.0.0",
    "@angular/common": "^20.0.0",
    "@angular/forms": "^20.0.0",
    "@angular/router": "^20.0.0",
    "@angular/animations": "^20.0.0",
    "@angular/material": "^20.0.0",
    "bootstrap": "^5.3.0",
    "rxjs": "^7.8.0",
    "typescript": "^5.0.0"
  }
}
```

## 🛡️ Tratamento de Erros

- **Formulários**: Validações em tempo real
- **HTTP Requests**: Interceptors globais
- **Feedback Visual**: Toasts e modais
- **Fallbacks**: Estados de loading e erro
- **Logs**: Console detalhado em desenvolvimento

## 📖 Padrões de Código

- **Standalone Components**: Arquitetura moderna do Angular
- **Reactive Forms**: Para formulários complexos
- **Observables**: Para programação reativa
- **TypeScript Strict**: Tipagem rigorosa
- **Component Communication**: Input/Output e Services

## 🎨 Design System

- **Bootstrap 5**: Framework CSS base
- **Angular Material**: Componentes avançados
- **Cores**: Paleta consistente
- **Tipografia**: Hierarquia clara
- **Ícones**: Font Awesome integrado
- **Layout**: Grid system responsivo

## 📱 UX/UI Features

- **Loading States**: Indicadores visuais
- **Empty States**: Mensagens quando não há dados
- **Error States**: Feedback claro de erros
- **Success States**: Confirmações de ações
- **Navigation**: Breadcrumbs e menu ativo
- **Accessibility**: ARIA labels e navegação por teclado

## 🔮 Próximos Passos

- [ ] Implementar lazy loading de módulos
- [ ] Adicionar testes unitários abrangentes
- [ ] Implementar PWA (Progressive Web App)
- [ ] Adicionar internacionalização (i18n)
- [ ] Implementar dark mode
- [ ] Adicionar relatórios e gráficos
