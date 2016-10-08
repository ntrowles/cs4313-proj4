Problem:
	Minesweeper game
		Starting state
			Game Board
				2-D array with mines, can be marked as flags or questionable
				? char array ?
				//gayyyyyy
				
				Hidden v Visiblae
				
			Score/performance:
				--
				| How many steps
				| How many revealed
				| How many 
				-- Relation:
					All used in heuristic
					
					Optimizing heuristic:
						Hardcoded equation
						Genetic
						Regression (Linear, polynomial)
						Neural Networks
						
			Solution:
				State where all non-bomb points are revealed
				Path
				Start State
			
			Path:
				List of clicks (point is selected) and resulting states (result of state-changing function)
			
			State-change fn:
				Given an initial state and action (selecting a point), give the resulting state
					//adheres to rules of game
			
			**RULES OF MINESWEEPER**
			2-D array that contains 'n' bombs
			Map is initialized with bombs placed "randomly" over board
			Map is updated so that non-bomb positions are given a number 'a' [0, 8], where 'a' is the number of neighbors that have bombs
			Each position is either revealed or hidden, initially all states are hidden
				**BONUS: Don't initialize map until first action, so that it is guarenteed that the first action is not to click on a bomb (generate map after first action (first click))
			Agent clicks (selects) point (x,y coordinate)
			As a result:
				each point is updated, either revealed
			Agent does this until a terminal state is reached
				Bomb is hit (loss)
				all positions are revealed except bombs (win)
			
			
			Score is evaluated each iteration:
				Action -> resulting State (history kept track of by agent)
				//see above for evaluation/optimizing evaluation
				
		AGENT OPERATION
		
		Knowledge:
			Starting state
			Path (list of actions), and resulting state
			Nothing else (I think)
		
			RL Approach
				??
				Given a state, choose action based on function
					Function parameters are altered by previous actions and resulting scores
					
			? Q-Network ?