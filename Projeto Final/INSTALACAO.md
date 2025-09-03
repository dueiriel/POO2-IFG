# 🚀 Guia de Instalação - Sistema Acadêmico

Instruções completas para configurar e executar o sistema completo em **Fedora Linux**.

> **🎯 Testado e Funcionando**: Este guia foi criado em **Fedora 40** e estava tudo rodando perfeitamente na hora de escrever estas instruções! Se você está usando Fedora também, vai ser moleza! 😄

## 📋 Pré-requisitos

### Software que Você Vai Precisar
- **Java 17+** - Para o backend Spring Boot
- **Node.js 18+** - Para o frontend Angular
- **PostgreSQL 16+** - Banco de dados
- **Git** - Para baixar o código (se vier do GitHub)

### Verificar se Já Tem Tudo Instalado
```bash
# Comando mágico para verificar tudo de uma vez
java -version && node -v && npm -v && psql --version && git --version

# Se algum comando der erro, é só instalar na seção abaixo 👇
```

## 🐧 Instalação no Fedora (Modo Raiz!)

### Instalar Tudo de Uma Vez no Fedora
```bash
# O comando dos comandos para instalar tudo no Fedora:
sudo dnf install java-17-openjdk java-17-openjdk-devel nodejs npm postgresql postgresql-server postgresql-contrib git

# Pronto! Fedora é vida! 🎉
```

### Ou Instalar Separadamente (Se Preferir)

#### Java 17
```bash
sudo dnf install java-17-openjdk java-17-openjdk-devel
```

#### Node.js e npm
```bash
sudo dnf install nodejs npm
```

#### PostgreSQL
```bash
sudo dnf install postgresql postgresql-server postgresql-contrib
```

## 🗄️ Configuração do PostgreSQL (Fedora Style!)

### 1. Inicializar o PostgreSQL (Só Precisa Fazer Uma Vez)

```bash
# Inicializar o banco de dados (obrigatório no Fedora)
sudo postgresql-setup --initdb

# Habilitar para iniciar automaticamente
sudo systemctl enable postgresql

# Iniciar o serviço agora
sudo systemctl start postgresql

# Verificar se está rodando (deve mostrar "active (running)")
sudo systemctl status postgresql
```

### 2. Configurar Autenticação (Para Não Dar Dor de Cabeça)

```bash
# Editar arquivo de configuração do PostgreSQL
sudo nano /var/lib/pgsql/data/pg_hba.conf

# Procurar essas linhas e mudar de 'ident' ou 'peer' para 'md5':
# local   all             all                                     md5
# host    all             all             127.0.0.1/32            md5
# host    all             all             ::1/128                 md5

# Exemplo do que deve ficar:
echo '
# TYPE  DATABASE        USER            ADDRESS                 METHOD
local   all             all                                     md5
host    all             all             127.0.0.1/32            md5
host    all             all             ::1/128                 md5
' | sudo tee -a /var/lib/pgsql/data/pg_hba.conf

# Reiniciar PostgreSQL para aplicar mudanças
sudo systemctl restart postgresql
```

### 3. Configurar Senha e Criar Banco

```bash
# Configurar senha do usuário postgres
sudo -u postgres psql -c "ALTER USER postgres PASSWORD 'aluno';"

# Criar banco de dados para o projeto
sudo -u postgres createdb jpa

# Testar se está funcionando
PGPASSWORD=aluno psql -h 127.0.0.1 -U postgres -d jpa -c "SELECT version();"
```

### 4. Executar Script de Criação das Tabelas

```bash
# Se você tem o arquivo DDL/DML do projeto:
PGPASSWORD=aluno psql -h 127.0.0.1 -U postgres -d jpa -f "Banco JPA (DDL e DML)"

# Ou criar as tabelas manualmente:
PGPASSWORD=aluno psql -h 127.0.0.1 -U postgres -d jpa << 'EOF'
-- Criar tabela de alunos
CREATE TABLE IF NOT EXISTS aluno (
    idaluno SERIAL PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    sexo VARCHAR(30) NOT NULL,
    dt_nasc DATE NOT NULL
);

-- Criar tabela de cursos  
CREATE TABLE IF NOT EXISTS curso (
    idcurso SERIAL PRIMARY KEY,
    nomecurso VARCHAR(100) NOT NULL
);

-- Criar tabela de relacionamento
CREATE TABLE IF NOT EXISTS aluno_curso (
    idcurso INTEGER REFERENCES curso(idcurso),
    idaluno INTEGER REFERENCES aluno(idaluno)
);

-- Inserir alguns dados de teste
INSERT INTO aluno (nome, sexo, dt_nasc) VALUES 
('Maria Silva', 'Feminino', '2000-01-15'),
('João Santos', 'Masculino', '1999-05-20'),
('Ana Costa', 'Feminino', '2001-03-10');

INSERT INTO curso (nomecurso) VALUES 
('Engenharia de Software'),
('Ciência da Computação'),
('Sistemas de Informação');
EOF
```

### 5. Verificar se Deu Tudo Certo

