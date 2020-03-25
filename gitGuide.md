# Guide to using Git/GitHub and (hopefully) not breaking anything!

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
5. Switch to the branch you want to work on. (See below for details.)
  ```
  git checkout [branch name]
  ```
6. Work on your code! :)
7. Check the status if you want to see which files have been changed.
  ```
  git status
  ```
8. Add the files you've changed to the staging area.
  ```
  git add [file name]
  ```
9. Commit your changes (don't forget to add a commit message)!
  ```
  git commit -m "[commit message]"
  ```
10. If you forget to add a commit message, you can exit Vim by typing ":wq" at the bottom and pressing enter.
11. Push your changes.
  ```
  git push origin [branch name]
  ```
  
![](https://media.giphy.com/media/47D5dzXraWsldmlx9F/giphy.gif)

* If you want to see the local branches:
  ```
  git branch
  ```
* If you want to see all branches, including remote:
  ```
  git branch -a
  ```
