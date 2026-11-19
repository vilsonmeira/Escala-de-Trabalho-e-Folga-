# Escala de Trabalho e Folga — HortiFruti Jardim Celeste

Projeto inicial do aplicativo Android para apoiar escalas e registros de presença relacionados à rede Wi-Fi exclusiva `HJC-PONTO`.

## Estado atual

- Rede planejada: `HJC-PONTO` (roteador TP-Link, banda 2,4 GHz).
- Plataforma-alvo: Android.
- Este repositório inicia a estrutura nativa Android.
- Ainda não existe servidor, cadastro real de funcionários, sincronização ou registro trabalhista validado.

## Como o registro deverá funcionar

1. Um administrador cadastra e autoriza cada funcionário e aparelho.
2. O funcionário entra no aplicativo e vincula seu aparelho à conta.
3. O aplicativo observa a conectividade Wi-Fi com uma notificação persistente enquanto o monitoramento estiver ativo.
4. O servidor valida eventos, horários, duplicidade e sequência antes de registrar uma ocorrência.
5. Quedas curtas, troca de antena ou instabilidade não devem gerar saída automática definitiva; eventos ambíguos ficam pendentes de revisão.

## Limitações importantes

Conectar ao Wi-Fi não prova, por si só, que a pessoa está trabalhando. Android limita a execução em segundo plano e o acesso ao nome da rede; por isso, a detecção precisa ser testada em aparelhos reais e pode exigir permissões adicionais. O aplicativo não deve ser tratado como único meio de controle de jornada sem validação jurídica, técnica e operacional.

## Desenvolvimento

Abra a pasta do projeto no Android Studio e sincronize o Gradle. O primeiro marco é validar a instalação e o monitoramento de conectividade em primeiro plano. A integração com backend, autenticação, painel administrativo e regras de ponto será adicionada em etapas posteriores.
