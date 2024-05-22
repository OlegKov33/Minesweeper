# Minesweeper
Simple minesweeper game with 3 game modes and an ability to load the game saved

Instruction to play minesweeper yourself:
1) Install Visual Studio Code, NetBeans, IntelliJ IDEA or some other software that can run java projects.
2) Install the extensions in VSC for java on left side bar fifth icon down or press (Ctrl+Shift+X) together
3) Setup JavaFX, using this guide [here](https://openjfx.io/openjfx-docs/)
4) Copy the files in this guthub repository, follow this guide [here](https://www.geeksforgeeks.org/how-to-clone-a-project-from-github-using-vscode/)
5) Run the App.java or press F5


Instructions on how to navigate the game:
Once you load the game you will see this window:
![image](https://github.com/OlegKov33/Minesweeper/assets/91954137/d50699c2-b02c-4a62-aa44-998d063dec7b)

In here you can choose to play or quit. 
Upon pressing Quit you will close the game.
Upon pressing Play you will will be directed to another window that looks like this:
![imageStart](https://github.com/OlegKov33/Minesweeper/assets/91954137/1998bdc6-de9b-4845-9a97-bdf971fe0b7a)

Once you are on second page, you have an option to select difficulty by pressing __(Settings)__ followed by one of the 3 difficulties. After that press __(Play)__. In the example below you can see beginner difficulty being selected:
![image](https://github.com/OlegKov33/Minesweeper/assets/91954137/8dc453c1-8adf-43b2-931f-cbf41bdd8b67)

After you have started, you can save the game by pressing __(Save)__ button, resulting in code being created:
![image](https://github.com/OlegKov33/Minesweeper/assets/91954137/f0b460d3-d951-425f-80ac-72379cecdd0c)

Notes and comments:
- The game allows you to create a field with one of the 3 sizes (8x8), (16x16), (30x16) with (10), (40), (90) mines respectively.
- You can win in any of the game modes __but__ you can only load __beginner__ and __intermediate__ reliably as expert doesn't load as expected.
- The game was tested in Visual Studio Code, with JavaFX runtime 13, where 21 is the latest version, windows 10, in addition 4:3 monitor was used.
- If you wish to save the game __(Right Click)__ on the text, select __"Select All"__ option and left click, __(Right Click)__ on the text again, select __"Copy"__ and press left click. After that save the code where ever you like as the game will only generate it, but __not__ save it.
- Upon restarting the project, the code gets erased from the clipboard (when you copy code, it is stored on your clipboard)
