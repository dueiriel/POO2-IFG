# 📄 Licença e Contribuição

## 📜 Licença

Este projeto é desenvolvido para fins acadêmicos como parte da disciplina **Programação Orientada a Objetos II (POO2)** do **Instituto Federal de Goiás (IFG)**.

### Uso Acadêmico
- ✅ **Permitido**: Uso para aprendizado e referência
- ✅ **Permitido**: Fork para projetos pessoais de estudo
- ✅ **Permitido**: Adaptação para outros trabalhos acadêmicos (com devida citação)
- ❌ **Não permitido**: Submissão como trabalho próprio sem modificações
- ❌ **Não permitido**: Uso comercial sem autorização

### Citação Sugerida
```
Sistema Acadêmico Full-Stack
Desenvolvido por [Seu Nome]
Disciplina: POO2 - Instituto Federal de Goiás
Ano: 2025
Repositório: [URL do seu repositório]
```

## 🤝 Como Contribuir

### Para Colegas de Curso
1. **Fork** o repositório
2. **Clone** seu fork localmente
3. **Crie** uma branch para sua feature: `git checkout -b feature/nova-funcionalidade`
4. **Implemente** suas modificações
5. **Teste** suas alterações
6. **Commit** suas mudanças: `git commit -m "Adiciona nova funcionalidade"`
7. **Push** para sua branch: `git push origin feature/nova-funcionalidade`
8. **Abra** um Pull Request explicando suas mudanças

### Tipos de Contribuição Bem-vindas

#### 🐛 Correções de Bug
- Corrigir problemas identificados
- Melhorar tratamento de erros
- Otimizar performance

#### ✨ Novas Funcionalidades
- Implementar relacionamento Aluno-Curso completo
- Adicionar sistema de autenticação
- Criar dashboard com estatísticas
- Implementar upload de fotos de alunos
- Adicionar validações mais robustas

#### 📚 Melhorias na Documentação
- Corrigir ou melhorar README
- Adicionar comentários no código
- Criar tutoriais adicionais
- Traduzir documentação

#### 🎨 Melhorias de Interface
- Implementar responsividade mobile
- Melhorar UX/UI
- Adicionar temas personalizáveis
- Implementar componentes reutilizáveis

#### 🧪 Testes
- Adicionar testes unitários
- Implementar testes de integração
- Criar testes end-to-end
- Configurar CI/CD

### Padrões de Código

#### Backend (Java/Spring Boot)
```java
// Usar CamelCase para métodos e variáveis
public List<AlunoDTO> listarAlunos() { }

// Usar PascalCase para classes
public class AlunoService { }

// Comentários em português para fins acadêmicos
/**
 * Método responsável por buscar aluno por ID
 * @param id Identificador único do aluno
 * @return DTO do aluno encontrado
 */
```

#### Frontend (TypeScript/Angular)
```typescript
// Usar camelCase para propriedades e métodos
export class AlunoComponent {
  alunos: Aluno[] = [];
  
  carregarAlunos(): void { }
}

// Usar kebab-case para arquivos e seletores
// aluno-novo.component.ts
// <app-aluno-novo></app-aluno-novo>
```

#### Banco de Dados
```sql
-- Usar snake_case para tabelas e colunas
CREATE TABLE aluno (
    id_aluno SERIAL PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    dt_nasc DATE
);
```

### Estrutura de Commit

Usar conventional commits:
```
tipo(escopo): descrição

feat(aluno): adiciona validação de CPF
fix(curso): corrige bug na listagem
docs(readme): atualiza instruções de instalação
style(frontend): ajusta layout responsivo
refactor(service): melhora estrutura do código
test(aluno): adiciona testes unitários
```

### Configuração do Ambiente de Desenvolvimento

1. **Instalar dependências de desenvolvimento**
```bash
# Backend - adicionar ao pom.xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
</dependency>

# Frontend - instalar ferramentas
npm install -g @angular/cli
npm install --save-dev prettier eslint
```

2. **Configurar hooks de commit**
```bash
# Instalar husky para hooks
npm install --save-dev husky
npx husky install

# Configurar pre-commit
npx husky add .husky/pre-commit "npm run lint"
```

### Processo de Review

Antes de submeter um PR, verifique:

#### ✅ Checklist Técnico
- [ ] Código compila sem erros
- [ ] Testes passam (se existirem)
- [ ] Funcionalidade foi testada manualmente
- [ ] Não quebra funcionalidades existentes
- [ ] Segue padrões de código estabelecidos

#### ✅ Checklist de Documentação
- [ ] README atualizado se necessário
- [ ] Comentários adicionados em código complexo
- [ ] Instruções de instalação verificadas
- [ ] Exemplos de uso documentados

#### ✅ Checklist de Qualidade
- [ ] Código é legível e bem estruturado
- [ ] Variáveis e métodos têm nomes descritivos
- [ ] Não há código duplicado
- [ ] Tratamento adequado de erros

## 🏆 Reconhecimentos

### Contribuidores
- **[Seu Nome]** - Desenvolvedor principal
- **Professor [Nome]** - Orientação acadêmica
- **Colegas de turma** - Feedback e sugestões

### Tecnologias Utilizadas
- **Spring Boot** - Framework backend
- **Angular** - Framework frontend
- **PostgreSQL** - Banco de dados
- **Bootstrap** - Framework CSS
- **MapStruct** - Mapeamento de DTOs

### Inspirações e Referências
- Documentação oficial do Spring Boot
- Angular Style Guide
- Boas práticas de desenvolvimento full-stack
- Padrões de arquitetura MVC

## 📞 Contato e Suporte

### Para Questões Acadêmicas
- **Professor**: [Email do professor]
- **Disciplina**: POO2 - Programação Orientada a Objetos II
- **Instituição**: Instituto Federal de Goiás

### Para Questões Técnicas
- **Issues**: Use o sistema de issues do GitHub
- **Discussões**: Use as discussions do repositório
- **Email**: [Seu email acadêmico]

### Canais de Comunicação
- 📧 **Email acadêmico**: Para questões formais
- 💬 **Issues do GitHub**: Para bugs e melhorias
- 🗣️ **Discussions**: Para dúvidas gerais
- 📚 **Wiki do projeto**: Para documentação detalhada

## 🎯 Roadmap do Projeto

### Próximas Versões

#### v2.0 - Melhorias de Interface
- [ ] Design responsivo completo
- [ ] Tema escuro/claro
- [ ] Componentes de loading
- [ ] Notificações toast

#### v3.0 - Funcionalidades Avançadas
- [ ] Sistema de autenticação
- [ ] Relacionamento Aluno-Curso funcional
- [ ] Relatórios e dashboards
- [ ] Upload de arquivos

#### v4.0 - Qualidade e Performance
- [ ] Testes automatizados
- [ ] Cache inteligente
- [ ] Paginação virtual
- [ ] PWA (Progressive Web App)

### Como Propor Novas Features
1. Abrir uma **issue** com label `enhancement`
2. Descrever detalhadamente a funcionalidade
3. Explicar o caso de uso acadêmico
4. Aguardar discussão da comunidade
5. Implementar após aprovação

---

**Desenvolvido com ❤️ para aprendizado em POO2 - IFG**
