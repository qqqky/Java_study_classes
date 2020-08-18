# Java_study_classes
Some simple Java programs. Study material


## Please note:

• Almost all of the programs (except SHA-1 hashing implementation), were made as solutions to exercises.

**• Nearly all of the programs are written in very basic Java, avoiding most of the Java Collections library and its methods.**
	
### Unoptimized or deprecated practice:
  
• Here, all Swing programs have the frame initialized through the main method. Should be rewritten to do so from the
	Event Dispatch thread.
  
• All painting was done using the old Graphics class.

• Comments in the code do not adhere to Javadoc standards.

• There are (almost) no null checks/safety wheels like exception handling - most classes assume the provided input (where needed) is correct.

• There is excessive use and manipulations of class variables, which could and should be avoided in most cases.


Looking back at most of the first projects, there is often a lot of unnecessary code, but all programs do work.

There are a lot of self-notes and a lot of undeleted 'printlns'. I decided to keep them, maybe someone who just starts with Java will find 
them helpful. Some classes may still have theory notes left.

I also tried to include a full description of the exercise where due.
Almost all of the tasks are from book "Java Software Solutions: Foundations of Program Design". There are a few places where
authors' provided examples were implemented in order to solve a particular exercise. If so, credits are in the comments.

Having no prior background to any programming, I personally found the provided exercises after each chapter extremely useful in helping
to understand the material. I made sure to complete every single one of the programming projects there (sometimes with my own spin
on it), so if anyone is just starting out and think the solutions will be helpful, I will be glad to share.

From this repo, however, all overly simple, overly unuseful or overly boring tasks are omitted. Only included ones which either
really took some brain scratching to figure out at that time, were extremely fun to do, or ones which I thought could be somewhat
useful for somebody.

The last project which was not from the mentioned book, was implementing SHA-1 algorithm. It was implemented from scratch just out 
of pure interest on the subject while reading documentation. Implementation works correctly, but only for <57 ASCII characters.

### Here is a list of my personal favorites:

**• Swing:**		  

**BrickWallPattern, Checkerboard, PolygonBouncyCar, QuadraticFormula, HanoiTowersVisualized + all the ones with fractals (KachSnowflakeCCurve and SierpinskiTriangle).**
            
**• Non-swing:**

**CardDealer, CityRoutes, NonAttackingQueens, BinaryTree, HashingSHA1.**

> 

> 

> License extends to this whole repository.
