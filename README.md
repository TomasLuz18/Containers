# Containers - Organizacao Otimizada de Contentores

Este projeto implementa uma solução em Java para o problema de reorganização de contentores empilhados utilizando um algoritmo de busca heurística (*Best-First Search*). O objetivo é encontrar a sequência de movimentos mais eficiente (com menor custo) para transformar uma configuração inicial em uma configuração final desejada.

---

## 🚀 Como Executar

### 1. Compilar o projeto
Abra um terminal na pasta `src/` e compile os arquivos Java:

```bash
javac *.java
```

### 2. Executar o programa

```bash
java Main
```

### 3. Inserir as configurações
Digite duas linhas:
- **Linha 1**: configuração inicial dos contentores (com custos)
- **Linha 2**: configuração meta (sem custos)

#### Formato da entrada:

A configuração inicial consiste em sequências de pares `LetraNúmero`, onde:
- A **letra** representa o identificador do contentor
- O **número** representa o **custo de mover** esse contentor

A ordem dos pares indica a ordem dos contentores de baixo para cima.

A configuração final é uma sequência de letras indicando a ordem desejada dos contentores, também do base para o topo, **sem incluir os custos**.

#### Exemplo:
```
A2 C4D3 B1
BC DA
```
Nesse exemplo:
- `A2 C4D3 B1` significa que o contentor A custa 2 para mover, C custa 4, D (em cima de C) custa 3, e B custa 1.
- A meta é organizar os contentores em duas pilhas BC DA (apenas se pode movar contentores do topo ou se tiverem sozinhos no chão).

---

## 🧠 Funcionalidades
- Representação de layouts de contentores como listas empilhadas.
- Algoritmo Best-First Search para encontrar soluções eficientes.
- Impressão da sequência de estados até a solução.
- Cálculo do custo total da solução.

---

## 📁 Estrutura do Projeto

```
Containers/
├── src/                  # Código fonte Java
│   ├── Main.java         # Ponto de entrada do programa
│   ├── Containers.java   # Representa os contentores e estados
│   ├── BestFirst.java    # Implementação do algoritmo heurístico
│   ├── Efficiency.java   # Função de avaliação (heurística)
│   └── Ilayout.java      # Interface para layouts de estados
└── ...
```

---

## 🛠️ Requisitos
- Java 8 ou superior

---

## 📌 Notas
- Se não houver solução possível, o programa imprime:
  ```
  No solution found
  ```
- Ao encontrar solução, imprime cada estado da sequência e o custo final.

---

## 📜 Licença
Este projeto é de uso educacional e livre para modificação.

