public class Monomial{
  public void setCoef(int co) {
    coef = co;
  }

  public void setVar(char v) {
    var = v;
  }

  public void setDeg(int deg) {
    degree = deg;
  }

  public boolean likeTerms(Monomial other) {
    if (getVar() == other.getVar()) return true;
    return false;
  }

  public boolean likeDeg(Monomial other) {
    if (getDeg() == other.getDeg()) return true;
    return false;
  }
  /*Below is a WIP for add(Monomial) because of certain issues
  1) if you add two monomials together and they aren't compatible, then they
  would create a Polynomial.
  In that case shouldn't this be moved to the Polynomial class?
  2) A proposed solution to this: Make this add a boolean that
  modifies the Monomial calling the function if possible */
  public Polynomial add(Monomial other) {
    Polynomial end = new Polynomial();
    Monomial out = (0, x, 0); //default
    if (likeTerms && likeDeg) {
      out.setCoef(getCoef() + other.getCoef());
      out.setVar(getVar());
      out.setDeg(getDeg() + other.getDeg());
      end.add(out);
    }
    else {
      end.add(this);
      end.add(other);
    }
  }
}
