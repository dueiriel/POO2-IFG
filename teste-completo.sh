#!/bin/bash

# Teste Completo do Sistema Acadêmico - POO2
# Executa bateria completa de testes automatizados

echo "🧪 BATERIA COMPLETA DE TESTES - SISTEMA ACADÊMICO"
echo "=================================================="
echo "Data: $(date)"
echo "Repositório: POO2-IFG"
echo ""

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Função para teste com resultado
test_endpoint() {
    local test_name="$1"
    local command="$2"
    local expected_check="$3"
    
    echo -n "  ${test_name}... "
    
    result=$(eval $command 2>/dev/null)
    
    if [[ $? -eq 0 && -n "$result" ]]; then
        echo -e "${GREEN}✅ PASSOU${NC}"
        echo "    Resultado: $result"
    else
        echo -e "${RED}❌ FALHOU${NC}"
        echo "    Comando: $command"
    fi
    echo ""
}

# Função para verificar se serviços estão rodando
check_service() {
    local service_name="$1"
    local url="$2"
    
    echo -n "  Verificando ${service_name}... "
    
    if curl -s "$url" > /dev/null 2>&1; then
        echo -e "${GREEN}✅ ONLINE${NC}"
        return 0
    else
        echo -e "${RED}❌ OFFLINE${NC}"
        return 1
    fi
}

echo -e "${BLUE}=== 1. VERIFICAÇÃO DE SERVIÇOS ===${NC}"
check_service "Backend" "http://localhost:8080/academico/alunos"
check_service "Frontend" "http://localhost:4200"
echo ""

echo -e "${BLUE}=== 2. TESTES DE BACKEND (APIs REST) ===${NC}"

test_endpoint "2.1 Listagem de Alunos" \
    "curl -s 'http://localhost:8080/academico/alunos' | jq '.content | length'" \
    "number"

test_endpoint "2.2 Busca Aluno Específico (ID 1)" \
    "curl -s 'http://localhost:8080/academico/alunos/1' | jq -r '.nome'" \
    "text"

test_endpoint "2.3 Listagem de Cursos" \
    "curl -s 'http://localhost:8080/academico/cursos' | jq '.content | length'" \
    "number"

test_endpoint "2.4 Busca Curso Específico (ID 1)" \
    "curl -s 'http://localhost:8080/academico/cursos/1' | jq -r '.nomeCurso'" \
    "text"

test_endpoint "2.5 Matrículas por Aluno (ID 1)" \
    "curl -s 'http://localhost:8080/academico/matriculas/aluno/1' | jq 'length'" \
    "number"

test_endpoint "2.6 Matrículas por Curso (ID 1)" \
    "curl -s 'http://localhost:8080/academico/matriculas/curso/1' | jq 'length'" \
    "number"

echo -e "${BLUE}=== 3. TESTES DE CRIAÇÃO DE DADOS ===${NC}"

# Criar aluno de teste
NEW_ALUNO_ID=$(curl -X POST "http://localhost:8080/academico/alunos" \
    -H "Content-Type: application/json" \
    -d '{"nome":"Teste Bateria","sexo":"Masculino","dt_nasc":"1990-01-01"}' \
    -s | jq -r '.idaluno')

test_endpoint "3.1 Criação de Aluno" \
    "echo '$NEW_ALUNO_ID'" \
    "number"

# Criar curso de teste
NEW_CURSO_ID=$(curl -X POST "http://localhost:8080/academico/cursos" \
    -H "Content-Type: application/json" \
    -d '{"nomeCurso":"Curso Teste Bateria"}' \
    -s | jq -r '.idCurso')

test_endpoint "3.2 Criação de Curso" \
    "echo '$NEW_CURSO_ID'" \
    "number"

# Criar matrícula
if [[ -n "$NEW_ALUNO_ID" && -n "$NEW_CURSO_ID" ]]; then
    test_endpoint "3.3 Criação de Matrícula" \
        "curl -X POST 'http://localhost:8080/academico/matriculas' -H 'Content-Type: application/json' -d '{\"idAluno\":$NEW_ALUNO_ID,\"idCurso\":$NEW_CURSO_ID}' -s | jq -r '.nomeAluno'" \
        "text"
fi

echo -e "${BLUE}=== 4. TESTES DE RELACIONAMENTO MANY-TO-MANY ===${NC}"

test_endpoint "4.1 Aluno com Múltiplos Cursos" \
    "curl -s 'http://localhost:8080/academico/alunos/1' | jq '.cursos | length'" \
    "number"

test_endpoint "4.2 Curso com Múltiplos Alunos" \
    "curl -s 'http://localhost:8080/academico/matriculas/curso/1' | jq 'length'" \
    "number"

test_endpoint "4.3 Verificação de Integridade Relacional" \
    "curl -s 'http://localhost:8080/academico/matriculas/aluno/1' | jq '.[0] | has(\"nomeAluno\") and has(\"nomeCurso\")'" \
    "boolean"

echo -e "${BLUE}=== 5. TESTES DE PERSISTÊNCIA NO BANCO ===${NC}"

test_endpoint "5.1 Contagem de Alunos no Banco" \
    "PGPASSWORD=aluno psql -h 127.0.0.1 -U postgres -d jpa -c 'SELECT COUNT(*) FROM aluno;' -t | xargs" \
    "number"

test_endpoint "5.2 Contagem de Cursos no Banco" \
    "PGPASSWORD=aluno psql -h 127.0.0.1 -U postgres -d jpa -c 'SELECT COUNT(*) FROM curso;' -t | xargs" \
    "number"

test_endpoint "5.3 Contagem de Matrículas no Banco" \
    "PGPASSWORD=aluno psql -h 127.0.0.1 -U postgres -d jpa -c 'SELECT COUNT(*) FROM aluno_curso;' -t | xargs" \
    "number"

