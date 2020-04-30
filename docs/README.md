# LPOO_11 - PacMan

O objetivo deste projeto é recriar um jogo PacMan clássico usando o terminal Lanterna em Java.

## Implemented Features:
- **Terminal do jogo** - Usando um *Screen* do Lanterna temos uma visão do mapa original do PacMan usando apenas caracteres de terminal.
- **Leitura de mapas** - O jogo consegue ler um mapa formatado a partir de um ficheiro txt e apresentar esse mapa no ecrã.
- **Movimento do Pac-man** - O PacMan está sempre em movimento constante dependendo da sua direção, apenas parando quando vai contra uma parede. É possível alterar a sua posição com as setas do teclado. 
- **Buffer de movimentos** - Antes de chegar a uma curva o *player* pode premir uma tecla para alterar a direção e o PacMan alterará a sua posição assim que conseguir.
- **Colisão com paredes** - O PacMan não consegue passar pelas paredes, só consegue andar em sítios com fundo preto.
- **Colisão com moedas** - Ao tocar numa moeda o PacMan retira essa moeda do mapa e o score é aumentado em 1.

![](res/pac-man_coins.gif)

## Planned Features:
- **Power Pellets** - Quando são comidas pelo Pac-man os fantasmas passam para "blue mode", ou seja, passam a poder ser comidos pelo Pac-man
- **Continuidade do mapa** - Caso o Pac-man saia do limite do ecrã, ele reaparecerá no lado oposto
- **Personalidade dos fantasmas** - Cada fantasma terá uma personalidade distinta relativamente à forma como persegue o PacMan.


## Design

### Model-View-Controller
#### Problem in Context
#### The Pattern
#### Implementation
#### Consequences

## Code Smells
### 1. Bloaters - Large CLass
Class Game was too large
Solution: Extract Class
Foi criada classe CollisionChecker

### 2. Code Smell 2
### 3. Code Smell 3

## Self-Evaluation
- André Gomes: x %
- Catarina Fernades: y %