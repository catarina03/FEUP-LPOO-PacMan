# PAC-MAN Lanterna WIP

## Classic Pac-Man game using [lanterna](https://github.com/mabe02/lanterna)

## Objetivo Final:
Ter um jogo de PacMan classico funcional a partir do terminal lanterna.
 
 Classic             |  Lanterna
 :-------------------------:|:-------------------------:
 ![Classic Pac-man](https://i.imgur.com/g4JHi4Z.jpg)  |  ![lanterna](https://i.imgur.com/UW9AwuK.png)
 
 

## Features
- Criar o mapa a partir de um ficheiro txt, isto permitirá ter multiplos mapas de forma organizada, acessivel e facilmente manipuláveis, tendo em conta o mapa original (Os mapas precissam de ser ajustados para terem uma forma mas quadrada):
  - \# wall
  - c coin
  - $ power pellet
  - g gate
  - e empty
  - M Pac-Man
  - B Blinky (Red Elements.Ghost)
  - P Pinky (Pink Elements.Ghost)
  - I Inky (Blue Elements.Ghost)
  - K Clyde (Orange Elements.Ghost)
  - \ endline delimiter
 
 
 Classic with Grid   |  Lanterna | Lantern Adjusted
 :-------------------------|:-------------------------:|:-----------:
 ![Grid Pacman](https://i.imgur.com/fDyiXt8.png)  |  ############################<br>#cccccccccccc##cccccccccccc#<br>#c####c#####c##c####c#####c#<br>#$####c#####c##c####c#####$#<br>#c####c#####c##c####c#####c#<br>#cccccccccccccccccccccccccc#<br>#c####c##e########e##c####c#<br>#c####c##e########e##c####c#<br>#cccccc##eeee##eeee##cccccc#<br>######c#####e##e#####c######<br>eeeee#c#####e##e#####c#eeeee<br>eeeee#c##eeeeBeeeee##c#eeeee<br>eeeee#c##e###gg###e##c#eeeee<br>######c##e#eeeeee#e##c######<br>eeeeeeceee#IePeeK#eeeceeeeee<br>######c##e#eeeeee#e##c######<br>eeeee#c##e########e##c#eeeee<br>eeeee#c##eeeeeeeeee##c#eeeee<br>eeeee#c##e########e##c#eeeee<br>######c##e########e##c######<br>#cccccccccccc##cccccccccccc#<br>#c####c#####c##c#####c####c#<br>#c####c#####c##c#####c####c#<br>#$cc##ccccccceMccccccc##cc$#<br>###c##c##c########c##c##c###<br>###c##c##c########c##c##c###<br>#cccccc##cccc##cccc##cccccc#<br>#c##########c##c##########c#<br>#c##########c##c##########c#<br>#cccccccccccccccccccccccccc#<br>############################ | ##################################################<br>#cececececececececececec##cececececececececececec#<br>#c#########c###########c##c###########c#########c#<br>#$#########c###########c##c###########c#########$#<br>#c#########c###########c##c###########c#########c#<br>#cecececececececececececeecececececececececececec#<br>#c#########c#####e##############e#####c#########c#<br>#c#########c#####e##############e#####c#########c#<br>#cececececec#####eeeeeee##eeeeeee#####cececececec#<br>###########c###########e##e###########c###########<br>eeeeeeeeee#c###########e##e###########c#eeeeeeeeee<br>eeeeeeeeee#c####eeeeeeeeeBeeeeeeee####c#eeeeeeeeee<br>eeeeeeeeee#c####e#######gg#######e####c#eeeeeeeeee<br>###########c####e#eeeeeeeeeeeeee#e####c###########<br>eeeeeeeeeeeceeeee#eeIeeePeeeeKee#eeeeeceeeeeeeeeee<br>###########c####e#eeeeeeeeeeeeee#e####c###########<br>eeeeeeeeee#c####e################e####c#eeeeeeeeee<br>eeeeeeeeee#c####eeeeeeeeeeeeeeeeee####c#eeeeeeeeee<br>eeeeeeeeee#c####e################e####c#eeeeeeeeee<br>###########c####e################e####c###########<br>#cececececececececececec##cececececececececececec#<br>#c###########c#########c##c#########c###########c#<br>#c###########c#########c##c#########c###########c#<br>#$ecec#######cecececececeMcececececec#######cece$#<br>#####c#######c######c########c######c#######c#####<br>#####c#######c######c########c######c#######c#####<br>#cecececececec######ecec##cece######cecececececec#<br>#c#####################c##c#####################c#<br>#c#####################c##c#####################c#<br>#cecececececececececececeecececececececececececec#<br>##################################################


### Notas
Each of the four ghosts have their own unique, distinct artificial intelligence (A.I.), or "personalities"; Blinky gives direct chase to Pac-Man, Pinky and Inky try to position themselves in front of Pac-Man, usually by cornering him, and Clyde will switch between chasing Pac-Man and fleeing from him.

[Pac-Man wiki pt](https://pt.wikipedia.org/wiki/Pac-Man)\
[Pac-Man wiki eng](https://en.wikipedia.org/wiki/Pac-Man)

