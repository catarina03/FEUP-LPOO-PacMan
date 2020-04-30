# LPOO_11 - PacMan

O objetivo deste projeto é recriar um jogo PacMan clássico usando o terminal Lanterna em Java.
> Template : https://web.fe.up.pt/~arestivo/page/courses/2020/lpoo/template/
> 
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

### 1. Model-View-Controller
#### Problem in Context
Numa fase inicial do projeto, para ter um jogo funcional (a conseguir abrir uma janela) o código estava dividido por classes mas tudo na mesma package e sem distinção sobre o que cada class fazia (PacMan conseguia guardar a sua informação, desenhar-se a si próprio e atualizar a sua posição). Isto viola seriamente o **Single Responsability Principle** e a forma de resolver foi aplicar o padrão arquitetural **Model-View-Controller**.
#### The Pattern
> MVC consists of three kinds of objects. The Model is the application object, the View is its screen presentation, and the Controller defines the way the user interface reacts to user input.
>
> GoF, Design Patterns - Elements of Reusable Object-Oriented Software

![](https://i.imgur.com/DduITpl.png) [1](https://medium.com/totvsdevelopers/protheus-mvc-72901b7efc8a)

Este padrão arquitetural é habitualmente usado para desenvolver programas que contenham uma interface para o utilizador e consiste em dividir uma aplicação em 3 partes:
- O **Modelo** representa os dados do programa.
- A **Vista** mostra os dados do **Modelo** e manda as ações do utilizador para o **Controlador**.
- O **Controlador** fornece dados do **Modelo** à **Vista** e interpreta as ações do utilizador.
#### Implementation
> **_NOTE:_**  Adicionar aqui UML ou algo para mostrar MVC em ação.
#### Consequences
- Divisão do código em packages e melhor organização
- Independência entre módulos possibilita testar cada parte do MVC individualmente


### 2. Factory Method (criação de ghosts) **TODO**
#### Problem in Context
#### The Pattern
#### Implementation
#### Consequences

### 3. Strategy (Para update do model? https://web.fe.up.pt/~arestivo/presentation/patterns/#31 )
#### Problem in Context
#### The Pattern
#### Implementation
#### Consequences

## Code Smells
### 1. Bloaters - Large CLass
A meio do Projeto apercebemo-nos que só tinhamos uma classe **Control** que tratava de todo o jogo. Esta tinha demasiados métodos e cada método era demasiado comprido.

**Solution**: Extract Class
A partir de uma só classe foi possível criar **3** classes: CollisionChecker, MapReader e ReadFile. A class MapReader faz uso de ReadFile e a classe original Game faz uso de MapReader para, _you guessed it_, ler o mapa
### 2. Code Smell 2
### 3. Code Smell 3

## Self-Evaluation
- André Gomes: x %
- Catarina Fernades: y %