import java.*;

public class PolynomeDense{
    private int[] coefficient ;
    private int degree;


    public PolynomeDense(){
        this.coefficient = new int[1];
        this.degree= 0;
        this.coefficient[0]=0;
    }
    /*
     * @requires coeff!=0
     * 
     */
    public PolynomeDense(int coeff , int exp){
        this.coefficient = new int[exp+1];
        for(int i=0;i<exp;i++){
            if(i==exp) coefficient[i]=coeff;
            else coefficient[i]
        }
    }

    public  PolynomeDense additioner ( PolynomeDense P){
        PolynomeDense res = new PolynomeDense();
        int nd = Math.max(P.getDegree(), this.getDegree());
        res.coefficients = new int [nd + 1];
        for(int i = 0, i<= nd; i++){
            int s = this.getcoefficient(i) + P.getcoefficient(i);
            if(s!=0){
                res.degree = i;
                res.coefficient[i] = s;
            }
            return res;
        }
    }

    public double evaluer(double x){
        double 
    }

}