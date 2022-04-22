package com.example.kwork2;

public class Trigon {
    double a,b,c;
    double alpha, beta, gamma;
    double area;
    double medianA, medianB, medianC;
    double bigRadius;
    double smallRadius;


    public Trigon (double a, double b, double c, double alpha_r, double beta_r, double gamma_r)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.alpha = alpha_r;
        this.beta = beta_r;
        this.gamma = gamma_r;

    }
    //рассчет медиан
    public void median_calculation()
    {
        this.medianA = 0.5*Math.sqrt(2*this.c*this.c+2*this.b*this.b-this.a*this.a);
        this.medianB = 0.5*Math.sqrt(2*this.a*this.a+2*this.c*this.c-this.b*this.b);
        this.medianC = 0.5*Math.sqrt(2*this.a*this.a+2*this.b*this.b-this.c*this.c);
    }
    //расчет радиусов
    public void radius_calculation()
    {
        double p = (this.a+this.b+this.c)/2;
        this.smallRadius = this.area/p;
        this.bigRadius = (this.a*this.b*this.c)/(4*this.area);
    }
    public void area_calculation()//расчет площади
    {
        double p = (this.a+this.b+this.c)/2;

        this.area = Math.sqrt(p*(p-this.a)*(p-this.b)*(p-this.c));
    }
}
