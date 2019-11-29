# Tower Defense Project

**Last updated**: 
>November 29th 2019 
>by binay gurung

**Update Log**:
1. `November 29th 2019 - Binay Gurung`
2. `October 7th 2019 - Binay Gurung`
##### Task - Duties
1. Arena Building - Kashish Jagtiani
2. Towers - Binay Gurung
3. Monsters - Aayush Shrestha

#### Monsters
- ##### Fox
  <img src="https://user-images.githubusercontent.com/44058187/69859354-f4eda780-12ce-11ea-99ad-d57f708e640f.png" width="50">
- ##### Penguin
  <img src="https://user-images.githubusercontent.com/44058187/69859378-ffa83c80-12ce-11ea-9a32-b34a3fbc1241.png" width="50">
- ##### Unicorn
  <img src="https://user-images.githubusercontent.com/44058187/69859411-15b5fd00-12cf-11ea-8381-aa76145b8792.png" width="50">

#### Towers
- ##### Basic Tower
  <img src="https://user-images.githubusercontent.com/44058187/69859455-29616380-12cf-11ea-945b-a2602ce9e058.png" alt="drawing" width="50">
- ##### Catapult
  <img src="https://user-images.githubusercontent.com/44058187/69859562-5877d500-12cf-11ea-9f28-ad9ce538369d.png" alt="drawing" width="50">
- ##### IceTower
  <img src="https://user-images.githubusercontent.com/44058187/69859586-6594c400-12cf-11ea-9348-b5e28d42a020.png" alt="drawing" width="50">
- ##### LaserTower
  <img src="https://user-images.githubusercontent.com/44058187/69859661-88bf7380-12cf-11ea-9131-7f6b0ed797ec.png" alt="drawing" width="50">

#### GUI Indication for Tower Attacking Monster
When a tower attacks a monster, the background image of the grid the monster is located at will include the following images (except for Laser Tower).

- ##### Basic Tower - GUI 
  <img src="https://user-images.githubusercontent.com/44058187/69860905-287e0100-12d2-11ea-8fa4-3258a5fa93b6.png" alt="drawing" width="80" height="80">
- ##### Ice Tower - GUI
  <img src="https://user-images.githubusercontent.com/44058187/69861275-06d14980-12d3-11ea-882f-52c4347ee6ec.png" width="50">
- ##### Catapult - GUI
  <img src="https://user-images.githubusercontent.com/44058187/69861329-236d8180-12d3-11ea-9bc4-9fae56961ff2.png" width="50">
- ##### Laser Tower - GUI
  <span style="color: red;">Red Line indicating laser beam</span>
  <img src="https://user-images.githubusercontent.com/44058187/69861632-b73f4d80-12d3-11ea-98a2-483f00e817ca.png" width="150">

#### Things to note regarding user interface:
1. The information of Towers and Monsters is using the **Tooltip** component so it will take few seconds for the information to pop up when hovering the mouse cursor over the monster or tower.


<hr>

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
