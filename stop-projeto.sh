#!/bin/bash

# Script para parar Backend e Frontend
# Sistema Acadêmico - POO2

echo "🛑 Parando Sistema Acadêmico..."
echo "==============================="

LOG_DIR="/tmp/projeto-academico-logs"

# Verificar se existem PIDs salvos
if [ -f "$LOG_DIR/backend.pid" ] && [ -f "$LOG_DIR/frontend.pid" ]; then
    BACKEND_PID=$(cat "$LOG_DIR/backend.pid")
    FRONTEND_PID=$(cat "$LOG_DIR/frontend.pid")
    
    echo "📊 PIDs encontrados:"
    echo "   Backend PID:  $BACKEND_PID"
    echo "   Frontend PID: $FRONTEND_PID"
    echo ""
    
    # Parar backend
    if ps -p $BACKEND_PID > /dev/null 2>&1; then
        echo "🔧 Parando Backend..."
        kill $BACKEND_PID
        echo "✅ Backend parado"
    else
        echo "⚠️  Backend já estava parado"
    fi
    
    # Parar frontend
    if ps -p $FRONTEND_PID > /dev/null 2>&1; then
        echo "🎨 Parando Frontend..."
        kill $FRONTEND_PID
        echo "✅ Frontend parado"
    else
        echo "⚠️  Frontend já estava parado"
    fi
    
    # Limpar arquivos PID
    rm -f "$LOG_DIR/backend.pid" "$LOG_DIR/frontend.pid"
    
else
    echo "⚠️  Arquivos PID não encontrados, tentando parar por nome do processo..."
    
    # Parar por nome do processo
    echo "🔧 Parando Spring Boot..."
    pkill -f "spring-boot:run"
    
    echo "🎨 Parando Angular..."
    pkill -f "ng serve"
    
    echo "✅ Processos parados"
fi

# Verificar se ainda há processos rodando
echo ""
echo "🔍 Verificando processos restantes..."
if ps aux | grep -E "(spring-boot:run|ng serve)" | grep -v grep > /dev/null; then
    echo "⚠️  Ainda há processos relacionados rodando:"
    ps aux | grep -E "(spring-boot:run|ng serve)" | grep -v grep
else
    echo "✅ Nenhum processo relacionado está rodando"
fi

echo ""
echo "🎉 Sistema parado com sucesso!"