```bash
# Listar alunos
PGPASSWORD=aluno psql -h 127.0.0.1 -U postgres -d jpa -c "SELECT * FROM aluno;"

# Listar cursos
PGPASSWORD=aluno psql -h 127.0.0.1 -U postgres -d jpa -c "SELECT * FROM curso;"

# Se aparecer os dados, está funcionando! 🎉
```

## 🔧 Configuração do Backend

### 1. Navegar para o diretório
```bash
cd backend-academico2025-completo-main
```

### 2. Compilar o projeto
```bash
# Dar permissão ao Maven Wrapper
chmod +x mvnw

# Compilar
./mvnw clean compile
```

### 3. Executar o backend
```bash
# Executar em primeiro plano
./mvnw spring-boot:run

# Ou executar em background
nohup ./mvnw spring-boot:run > app.log 2>&1 &
```

### 4. Verificar se está funcionando
```bash
# Testar endpoint de alunos
curl "http://localhost:8080/academico/alunos"

# Testar endpoint de cursos
curl "http://localhost:8080/academico/cursos"
```

## 🌐 Configuração do Frontend

### 1. Navegar para o diretório
```bash
cd app-academico20205-completo-main
```

### 2. Instalar dependências
```bash
npm install
```

### 3. Executar o frontend
```bash
# Executar em modo de desenvolvimento
npm start

# Ou usar Angular CLI diretamente
npx ng serve
```

### 4. Acessar a aplicação
Abrir navegador em: `http://localhost:4200`

## ✅ Verificação Final

### 1. Testar Backend
- ✅ `http://localhost:8080/academico/alunos` retorna lista de alunos
- ✅ `http://localhost:8080/academico/cursos` retorna lista de cursos
- ✅ `http://localhost:8080/academico/swagger-ui.html` mostra documentação

### 2. Testar Frontend
- ✅ `http://localhost:4200` carrega página inicial
- ✅ Menu "Aluno" funciona
- ✅ Menu "Curso" funciona
- ✅ Formulários de cadastro funcionam

### 3. Testar Integração
- ✅ Criar novo aluno via interface
- ✅ Editar aluno existente
- ✅ Excluir aluno com confirmação
- ✅ Buscar alunos por nome

## 🐛 Solução de Problemas

### Backend não inicia
```bash
# Verificar se PostgreSQL está rodando
sudo systemctl status postgresql

# Verificar logs do backend
tail -f app.log

# Verificar se porta 8080 está ocupada
netstat -tlnp | grep 8080
```

### Frontend não carrega
```bash
# Verificar se Node.js está atualizado
node -v  # Deve ser 18+

# Limpar cache e reinstalar
rm -rf node_modules package-lock.json
npm install

# Verificar se porta 4200 está livre
netstat -tlnp | grep 4200
```

### Erro de CORS
- ✅ Verificar se backend tem `@CrossOrigin(origins = "http://localhost:4200")`
- ✅ Verificar se frontend usa URL correta: `http://localhost:8080/academico`

### Erro de conexão com banco
```bash
# Testar conexão manual
PGPASSWORD=aluno psql -h 127.0.0.1 -U postgres -d jpa

# Verificar configurações em application.properties
cat backend-academico2025-completo-main/src/main/resources/application.properties
```

## 📦 Scripts Úteis

### Iniciar tudo de uma vez
```bash
#!/bin/bash
# start-all.sh

# Iniciar PostgreSQL
sudo systemctl start postgresql

# Iniciar Backend (background)
cd backend-academico2025-completo-main
nohup ./mvnw spring-boot:run > app.log 2>&1 &
cd ..

# Aguardar backend inicializar
sleep 15

# Iniciar Frontend
cd app-academico20205-completo-main
npm start
```

### Parar tudo
```bash
#!/bin/bash
# stop-all.sh

# Parar frontend (Ctrl+C se em primeiro plano)
pkill -f "ng serve"

# Parar backend
pkill -f "spring-boot:run"

# Parar PostgreSQL (opcional)
# sudo systemctl stop postgresql
```

## 🔧 Configurações Opcionais

### Alterar porta do backend
```properties
# application.properties
server.port=8081
```

### Alterar porta do frontend
```bash
ng serve --port 4201
```

### Configurar banco externo
```properties
# application.properties
spring.datasource.url=jdbc:postgresql://seu-servidor:5432/jpa
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
```

## 📚 Próximos Passos

1. **Explorar a API** - Usar Swagger UI em `http://localhost:8080/academico/swagger-ui.html`
2. **Personalizar Frontend** - Modificar estilos e layout
3. **Adicionar Funcionalidades** - Implementar relacionamento Aluno-Curso
4. **Deploy** - Configurar para produção
5. **Testes** - Adicionar testes unitários e de integração

## 📞 Suporte

Se encontrar problemas durante a instalação:
1. Verificar logs de erro
2. Consultar documentação oficial das tecnologias
3. Verificar issues no repositório
4. Contactar o desenvolvedor

---

**Sistema desenvolvido para POO2 - Instituto Federal de Goiás**
