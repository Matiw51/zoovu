The credentials to log in to are John / John . 
I've added Angular, but haven't done anything in it. I just wanted to learn how to join front-end with Spring Boot for now, may expand later.

1. Created logging in with users stored in a h2 database. They can access main site, but they cannot access the leaderboard.
I've made dto and mapper for the user as well.

2. Created the leaderboard functions for the user controller. Created a function for leaderboard with date as well.


3. Cooperative mode proposition:

	Two people would take turns choosing the right piece. The correctness would matter in the first place, the time in the second one. 
	I'm not sure how to synchronize their play as I've never done that. 
	
	I'd imagine this as the user creating a lobby with 2 slots. When the other user would connect, game would be possible to get started.
	Then one person gets blocked until another one makes a move. It's a version that wouldn't have troubles with moving the pieces at the same time.
	
	Mock classes: 
	a) LobbyController with 
	GET("/lobby/{id}") for searching a direct lobby, 
	GET("/lobby") for searching lobbies for playing with strangers,
	POST("/lobby") for creating new lobbies
	
	b) A class that would make the actual game possible. I have never synchronized people in that way yet - I'd have to research more.

	
4. Cool features to enchance the game:
	
	a) Choosing how many pieces it will split to.
	b) Race mode where people would fight for the best score simultaneously within a timeframe of let's say 10 minutes. 
	There could be another metric in the leaderboard - "won races". That would encourage people to play the game.
	c) Different types of logo. Maybe commonness of choice could be an indicator which one would be the most attention-grabbing.

	
