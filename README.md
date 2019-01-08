# MKS21X-FinalProject
APCS Project ;) : Calculator


Instructions:




Development Log:
1/3/2019
- Worked on Fraction class, mostly done with all the methods in there; also added extra methods needed. (Peihua)
- Worked on the foundation for the Graph class: a graph with axis can be made with specified or non specified bounds. (Jawwad)

1/4/2019
- Finished the Fraction class and started working on Monomial. Also did some debugging on the Fraction and Monomial classes after all the methods were written. Started the Polynomial class by writing the constructor, add(Monomial), subtract(Monomial), and solveQuad methods (Peihua)
- Added the four operation methods (add/subtract/divide/multiply), mutator methods, boolean checks for operations, and documented the methods. (Jawwad)

- Complication#1: Adding/Subtracting a monomial to another monomial without the same base and exponent causes the creation of a polynomial, but polynomial has not yet been defined (created). So for now, we won't touch it but we need to figure this out. (Jawwad)
- Complication#2: Multiplication can lead to monomials with more than one variable so eventually we WILL have to allow monomials to be multivariable and update our methods as such. (Jawad0)

1/6/2019
- Added multiply and divide polynomial by a monomial. Also wrote add and subtract polynomial from polynomial. (Peihua)

1/7/2019
- Added sub method for polynomial and cleaned up monomial's toString (added helpers) to avoid showing 1 as a coefficient or a degree. (Jawwad)
- Got four function operations to work in calculator class. (Peihua)
- Added round [helper] method that rounds a fraction to nearest int, and with the aid of another helper, *we can now graph any equation!* (Jawwad)
