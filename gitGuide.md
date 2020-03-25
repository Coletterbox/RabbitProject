# Guide to using Git/GitHub and (hopefully) not breaking anything!

## Please let me know if anything is still confusing, or if this sequence of events isn't the most helpful.

![](https://media.giphy.com/media/kswZdQQQV7pAc/giphy.gif)

1. Open Command Prompt.
2. Navigate to where you want your project to be.
3. Clone the existing repository.
  ```
  git clone https://github.com/Coletterbox/RabbitProject.git
  ```
4. CD into the project folder.
  ```
  cd RabbitProject
  ```
5. If you want to see the local branches:
  ```
  git branch
  ```
&nbsp;&nbsp;&nbsp;&nbsp;Or if you want to see all branches, including remote:
  ```
  git branch -a
  ```
6. If the branch you want to work on already exists, switch to it.
  ```
  git checkout [branch name]
  ```
&nbsp;&nbsp;&nbsp;&nbsp;Or create a new branch and switch to it.
  ```
  git checkout -b [branch name]
  ```
7. Work on your code! :)
8. Check the status if you want to see which files have been changed.
  ```
  git status
  ```
9. Add the files you've changed to the staging area.
  ```
  git add [file name]
  ```
10. Commit your changes (don't forget to add a commit message)!
  ```
  git commit -m "[commit message]"
  ```
11. If you forget to add a commit message, you can exit Vim by typing ":wq" at the bottom and pressing enter.
12. Push your changes.
  ```
  git push origin [branch name]
  ```
  
![](https://media.giphy.com/media/47D5dzXraWsldmlx9F/giphy.gif)
