#!/bin/bash

# Script para iniciar Backend e Frontend simultaneamente
# Sistema Acadêmico - POO2

echo "🚀 Iniciando Sistema Acadêmico Completo..."
echo "==========================================="

# Função para verificar se uma porta está em uso
check_port() {
    if netstat -tlnp | grep -q ":$1 "; then
        echo "⚠️  Porta $1 já está em uso!"
        return 1
    fi
    return 0
}

# Verificar portas
echo "🔍 Verificando portas..."
if ! check_port 8080; then
    echo "   Backend (porta 8080) precisa estar livre"
fi

if ! check_port 4200; then
    echo "   Frontend (porta 4200) precisa estar livre"
fi

# Diretórios
BACKEND_DIR="/home/dueiriel/Documents/POO2/Projeto Final/backend-academico2025-completo-main"
FRONTEND_DIR="/home/dueiriel/Documents/POO2/Projeto Final/app-academico20205-completo-main"

echo ""
echo "📁 Verificando diretórios..."
if [ ! -d "$BACKEND_DIR" ]; then
    echo "❌ Diretório backend não encontrado: $BACKEND_DIR"
    exit 1
fi

if [ ! -d "$FRONTEND_DIR" ]; then
    echo "❌ Diretório frontend não encontrado: $FRONTEND_DIR"
    exit 1
fi

echo "✅ Diretórios encontrados"

# Criar logs
LOG_DIR="/tmp/projeto-academico-logs"
mkdir -p "$LOG_DIR"

echo ""
echo "🔧 Iniciando Backend (Spring Boot)..."
cd "$BACKEND_DIR"
nohup ./mvnw spring-boot:run > "$LOG_DIR/backend.log" 2>&1 &
BACKEND_PID=$!
echo "   Backend PID: $BACKEND_PID"

echo ""
echo "⏳ Aguardando backend inicializar (30 segundos)..."
sleep 30

# Verificar se backend subiu
if curl -s http://localhost:8080/academico/alunos > /dev/null 2>&1; then
    echo "✅ Backend online em http://localhost:8080"
else
    echo "⚠️  Backend pode ainda estar inicializando..."
fi

echo ""
echo "🎨 Iniciando Frontend (Angular)..."
cd "$FRONTEND_DIR"
nohup npx ng serve --host 0.0.0.0 --port 4200 > "$LOG_DIR/frontend.log" 2>&1 &
FRONTEND_PID=$!
echo "   Frontend PID: $FRONTEND_PID"

echo ""
echo "⏳ Aguardando frontend inicializar (45 segundos)..."
sleep 45

echo ""
echo "🎉 Sistema Iniciado!"
echo "==================="
echo "🔗 URLs de Acesso:"
echo "   🖥️  Frontend: http://localhost:4200"
echo "   🔧 Backend:  http://localhost:8080"
echo "   📚 API Docs: http://localhost:8080/swagger-ui.html"
echo ""
echo "📊 Processos:"
echo "   Backend PID:  $BACKEND_PID"
echo "   Frontend PID: $FRONTEND_PID"
echo ""
echo "📋 Para parar os serviços:"
echo "   kill $BACKEND_PID $FRONTEND_PID"
echo ""
echo "📁 Logs disponíveis em:"
echo "   Backend:  $LOG_DIR/backend.log"
echo "   Frontend: $LOG_DIR/frontend.log"
echo ""
echo "💡 Para monitorar logs em tempo real:"
echo "   tail -f $LOG_DIR/backend.log"
echo "   tail -f $LOG_DIR/frontend.log"

# Salvar PIDs para script de parada
echo "$BACKEND_PID" > "$LOG_DIR/backend.pid"
echo "$FRONTEND_PID" > "$LOG_DIR/frontend.pid"

echo ""
echo "✅ Script concluído! Os serviços estão rodando em background."
