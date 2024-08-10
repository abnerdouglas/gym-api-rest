FROM node:20-alpine as frontend

# Define o diretório de trabalho
WORKDIR /code

# Copia os arquivos de configuração
COPY ./package.json ./package-lock.json /code/

# Instala as dependências
RUN npm ci

# Define o PATH para os binários do Node.js
ENV PATH /code/node_modules/.bin:$PATH


# Copia o restante do código
COPY . .

# Define o diretório de trabalho
WORKDIR /code/next/

# Expõe a porta
EXPOSE 3000

# Executa o build
RUN npm run build

# Define o usuário
USER 0:0

# Comando padrão para rodar o servidor
CMD ["npm", "run", "dev", "--", "--host", "0.0.0.0", "--port", "3000", "--disableHostCheck", "true"]
