# LPOO_11 - Pac-Man

O objetivo deste projeto é recriar um jogo PacMan clássico (1980) usando o terminal Lanterna em Java.

Este projeto foi desenvolvido por André Gomes (up201806224@fe.up.pt) e Catarina Fernandes (up201806610@fe.up.pt) para a Unidade Curricular LPOO 2019/2020.

## Implemented Features:
- **Terminal do jogo** - Usando um *Screen* do Lanterna temos uma visão do mapa original do PacMan usando apenas caracteres de terminal. Também implementamos uma GUI que usufrui de uma _font_ quadrada para o jogo ser mais apelativo.
- **Leitura de mapas e highscore** - O jogo consegue ler um mapa formatado a partir de um ficheiro txt, juntamente com o _High Score_ e apresentar essa informação no ecrã. 2 Mapas/Gui's foram implementados.
- **Movimento do Pac-man** - O Pac-Man está sempre em movimento constante dependendo da sua direção, apenas parando quando vai contra uma parede. É possível alterar a sua posição com as setas do teclado. 
- **Buffer de movimentos** - Antes de chegar a uma curva o *player* pode premir uma tecla para alterar a direção e o PacMan alterará a sua posição assim que conseguir. Não é possivel virar o Pac-Man contra uma das paredes quando este está num corredor.
- **Colisão com paredes** - O PacMan não consegue passar pelas paredes, só consegue andar em sítios com fundo preto e não consegue entrar na _Ghost House_ a partir dos portões.
- **Colisão com moedas** - Ao tocar numa moeda o PacMan retira essa moeda do mapa e o score é aumentado em 1.
- **Power Pellets** - Quando são comidas pelo Pac-man os fantasmas passam para "Frightened Mode", ou seja, passam a poder ser comidos pelo Pac-man. O tempo de duração é de 8 segundos. Caso um fantasma esteja dentro da casa, ou após ser comido, não passará para "Frightened Mode" se o Pac-Man comer um Power Pellet. Cada Power Pellet vale 50 pontos.
- **Continuidade do mapa** - Caso o Pac-man saia do limite do ecrã, ele reaparecerá no lado oposto
- **Ghosts** - Cada Fantasma tem a sua estratégia ou _personalidade_ relativamente à forma como vai atrás do Pac-Man. Podem se encontrar em vários estados e são independentes uns dos outros, mesmo usando a mesma classe de controlador.

