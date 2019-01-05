# Snake AI
This is the java code for an AI agent in a graphical snake game.

# Description of game
Players are two red and blue snakes which move in a 20 by 20 board in turn. Initially, the length of each one is 1 unit. Later, as they eat empty and filled pits, which are generated randomly on the board, they become longer by 1 and 2 unit, respectively.

Whenever a snake collides with the other one, its length will get decreased by the length of it opponent and if this interaction changes its size to 0 or less, it will lose. Moreover, if a snake moves into itself then the game ends and the other one wins.

This game continues until 500 moves are finished (including both players) and in that case the winner is the one which has the greater length.

# Results
In a series of games like a league in which each pair of players played twice (one as first player and the other as second one), my agent played 68 matches (with 34 other agents) and won 64 of them, thus it become the best agent in my AI class.
