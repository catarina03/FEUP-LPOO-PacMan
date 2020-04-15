# PAC-MAN Lanterna WIP

## Classic Pac-Man game using [lanterna](https://github.com/mabe02/lanterna)

## Objetivo Final:

![Classic Pac-man](https://i.imgur.com/g4JHi4Z.jpg)

Um dos Objetivos é criar o mapa a partir de um ficheiro txt, isto permitirá ter multiplos mapas de forma organizada, acessivel e facilmente manipuláveis, tendo em conta o mapa original:
![Grid Pacman](https://i.imgur.com/fDyiXt8.png)

Ficariamos com a seguinte configuração 28x31 (Ver em formato Raw para ter linhas com mesmo tamanho):
 - \# wall
 - c coin
 - $ power pellet
 - g gate
 - e empty
 - M Pac-Man
 - B Elements.Blinky (Red Elements.Ghost)
 - P Elements.Pinky (Pink Elements.Ghost)
 - I Elements.Inky (Blue Elements.Ghost)
 - K Elements.Clyde (Orange Elements.Ghost)
 - \ endline delimiter
```c
############################\
#cccccccccccc##cccccccccccc#\
#c####c#####c##c####c#####c#\
#$####c#####c##c####c#####$#\
#c####c#####c##c####c#####c#\
#cccccccccccccccccccccccccc#\
#c####c##e########e##c####c#\
#c####c##e########e##c####c#\
#cccccc##eeee##eeee##cccccc#\
######c#####e##e#####c######\
eeeee#c#####e##e#####c#eeeee\
eeeee#c##eeeeBeeeee##c#eeeee\
eeeee#c##e###gg###e##c#eeeee\
######c##e#eeeeee#e##c######\
eeeeeeceee#IePeeK#eeeceeeeee\
######c##e#eeeeee#e##c######\
eeeee#c##e########e##c#eeeee\
eeeee#c##eeeeeeeeee##c#eeeee\
eeeee#c##e########e##c#eeeee\
######c##e########e##c######\
#cccccccccccc##cccccccccccc#\
#c####c#####c##c#####c####c#\
#c####c#####c##c#####c####c#\
#$cc##ccccccceMccccccc##cc$#\
###c##c##c########c##c##c###\
###c##c##c########c##c##c###\
#cccccc##cccc##cccc##cccccc#\
#c##########c##c##########c#\
#c##########c##c##########c#\
#cccccccccccccccccccccccccc#\
############################\
```
### Notas
Each of the four ghosts have their own unique, distinct artificial intelligence (A.I.), or "personalities"; Elements.Blinky gives direct chase to Pac-Man, Elements.Pinky and Elements.Inky try to position themselves in front of Pac-Man, usually by cornering him, and Elements.Clyde will switch between chasing Pac-Man and fleeing from him.

[Pac-Man wiki pt](https://pt.wikipedia.org/wiki/Pac-Man)\
[Pac-Man wiki eng](https://en.wikipedia.org/wiki/Pac-Man)



