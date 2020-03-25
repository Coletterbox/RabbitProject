# Guide to using Git/GitHub and (hopefully) not breaking anything!

## Please let me know if anything is still confusing, or if this sequence of events isn't the most helpful.

![This is me and you.](https://media.giphy.com/media/kswZdQQQV7pAc/giphy.gif)

----

### Contents
#### [Section 1: A brief walkthrough of a sequence of events](#section-1-a-brief-walkthrough-of-a-sequence-of-events)
#### [Section 2: Help! :'( (And FAQ)](#section-2-help--and-faq-1)
#### [Section 3: Common problems people have encountered on this project so far](#section-3-common-problems-people-have-encountered-on-this-project-so-far-1)

----

### Section 1: A brief walkthrough of a sequence of events
#### From cloning the repo to pushing changes...

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
&nbsp;&nbsp;&nbsp;&nbsp;If you forget to add a commit message, you can exit Vim by typing ":wq" at the bottom and pressing enter.

11. Push your changes.
  ```
  git push origin [branch name]
  ```
  
![Final Space was only okay.](https://media.giphy.com/media/47D5dzXraWsldmlx9F/giphy.gif)

----

### Section 2: Help! :'( (And FAQ)

* "It's saying I've changed files that I haven't."
  * Try "git diff" to see the changes. That will shed some light on whether it's just your linter (if you have one), or if maybe you didn't merge some changes that you thought you did, etc.
  ```
  git diff
  ```
  * To see changes in files you've already staged:
  ```
  git diff --staged
  ```
* "Why does it keep saying I've changed RabbitProject.iml?"
  * I don't know.
* "Why can't I see the branches I expect to see?"
  * You may be looking at local branches specifically. This is the command to also see remote branches:
  ```
  git branch -a
  ```
  * You may have an old version of the repo that doesn't have the branch. Pull the most recent version.
  ```
  git pull
  ```
* "What if I have changes that I don't want to commit, but I want a clean working tree?"
  * You can stash changes, and an upside of this is that it doesn't actually delete them.
  ```
  git stash
  ```
* "I accidentally staged a file that I didn't mean to."
  * To remove a file from the staging area:
  ```
  git reset [file name]
  ```
* "Where did \[person\] go?!"
  * They're probably peeing, but try sending another message in all caps this time.

![It's okay, friend. <3](https://media.giphy.com/media/gl8ymnpv4Sqha/giphy.gif)

----

### Section 3: Common problems people have encountered on this project so far
#### This is kind of like the above section, but more specific to our experiences...

* Sometimes it's really easy to miss that the command line is telling you that it has run into a problem. The text will be calm and white, but it will (for example) be telling you that the merge you just attempted actually failed, so when you next start the process of adding and committing, it will tell you that a whole bunch of files have changed, and you won't know why. That's why we have to be super careful about reading what the terminal says! :)
