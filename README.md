# Escala de Trabalho e Folga — Hortifruti Jardim Celeste

Aplicativo Android em desenvolvimento para testar a identificação da rede Wi-Fi exclusiva da empresa: `HJC-PONTO`.

## Estado implementado

- Projeto Android nativo em Kotlin.
- Tela inicial HJC Ponto.
- Verificação se o aparelho está conectado por Wi-Fi.
- Solicitação da permissão de localização necessária para o Android revelar o SSID em versões compatíveis.
- Comparação do nome da rede conectada com `HJC-PONTO`.
- Mensagens para rede correta, outra rede ou SSID oculto.
- GitHub Actions configurado para compilar o APK de depuração e disponibilizá-lo como artefato por 14 dias.

## Como gerar e baixar o APK pelo celular

1. Abra a aba **Actions** deste repositório no GitHub.
2. Abra a execução chamada **Build Android APK**.
3. Aguarde o resultado ficar verde (sucesso).
4. Na seção **Artifacts**, baixe `hjc-ponto-debug`.
5. Extraia o ZIP no celular e instale `app-debug.apk`. O Android poderá pedir autorização para instalar aplicativos dessa origem.

## Teste no aparelho

1. Conecte o celular à rede `HJC-PONTO`.
2. Abra HJC Ponto.
3. Toque em **Autorizar identificação do Wi-Fi** e conceda a permissão.
4. Se necessário, ative a localização do Android.
5. Toque em **Verificar conexão HJC-PONTO**.

## Ainda não implementado

- Login seguro do administrador e dos funcionários.
- Banco de dados e sincronização com servidor.
- Cadastro e autorização dos aparelhos.
- Monitoramento contínuo em segundo plano.
- Registro automático de entrada e saída, com tratamento de quedas e revisões.
- Escalas, folgas, relatórios e trilha de auditoria.

## Limitações e privacidade

O teste atual identifica a rede apenas quando o usuário toca no botão. Ele ainda não registra jornada nem monitora continuamente. O SSID pode ser ocultado pelo Android conforme permissões e configurações do aparelho. A presença no Wi-Fi é um indício técnico, não prova isolada de trabalho; o sistema final deverá permitir revisão e correção dos registros e informar claramente os funcionários sobre os dados coletados.