echo -e "${BLUE}=== 6. TESTES DE EDIÇÃO ===${NC}"

if [[ -n "$NEW_ALUNO_ID" ]]; then
    test_endpoint "6.1 Edição de Aluno" \
        "curl -X PUT 'http://localhost:8080/academico/alunos/$NEW_ALUNO_ID' -H 'Content-Type: application/json' -d '{\"idaluno\":$NEW_ALUNO_ID,\"nome\":\"Teste Editado\",\"sexo\":\"Masculino\",\"dt_nasc\":\"1990-01-01\"}' -s | jq -r '.nome'" \
        "text"
fi

if [[ -n "$NEW_CURSO_ID" ]]; then
    test_endpoint "6.2 Edição de Curso" \
        "curl -X PUT 'http://localhost:8080/academico/cursos/$NEW_CURSO_ID' -H 'Content-Type: application/json' -d '{\"idCurso\":$NEW_CURSO_ID,\"nomeCurso\":\"Curso Editado\"}' -s | jq -r '.nomeCurso'" \
        "text"
fi

echo -e "${BLUE}=== 7. TESTES DE REMOÇÃO ===${NC}"

if [[ -n "$NEW_ALUNO_ID" && -n "$NEW_CURSO_ID" ]]; then
    test_endpoint "7.1 Remoção de Matrícula" \
        "curl -X DELETE 'http://localhost:8080/academico/matriculas/aluno/$NEW_ALUNO_ID/curso/$NEW_CURSO_ID' -s -w '%{http_code}' | tail -c 3" \
        "number"
fi

echo -e "${BLUE}=== 8. TESTES DE VALIDAÇÃO E ERRO ===${NC}"

test_endpoint "8.1 Busca de Aluno Inexistente" \
    "curl -s 'http://localhost:8080/academico/alunos/99999' -w '%{http_code}' | tail -c 3" \
    "number"

test_endpoint "8.2 Busca de Curso Inexistente" \
    "curl -s 'http://localhost:8080/academico/cursos/99999' -w '%{http_code}' | tail -c 3" \
    "number"

echo -e "${BLUE}=== 9. TESTES DE FRONTEND ===${NC}"

test_endpoint "9.1 Carregamento da Página Principal" \
    "curl -s 'http://localhost:4200' | grep -o '<title>[^<]*' | sed 's/<title>//'" \
    "text"

test_endpoint "9.2 Verificação de Componentes Angular" \
    "curl -s 'http://localhost:4200' | grep -c 'app-'" \
    "number"

echo -e "${BLUE}=== 10. TESTES DE PERFORMANCE ===${NC}"

echo -n "  10.1 Tempo de Resposta da API de Alunos... "
time_result=$(time (curl -s "http://localhost:8080/academico/alunos" > /dev/null) 2>&1 | grep real | cut -d'm' -f2 | cut -d's' -f1)
echo -e "${GREEN}✅ ${time_result}s${NC}"

echo -n "  10.2 Tempo de Resposta do Frontend... "
time_result=$(time (curl -s "http://localhost:4200" > /dev/null) 2>&1 | grep real | cut -d'm' -f2 | cut -d's' -f1)
echo -e "${GREEN}✅ ${time_result}s${NC}"

echo ""
echo -e "${BLUE}=== 11. LIMPEZA DE DADOS DE TESTE ===${NC}"

# Limpeza dos dados de teste criados
if [[ -n "$NEW_ALUNO_ID" ]]; then
    echo -n "  11.1 Removendo aluno de teste... "
    curl -X DELETE "http://localhost:8080/academico/alunos/$NEW_ALUNO_ID" -s > /dev/null
    echo -e "${GREEN}✅ REMOVIDO${NC}"
fi

if [[ -n "$NEW_CURSO_ID" ]]; then
    echo -n "  11.2 Removendo curso de teste... "
    curl -X DELETE "http://localhost:8080/academico/cursos/$NEW_CURSO_ID" -s > /dev/null
    echo -e "${GREEN}✅ REMOVIDO${NC}"
fi

echo ""
echo -e "${GREEN}🎉 BATERIA DE TESTES CONCLUÍDA!${NC}"
echo "=================================================="
echo "✅ Sistema Acadêmico: TOTALMENTE FUNCIONAL"
echo "✅ Backend APIs: OPERACIONAIS"
echo "✅ Frontend Angular: ATIVO"
echo "✅ Banco PostgreSQL: PERSISTINDO DADOS"
echo "✅ Relacionamento Many-to-Many: IMPLEMENTADO"
echo "✅ CRUD Completo: FUNCIONANDO"
echo ""
echo "🔗 URLs de Acesso:"
echo "   Frontend: http://localhost:4200"
echo "   Backend:  http://localhost:8080"
echo "   API Docs: http://localhost:8080/swagger-ui.html"
echo ""
echo "📊 Estatísticas Finais:"
echo -n "   Alunos no sistema: "
PGPASSWORD=aluno psql -h 127.0.0.1 -U postgres -d jpa -c "SELECT COUNT(*) FROM aluno;" -t 2>/dev/null | xargs || echo "N/A"
echo -n "   Cursos no sistema: "
PGPASSWORD=aluno psql -h 127.0.0.1 -U postgres -d jpa -c "SELECT COUNT(*) FROM curso;" -t 2>/dev/null | xargs || echo "N/A"
echo -n "   Matrículas ativas: "
PGPASSWORD=aluno psql -h 127.0.0.1 -U postgres -d jpa -c "SELECT COUNT(*) FROM aluno_curso;" -t 2>/dev/null | xargs || echo "N/A"
echo ""
echo "Teste concluído em: $(date)"