| GuiRectangle |  GuiSquare |
|-----------|---------|
| ![Rectangle2Gui](https://user-images.githubusercontent.com/54408098/83135136-50152100-a0dd-11ea-85fd-ee9061f9f32a.gif) | ![SquareGui](https://user-images.githubusercontent.com/54408098/83068272-02a69e80-a060-11ea-89ba-b911f54fb681.gif) |


## Design

### 1. Model-View-Controller
#### Problem in Context
Numa fase inicial do projeto, para ter um jogo funcional (a conseguir abrir uma janela) o código estava dividido por classes mas tudo na mesma package e sem distinção sobre o que cada class fazia (PacMan conseguia guardar a sua informação, desenhar-se a si próprio e atualizar a sua posição). Isto viola seriamente o **Single Responsability Principle** e a forma de resolver foi aplicar o padrão arquitetural **Model-View-Controller**.
#### The Pattern
> MVC consists of three kinds of objects. The Model is the application object, the View is its screen presentation, and the Controller defines the way the user interface reacts to user input.
>
> GoF, Design Patterns - Elements of Reusable Object-Oriented Software

![](https://i.imgur.com/FLvkd0x.png) [Fig. 1](https://medium.com/totvsdevelopers/protheus-mvc-72901b7efc8a)

Este padrão arquitetural é habitualmente usado para desenvolver programas que contenham uma interface para o utilizador e consiste em dividir uma aplicação em 3 partes:
- O **Modelo** representa os dados do programa.
- A **Vista** mostra os dados do **Modelo** e manda as ações do utilizador para o **Controlador**.
- O **Controlador** fornece dados do **Modelo** à **Vista** e interpreta as ações do utilizador.
  
#### Implementation
A estrutura do código está dividida de forma a haver 3 packages, cada package referente a um ponto do _MVC_ e uma classe _Application_ que serve como executável e que contém o método _main()_. Cada package de MVC tem dentro as classes referentes ao seu perfil.

![](https://i.imgur.com/IQbc6C3.png)

| [Controller Package](../src/main/java/g11/controller)  | [Model Package](../src/main/java/g11/model) | [View Package](../src/main/java/g11/view)  |
|-------------|-------|-------|
| ![](https://i.imgur.com/BAGY5HX.png) | ![](https://i.imgur.com/m7mYSE7.png) | ![](https://i.imgur.com/9dgubsn.png) |


#### Consequences
- Divisão do código em packages e melhor organização
- Independência entre módulos possibilita testar cada parte do MVC individualmente
- Possibilidade de adicionar novas GUI's sem alterar os packages Model e Controller

### 2. State Method
#### Problem in Context

#### The Pattern

#### Implementation
![](https://i.imgur.com/PSA69ED.png)
![](https://i.imgur.com/i4qQpxZ.png)
#### Consequences


### 3. Strategy 
#### Problem in Context
Seguindo o _design pattern_ acima apresentado, iremos implementar o DP _Strategy_ para que quando seja preciso atualizar o **Model** a partir dos controladores de _Ghosts_, o controlador principal não tenha de se preocupar com qual controlador é que está a lidar.
#### The Pattern
O objetivo deste DP é encapsular algoritmos e fazê-los permutáveis para poder processar um a um. Conseguimos captar a partir da classe abstrata o comum a todas as subclasses e com isto podemos implementar os detalhes apenas nas classes derivadas.

Com este DP também tocamos no **Open-Closed Principle** já que _GhostController_ fica aberto para extensão mas fechado para modificação, apenas aplicável ás subclasses. 
#### Implementation
![](https://i.imgur.com/f64KgZa.png)

> **_NOTE:_**  Classes não têm links associados porque ainda vão ser criadas. Excepto [Game](../src/main/java/g11/controller/Game.java).

#### Consequences
- Facilidade para adicionar mais controladores 
- Facilidade para executar _update()_ para cada controlador

## Code Smells
### 1. Bloaters - Large CLass
A meio do Projeto apercebemo-nos que só tinhamos uma classe **Control** que tratava de todo o jogo. Esta tinha demasiados métodos e cada método era demasiado comprido.

**Solution**: Extract Class

A partir de uma só classe foi possível criar **3** classes: CollisionChecker, MapReader e ReadFile. A class MapReader faz uso de ReadFile e a classe original Game faz uso de MapReader para, _you guessed it_, ler o mapa.

![](https://i.imgur.com/IohGNNh.png)
### 2. Comments
Os controladores são a parte que vai receber mais trabalho na segunda fase deste projeto, mas por enquanto estão complicados de entender, para isso há métodos que têm demasiados comentários que vão ter de ser removidos antes da entrega final, temos como exemplo o método update do controlador principal:

```java
 private void update(GameData gameData) {
        // Can Pacman move to next position?
            // Pacman's next position?
            // Is a wall in the position pacman is about to move to?
        // yes -> update position
        // no  -> don't update
        if (!checkWallColison(pacManNextPosition())){
            coinColison(gameData.getPacMan().getPosition());
            gameData.update();
        }
    }
```

e o método run, também de Game:
```java
   public void run() throws IOException {
        long startTime = System.currentTimeMillis();
        boolean alreadyin = false;
        // ciclo de jogo
        while(true) {
            // Ler Esc para sair de ciclo
            KeyStroke keyStroke = screen.pollInput();
            if(keyStroke != null ){
                if(keyStroke.getKeyType() == KeyType.Escape || keyStroke.getKeyType() == KeyType.EOF) {break;}
                else{processKey(keyStroke);
                    //pacMan.moveDirection();}
            }
            //Detetar keystrokes de setas e mudar direção de pacman
            // taxa de atualização a cada meio segundo
            if ((System.currentTimeMillis() - startTime) % 250 == 0){
                // como entra mais do que uma vez a cada milissegundo, só vai atualizar uma vez
                if (!alreadyin){
                    System.out.println(System.currentTimeMillis() - startTime);
                    pacMan.moveDirection();
                    draw();
                    alreadyin = true;}
            }
            else{
                // assim que sair do milissegundo em que dá refresh, avisa que pode dar refresh outra vez
                alreadyin = false;
            }
        }
        if (screen != null)
            screen.close();
    }
```
### 3. Switch Statements
Na classe [Gui.java](../src/main/java/g11/view/Gui.java) é declarado um enum: `public enum MOVE {UP, DOWN, LEFT, RIGHT, ESC}`, tal como em [Orientation.java](../src/main/java/g11/model/Orientation.java): `public enum Orientation {UP, DOWN, LEFT, RIGHT}` .

Devido a estes enums surgem ao longo do programa switch cases para selecionar o que fazer dependendo do valor de uma variável enum:

Em [checkWallCollision() e orientationToMove()](../src/main/java/g11/controller/CollisionChecker.java):
```java
switch (direction){
    case UP:
        pacmannextpos = gameData.getPacMan().getPosition().up();
        break;
    case DOWN:
        pacmannextpos = gameData.getPacMan().getPosition().down();
        break;
    case LEFT:
        pacmannextpos = gameData.getPacMan().getPosition().left();
        break;
    case RIGHT:
        pacmannextpos = gameData.getPacMan().getPosition().right();
        break;
}
...
switch (orientationEnumeration){
            case UP:
                return Gui.MOVE.UP;
            case DOWN:
                return Gui.MOVE.DOWN;
            case LEFT:
                return Gui.MOVE.LEFT;
            case RIGHT:
                return Gui.MOVE.RIGHT;
        }
        return Gui.MOVE.ESC;
```

Mesmo sendo necessários e executando simples operações podem ser considerados um _code smell_ caso comecem a ser demasiado proeminentes ao longo do código. 

**Solution**: Replace Type Code with Subclasses

O refactoring **Replace Type Code with Subclasses** já foi usado para criar a classe [Moveable](../src/main/java/g11/model/Elements/Moveable.java) para esta ser a única que será a _parent class_ dos objetos móveis e com isto [Pacman]() e os futuros Ghosts apenas precisam de fazer _extend_ a esta classe.

![](https://i.imgur.com/k07Y7nn.png)

## Testing

![Code Coverage](https://i.imgur.com/ZikaxRv.png)

![Unit Testing](https://i.imgur.com/hYZc5SR.png)

![PiTest](https://i.imgur.com/CukfIXQ.png)

> **_NOTE:_**  Para PiTest não estamos a ter em conta os testes de [Gui](../src/test/java/g11/view/GuiTest.java) e de [Game](../src/test/java/g11/controller/GameTest.java) pois ambos abrem um _Screen_ que faz com que `gradlew pitest` origine um **Build Error**

## Self-Evaluation
Quando à divisão do trabalho foram criados ao longo desta primeira parte vários [Issues](https://github.com/FEUP-LPOO/lpoo-2020-g11/issues) para cada ponto a trabalhar e cada elemento do grupo foi implementando melhorias, refactorings, testes ou funcionalidades do jogo em branches separados que depois ficavam sujeitos a [Pull Requests](https://github.com/FEUP-LPOO/lpoo-2020-g11/pulls) e eram implementados no master. Até agora o trabalho foi bem organizado e dividido entre os membros do grupo, tendo ambos feito um esforço semelhante para a concretização do projeto.
- André Gomes: 50 %
- Catarina Fernades: 50 %
