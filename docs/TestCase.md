1. captureStones(int stoppingPoint)
Test Case ID	Test Scenario	Expected Outcome
1	Valid stopping point, stones to capture exist	Returns the correct number of captured stones
2	Invalid stopping point (negative value)	Throws PitNotFoundException
3	Invalid stopping point (beyond pit range)	Throws PitNotFoundException
4	More than one stone in the starting pit	Throws PitNotFoundException

2. distributeStones(int startingPoint)

Test Case ID	Test Scenario	Expected Outcome
1	Valid starting point, stones to distribute exist	Returns the correct number of stones distributed
2	Invalid starting point (negative value)	Throws PitNotFoundException
3	Invalid starting point (beyond pit range)	Throws PitNotFoundException

3. getNumStones(int pitNum)

Test Case ID	Test Scenario	Expected Outcome
1	Valid pit number, stones exist in the pit	Returns the correct number of stones in the pit
2	Invalid pit number (negative value)	Throws PitNotFoundException
3	Invalid pit number (beyond pit range)	Throws PitNotFoundException

4. isSideEmpty(int pitNum)

Test Case ID	Test Scenario	Expected Outcome
1	Valid pit number, side is empty	Returns true
2	Valid pit number, side is not empty	Returns false
3	Invalid pit number (negative value)	Throws PitNotFoundException
4	Invalid pit number (beyond pit range)	Throws PitNotFoundException

5. resetBoard()

Test Case ID	Test Scenario	Expected Outcome
1	Board in any state	Board is reset to the initial state

6. registerPlayers(Player one, Player two)

Test Case ID	Test Scenario	Expected Outcome
1	Valid players provided	Players are correctly connected to their stores