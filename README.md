# Tower Defense Project

**Last updated**: `October 7th 2019 - Binay Gurung`
##### Task - Duties
1. Arena Building - Kashish Jagtiani
2. Towers - Binay Gurung
3. Monsters - Aayush Shrestha
##### Things to Note from Piazza 
1. ***(Game Physics)*** Tower’s after being built in a spot `cannot move` (i.e. it cannot be removed to another location, only can be destroyed and rebuilt elsewhere)
2. ***(Game Physics)*** All Towers Shoot at a monster (1 monster) at a time, however, catapult and laser tower can also damage monsters nearby.
3. ***(Task3)*** **`Winning the game or run forever?`** It is up to our own design on whether we want the game to run forever till player loses or there is a winning stage/condition
4. ***(Task3)*** **`What is it meant by time elapsed? Need wave system?`** Not clearly stated in the document, design is up to us on how we want the game to be.
5. ***(Task2)*** **`Travel time of the catapult? Do the thrown stones land immediately?`** It is not stated so it up to our design.
6. ***(Task2)*** **`“No tower shall be able to move.” What do you mean by “move”? Do you mean a tower cannot move like how a monster moves, or a tower cannot be moved to somewhere else after built?`** Move refers to a change of location. So, it cannot move like monster, cannot be moved to somewhere else after built.
7. ***(Task2)*** **`What happens if a tower attacks? Do all the monsters in that grid get attacked, or it differs between different kinds of towers? If not, which monsters to apply the damage for?`** Monster is modeled as a pixel so even a grid contain multiple monster, it does not has any implication on monster overlaps. All towers shoot at a monster (one monster) at a time. However, Catapult and Laser Tower will also bring damage to the monsters near by (according to the definition). In case of Catapult and Laser Tower, you can also consider that they are shooting at one monster.
8. ***(TeamWork)*** **`Design pattern --> Will it be taught, or we need to research ourselves?`** Yes, it is in the syllabus. You are also encouraged to do some research on this topic
9. ***(Testing)*** **`If the lab machine is running a Java version below the one used in our program, how do I test it if I have used some features which only available in a higher version of Java?`** You will need to modify you build.gradle to do it.
