# Snake_AI

This is java code for an AI agent in graphical snake game.

# Description of game:
Players are two red and blue snakes which compete in a 20 by 20 board. Initially, the length of each one is 1 unit. Later, as they eat empty and filled pits, which are generated randomly on the board, they become longer by 1 and 2 unit, respectively.

Whenever a snake collides with the other one, its length will get decreased by the length of it opponent and if this interaction changes its size to 0 or less, it will lose. Moreover, if a snake moves into itself then the game ends and the other one wins.

This game continues until 500 moves are finished (including both players) and in that case the winner is the one which has the greater length.
