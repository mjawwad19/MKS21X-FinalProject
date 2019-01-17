# MKS21X-FinalProject
APCS Project ;) : Calculator


**Instructions:**
- java Calculator
- type a mode to work with (displayed after typing java Calculator)
  - modes are the types of functions you can do on our Calculator.
- use help if in trouble for examples on how to use each mode
  - note, the separation is based by *tabbing* instead of generic every white space
- use exit to escape the program, and exit mode to choose another mode.
  - note, some modes allow the saving of the previous answer to keep working with. When you exit a mode, this is lost!

**Development Log:**
1/3/2019
- Worked on Fraction class, mostly done with all the methods in there; also added extra methods needed. (Peihua)
- Worked on the foundation for the Graph class: a graph with axis can be made with specified or non specified bounds. (Jawwad)

1/4/2019
- Finished the Fraction class and started working on Monomial. Also did some debugging on the Fraction and Monomial classes after all the methods were written. Started the Polynomial class by writing the constructor, add(Monomial), subtract(Monomial), and solveQuad methods (Peihua)
- Added the four operation methods (add/subtract/divide/multiply), mutator methods, boolean checks for operations, and documented the methods. (Jawwad)

- Complication#1: Adding/Subtracting a monomial to another monomial without the same base and exponent causes the creation of a polynomial, but polynomial has not yet been defined (created). So for now, we won't touch it but we need to figure this out. (Jawwad)
- Complication#2: Multiplication can lead to monomials with more than one variable so eventually we WILL have to allow monomials to be multivariable and update our methods as such. (Jawwad)

1/6/2019
- Added multiply and divide polynomial by a monomial. Also wrote add and subtract polynomial from polynomial. (Peihua)

1/7/2019
- Added sub method for polynomial and cleaned up monomial's toString (added helpers) to avoid showing 1 as a coefficient or a degree. (Jawwad)
- Got four function operations to work in calculator class. (Peihua)
- Added round [helper] method that rounds a fraction to nearest int, and with the aid of another helper, *we can now graph any equation!* (Jawwad)

1/8/2019
- Wrote parseMono and parsePoly in the respective classes to convert a String into either a Monomial or Polynomial (this will be helpful later on after implementing solve given the value of the variable or for solving quadratics). Also added a constructor in Fraction that takes in a double, making it easier to parse expressions with decimal coefficients. Also fixed some small bugs and errors in various methods. (Peihua)
- Made graphing background/ background in general white, and equations being graphed red. (Jawwad)

1/9/2019
- Wrote a mean and median (if time section stuff) in calculator class and a Polynomial on Polynomial multiplier. Found an error in polynomial subtract that modifies the polynomial being subtracted as well as the one being subtracted from, when it should only modify the polynomial that is calling the method. (Jawwad)
- Finished implementing quadratic solving in Calculator class. Also went around fixing bugs found along the way. There's one bug that still needs to be worked on. (Peihua)

1/10/2019
- After EXTENSIVE debugging of practically everything, and isolating that no matter what I do to other like other.add, it will also update the polynomial that is subtracting other. HENCE, they must be linked. The solution: do operations on a copy of other, not other itself, thus breaking that link. *(actually now that I say it it sounds really simple)* HOLY FRIGGIN GOD THE BUG IS OVER. (literally 2 hours both of us and 3 hours today) (Jawwad)
- Added trig functions into PEMDAS evaluations and added foil method to allow foiling. (Conversion of binomials to be multiplied) (Peihua)

1/11/2019
- Added the functionality of solving linear equations that have one variable. Also changed the foil method and remained it factor method. Now it can do polynomial multiplied by polynomial instead of just binomial x binomial. (Peihua)

1/12/2019
- Added other fxns to driver main so user can call them/ streamlined factor method to be used for multiply/add/subtract: 2 factor Methods: one for poly poly and one for mono mono operations; and if they don't they are given all their possible options (Jawwad) (This is between 1/11 and 1/12) (Jawwad)
- Updated Monomial methods (a little cleanup here and there, some updates to toString(s)) etc. (Jawwad)
- Wrote power method for Polynomial class (Peihua)

1/13/2019
- Went around all of classes debugging and testing the functionality of the Calculator (making sure that the right Exceptions are thrown and the right messages are printed). Also went around trying to simplify code and made the Calculator easy to use. Also added comments to the classes that don't already have them. (Peihua)
- Ditto (Jawwad), and catching each other's mistakes/ guide typos.
- Updated comments to work with java docs (Jawwad)

1/14/2019
- Worked on implementing an option for the users to use the previous answer in their new PEMDAS expression to be solved. Also used Scanner class on PEMDAS to allow for an interactive terminals where the user does not have to call java Calculator every time. Updates are still not merged with master branch because we still have to convert the other functionalities of the Calculator. (Peihua)
- Updated Prototype for Friday (Jawwad)

1/15/2019
- Finished implementing all the modes of the calculator into the new main. Will work on making help and error messages more detailed later. (Peihua)

1/16/2019
- Added List functionality to graphs, though negatives < -9 regardless of how the conditions are placed will shift an extra 1. Fixed this by changing the List table string layout from horizontal to vertical. (methods List + ALToString). Updated calculator to match/played around with new modes to make sure they work. Updated instructions(Jawwad)
- Started debugging the new interface. Fixed a bug in PEMDAS where entering an invalid argument broke the code. Will continue working on PEMDAS to provide better help messages. (Peihua)
