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
| ![](https://i.imgur.com/BAGY5HX.png) | ![](https://i.imgur.com/h7Dv2fq.png) | ![](https://i.imgur.com/tNkfTU4.png) |

|Package Structure|
|---|
|![](https://i.imgur.com/CeL7Aer.png)|

#### Consequences
- Divisão do código em packages e melhor organização
- Independência entre módulos possibilita testar cada parte do MVC individualmente
- Possibilidade de adicionar novas GUI's sem alterar os packages Model e Controller


### 2. State Pattern
#### Problem in Context
Para os Ghosts era necessário uma forma de implementar os seus diferentes estados em que se podiam encontrar, juntamente com uma forma de alterar entre os seus estados de acordo com o tempo decorrido desde o inicio do jogo ou dependendo de outros fatores:

![](https://user-images.githubusercontent.com/54408098/81789782-6081a500-94fc-11ea-972f-d5c8644032b4.png)
[Fig. 2](https://youtu.be/ataGotQ7ir8?list=LLz1wiDr8PNRrbiUlvgHFBZA&t=57)

Para o Game era necessário subdividir este também em diferentes estados para haver uma melhor organização entre o que apresentar no ecrã.
#### The Pattern

Implementamos assim o **State Pattern** para satisfazer as condições acima. Este padrão permite alternar entre diferentes estados em _runtime_ a partir de diferentes subclasses de uma classe abstracta. Com isto conseguimos simplificar o entendimento do comportamente de cada Ghost e principalmente simplificar as transições entre cada state. Quando não tinhamos implementado os _Ghost States_ havia uma função que tomava todos os dados e determinava o estado de cada fantasmas:

```java
public void determineState(long elapsedtime, GhostState ghostState){
        // TODO Ainda fica preso na Ghost House

        // Caso fiquem presos dentro da casa em Chase ou Scatter
        if (ghost.getState() != GhostState.EATEN && ghost.getState() != GhostState.ENTERINGHOUSE && isInsideHouse(ghost)) {
            ghost.setState(GhostState.CHASE);
            ghost.setTarget(new Position(13, 14));
            setExitingHouse(true);
        }
        // Para não sairem e voltarem a entrar
        if (ghost.getState() != GhostState.EATEN &&
                ghost.getState() != GhostState.ENTERINGHOUSE &&
                ghost.getState() != GhostState.FRIGHTENED &&
                ghost.getPosition().equals(new Position(13, 14))) {
            setExitingHouse(false);
            ghost.setState(setStatetime(elapsedtime));
        } else {
            // Houve colisão, Ou é comido ou acaba jogo
            if (ghostState == GhostState.EATEN) {
                if (ghost.getState() == GhostState.FRIGHTENED) {
                    ghost.setState(GhostState.EATEN);
                    setTicksToEndFrightened(0);
                }
            }
            // Entra em FRIGHTENED
            if (ghostState == GhostState.FRIGHTENED) {
                // só põe frightened se não estiver EATEN, ENTERINGHOUSE, ou exiting
                if (ghost.getState() != GhostState.EATEN && ghost.getState() != GhostState.ENTERINGHOUSE && !isExitingHouse()) {
                    ghost.setState(GhostState.FRIGHTENED);
                    setChangeOrientation(true);
                    setTicksToEndFrightened(160);
                }
            }
            // Se estiver em frightened, atualiza o tempo restante
            if (getTicksToEndFrightened() > 0) {
                setTicksToEndFrightened(getTicksToEndFrightened() - 1);
            }
            // Se acabar o Tempo e não estiver a meio de um dos outros passos passa para Chase, que depois é atualizado de acordo com o tempo em baixo
            if (getTicksToEndFrightened() == 0 && ghost.getState() != GhostState.EATEN && ghost.getState() != GhostState.ENTERINGHOUSE && !isExitingHouse())
                ghost.setState(setStatetime(elapsedtime));

            // se estiver em STATE EATEN, não atualiza STATE que vem de ghostState
            // se estiver em ENTERINGHOUSE, também não atualiza o seu state
            // se estiver em FRIGHTENED, não atualiza com base no tempo
            // se estiver a Sair da Casa, também não atualiza o seu state
            if (ghost.getState() != GhostState.EATEN &&
                    ghost.getState() != GhostState.ENTERINGHOUSE &&
                    ghost.getState() != GhostState.FRIGHTENED &&
                    !(isExitingHouse() && ghost.getState() == GhostState.CHASE))
                ghost.setState(setStatetime(elapsedtime));
        }
    }
```

Um óbvio _code smell_ e uma fonte de bugs que só conseguimos resolver assim que implementamos o **State Pattern**. 

Um dos bugs que nos acompanhou enquanto estavamos a implementar os Ghosts acontecia quando um _Ghost_ tentava sair da _Ghost House_ e assim que saía, voltava a entrar e ficava preso. Após implementar o **State Pattern** ficou mais evidente o que causava este comportamento e tornou-se facilmente resolúvel.

#### Implementation
![](https://i.imgur.com/B4QsZVj.png)
- [GhostController](../src/main/java/g11/controller/ghosts/GhostController.java)
- [GhostState](../src/main/java/g11/controller/ghosts/GhostState.java)
- [GhostStateChase](../src/main/java/g11/controller/ghosts/states/GhostStateChase.java)
- [GhostStateScatter](../src/main/java/g11/controller/ghosts/states/GhostStateScatter.java)
- [GhostStateFrightened](../src/main/java/g11/controller/ghosts/states/GhostStateFrightened.java)

![](https://i.imgur.com/GnmdJlS.png)
- [Game](../src/main/java/g11/controller/Game.java)
- [GameState](../src/main/java/g11/controller/gamestates/GameState.java)
- [GameStatePresentation](../src/main/java/g11/controller/gamestates/GameStatePresentation.java)
- [GameStateReady](../src/main/java/g11/controller/gamestates/GameStateReady.java)
- [GameStateRun](../src/main/java/g11/controller/gamestates/GameStateRun.java)


#### Consequences
- Classes obedecem ao _Single Responsability Principle_; Cada estado fica com o seu comportamento.
- O _Open/Closed Principle_ também é obedecido já que é possivel adicionar mais states aos Ghosts. Podemos verificar isto no código já que para além dos estados representados na Fig.2, também implementamos estados extra : [GhostStateEnteringHouse](../src/main/java/g11/controller/ghosts/states/GhostStateEnteringHouse.java) e [GhostStateExitingHouse](../src/main/java/g11/controller/ghosts/states/GhostStateExitingHouse.java).

### 3. Strategy 
#### Problem in Context
Para evitar que os _Ghosts_ fiquem uns em cima dos outros quando estiverem a perseguir o Pac-Man, [Toru Iwatani](https://en.wikipedia.org/wiki/Toru_Iwatani) decidiu dar a cada _Ghost_ uma "personalidade", fornecendo para cada _Ghost_ uma maneira diferentes de perseguir o Pac-man. Diferente do _Design Pattern_ acima, estas personalidades não são alteradas em _runtime_ para cada Ghost. Com isto em mente decidimos aplicar o **Strategy Pattern**.
 
#### The Pattern
Este padrão comportamental deixa-nos definir uma família de algoritmos, pondo cada um numa classe separada. Uma classe "context" terá como atributo um campo para guardar uma referência a uma destas estratégias e com isto foi possível resolver o problema de cada Ghost ter uma "personalidade" diferente.

#### Implementation
![](https://i.imgur.com/UwMuoMB.png)
- [GhostState](../src/main/java/g11/controller/ghosts/GhostState.java)
- [TargetStrategy](../src/main/java/g11/controller/ghosts/TargetStrategy.java)
- [TargetStrategyBlinky](../src/main/java/g11/controller/ghosts/strategies/TargetStrategyBlinky.java)
- [TargetStrategyInky](../src/main/java/g11/controller/ghosts/strategies/TargetStrategyInky.java)
- [TargetStrategyClyde](../src/main/java/g11/controller/ghosts/strategies/TargetStrategyClyde.java)
- [TargetStrategyPinky](../src/main/java/g11/controller/ghosts/strategies/TargetStrategyPinky.java)


#### Consequences
- O _Open/Closed Principle_ é obedecido, o que permite acrescentar mais estratégias.
- É possivel mudar estratégias em _runtime_ (não aplicável ao nosso projeto, mas poderá ser usado no futuro).
- Isolação da implementação de cada algoritmo de perseguição.

### 4. Game Loop 

#### Problem in Context
Qualquer jogo tem de ter um _Loop_ principal, não sendo o Pac-Man uma exceção. Para isto tivemos que refazer o nosso loop original tendo em conta um [_Design Pattern_ existente](https://gameprogrammingpatterns.com/game-loop.html).

#### The Pattern
Do Link acima decidimos usar a opção **Fixed time step with synchronization**:
```java
while (true)
{
  double start = getCurrentTime();
  processInput();
  update();
  render();

  sleep(start + MS_PER_FRAME - getCurrentTime());
}
```
Isto possibilitou separar as 3 partes da criação de uma _frame_ e definir uma _framerate_ mais estável do que o que tinhamos anteriormente.

#### Implementation

<table>
<thead>
  <tr>
    <th>Before</th>
    <th>After</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>

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
</td>
    <td>

    while (game.getRunning()) {
            long current = System.currentTimeMillis();

            // process input
            MoveEnumeration temp = gui.getMove();
            if (temp != null) game.setLastmove(temp);
            boolean pause = game.processKey(game.getLastmove());

            if (pause){
                long starOfPause = System.currentTimeMillis();
                if (pauseScreen(gui)) {
                    game.changeGameState(new GameStatePresentation(game));
                    return false;
                }
                startTime = startTime + (System.currentTimeMillis() - starOfPause);
                game.setLastmove(null);
            }

            // update
            game.update(game.getGameData(), step, System.currentTimeMillis() - startTime);

            // render
            gui.draw(game.getGameData());

            step++;
            long elapsed = System.currentTimeMillis() - current;
            if (elapsed < 50) Thread.sleep(50 - elapsed);
        }
</td>
  </tr>
</tbody>
</table>

- [GameStateRun](../src/main/java/g11/controller/gamestates/GameStateRun.java)

#### Consequences

- Simplicidade de código e atualização organizada.
- _Power-friendly_. Como só atualizamos o jogo a um passo constante, tiramos muita carga do CPU. Este é um dos pontos menos importantes já que o nosso Pac-Man não precisa de muitos recursos.
- O jogo não se atualiza demasiado depressa. Com a taxa de atualização constante não é possivel que máquinas melhores corram o jogo mais rápido.
- O jogo pode correr demasiado devagar. Para máquinas menos capazes, um ciclo pode demorar mais millisegundos a processar do que o esperado, fazendo com que o jogo abrande em certos casos. 

## Code Smells
### 1. Bloaters - Large CLass
A meio do Projeto apercebemo-nos que só tinhamos uma classe **Control** que tratava de todo o jogo. Esta tinha demasiados métodos e cada método era demasiado comprido.

**Solution**: Extract Class

A partir de uma só classe foi possível criar **3** classes: CollisionChecker, MapReader e ReadFile. A class MapReader faz uso de ReadFile e a classe original Game faz uso de MapReader para, _you guessed it_, ler o mapa.

![](https://i.imgur.com/IohGNNh.png)

### 2. If Statements / Switch Statements

Como lidamos com 4 Orientações constantemente (processar comandos, verificar colisões) houve muitas partes de código que conseguimos simplificar e retirar muitos _if's_ repetidos ou até remover _switch cases_. Como exemplo adicionamos ao _enum_ [Orientation](../src/main/java/g11/model/OrientationEnumeration.java) um método que retorna todas as Orientações disponiveis e com isto foi possivel simplificar o seguinte pedaço de código

```java
for (EmptySpace emptySpace : gameData.getMap().getEmptySpaces()) {
            if (emptySpace.getPosition().equals(ghost.getPosition().up())) {
                if (ghost.getOrientation().getOpposite() != UP) {
                    returning.add(UP);
                }
            } else if (emptySpace.getPosition().equals(ghost.getPosition().down())) {
                if (ghost.getOrientation().getOpposite() != DOWN) {
                    returning.add(DOWN);
                }
            } else if (emptySpace.getPosition().equals(ghost.getPosition().left())) {
                if (ghost.getOrientation().getOpposite() != LEFT) {
                    returning.add(LEFT);
                }
            } else if (emptySpace.getPosition().equals(ghost.getPosition().right())) {
                if (ghost.getOrientation().getOpposite() != RIGHT) {
                    returning.add(RIGHT);
                }
            }
        }
```
```java
for (EmptySpace emptySpace : gameData.getMap().getEmptySpaces()) {
            for (OrientationEnumeration ori : OrientationEnumeration.allOptions()) {
                if (emptySpace.getPosition().equals(ghost.getPosition(ori))) {
                    if (ghost.getOrientationEnumeration().getOpposite() != ori)
                        returning.add(ori);
                }
            }
        }
```

### 3. Long Parameter List

O mapa é composto por diversos elementos, cada um com a usa posição e forma de desenhar. Para passar toda a informação de uma vez para a gui e para conseguir ler o mapa do ficheiro _.txt_ para uma única classe foi criada a classe [Map](../src/main/java/g11/model/Map.java):

```java
public class Map {
    private ArrayList<String> map;
    private ArrayList<Wall> walls;
    private ArrayList<EmptySpace> emptySpaces;
    private ArrayList<Coin> coins;
    private ArrayList<PowerPellet> powerPellets;
    private ArrayList<Gate> gates;
    private ArrayList<MapComponent> mapComponents;
    private ArrayList<Position> unturnable;

    public Map(ArrayList<String> map, ArrayList<Wall> walls, ArrayList<EmptySpace> emptySpaces, ArrayList<Coin> coins, ArrayList<PowerPellet> powerPellets, ArrayList<Gate> gates, ArrayList<MapComponent> mapComponents, ArrayList<Position> unturnable) {
        this.map = map;
        this.walls = walls;
        this.emptySpaces = emptySpaces;
        this.coins = coins;
        this.powerPellets = powerPellets;
        this.gates = gates;
        this.mapComponents = mapComponents;
        this.unturnable = unturnable;
    }
```

O custo de ter a simplicidade de apenas passar uma classe para a gui é de sofrermos do smell **Long Parameter List**.

## Testing

// TODO Link to mutation testing report.

![Code Coverage](https://i.imgur.com/ZikaxRv.png)

![Unit Testing](https://i.imgur.com/hYZc5SR.png)

![PiTest](https://i.imgur.com/CukfIXQ.png)

> **_NOTE:_**  Para PiTest não estamos a ter em conta os testes de [Gui](../src/test/java/g11/view/GuiTest.java) e de [Game](../src/test/java/g11/controller/GameTest.java) pois ambos abrem um _Screen_ que faz com que `gradlew pitest` origine um **Build Error**

## Self-Evaluation
Quando à divisão do trabalho foram criados ao longo desta primeira parte vários [Issues](https://github.com/FEUP-LPOO/lpoo-2020-g11/issues) para cada ponto a trabalhar e cada elemento do grupo foi implementando melhorias, refactorings, testes ou funcionalidades do jogo em branches separados que depois ficavam sujeitos a [Pull Requests](https://github.com/FEUP-LPOO/lpoo-2020-g11/pulls) e eram implementados no master. Até agora o trabalho foi bem organizado e dividido entre os membros do grupo, tendo ambos feito um esforço semelhante para a concretização do projeto.
- André Gomes: 50 %
- Catarina Fernades: 50 %

![](https://s7.gifyu.com/images/Progress.gif)