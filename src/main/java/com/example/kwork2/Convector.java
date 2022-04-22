package com.example.kwork2;

public class Convector {
    //нахождение сторон и углов треугольника по 2 сторонам и углу
    public static Trigon computeTrigon1(double a, double b, double c, double alpha, double beta, double gamma) {
        //если у нас неизвестана сторона А
        if (a == -1) {
            return findA(a, b, c, alpha, beta, gamma);
        }
        //если у нас неизвестана сторона B
        if (b == -1) {
            return findB(a, b, c, alpha, beta, gamma);
        }
        //если у нас неизвестана сторона C
        if (c == -1) {
            return findC(a, b, c, alpha, beta, gamma);
        }
        return new Trigon(a, b, c, alpha, beta, gamma);
    }
    //нахождение сторон и углов треугольника по 3 сторонам
    public static Trigon computeTrigon2(double a, double b, double c, double alpha, double beta, double gamma) {
        //если у нас неизвестен угол alpha
        if (alpha == 0)
        {
            alpha = Math.acos((a*a + c*c - b*b)/(2*a*c));

        }
        //если у нас неизвестен угол beta
        if(beta == 0)
        {
            beta = Math.acos((b*b + c*c - a*a)/(2*b*c));
        }
        //если у нас неизвестен угол gamma
        if(gamma == 0)
        {
            gamma = Math.acos((a*a + b*b -c*c)/(2*a*b));
        }
        return new Trigon(a, b, c, alpha, beta, gamma);
    }
    //нахождение сторон и углов треугольника по 2 углам и стороне
    public static Trigon computeTrigon3(double a ,double b,double c,double alpha,double beta,double gamma) {
        //если у нас неизвестен угол alpha
        if(alpha == 0)
        {
            alpha = Math.toRadians(180)-beta-gamma;
        }
        //если у нас неизвестен угол beta
        if(beta == 0)
        {
            beta = Math.toRadians(180)-alpha-gamma;
        }
        //если у нас неизвестен угол gamma
        if(gamma == 0)
        {
            gamma = Math.toRadians(180)-alpha-beta;
        }
        //находим любую вторую сторону и сводим все к первой формуле
        if(a != -1)
        {
            b = a*Math.sin(alpha)/Math.sin(beta);
            return computeTrigon1(a, b, c, alpha, beta, gamma);
        }
        if(b != -1)
        {
            a = b*Math.sin(beta)/Math.sin(alpha);
            return computeTrigon1(a, b, c, alpha, beta, gamma);
        }
        if(c != -1)
        {
            a = c*Math.sin(beta)/Math.sin(gamma);
            return computeTrigon1(a, b, c, alpha, beta, gamma);
        }
        return computeTrigon1(a, b, c, alpha, beta, gamma);
    }
    public static Trigon findA(double a, double b, double c, double alpha, double beta, double gamma) {
        if (alpha != 0) {
            if (gamma == 0.0)
                gamma = Math.asin(Math.sin(alpha) * c / b);
            if (beta == 0.0)
                beta = Math.toRadians(180) - gamma - alpha;
            a = Math.sin(beta) * b / Math.sin(alpha);
            return new Trigon(a, b, c, alpha, beta, gamma);
        }
        if (beta != 0) {
            a = Math.sqrt(b * b + c * c - 2 * b * c * Math.cos(beta));
            if (gamma == 0.0)
                gamma = Math.asin(Math.sin(beta) * c / a);
            if (alpha == 0.0)
                alpha = Math.asin(Math.sin(beta) * b / a);
            return new Trigon(a, b, c, alpha, beta, gamma);
        }
        if (gamma != 0) {
            if (alpha == 0.0)
                alpha = Math.asin(Math.sin(gamma) * b / c);
            if (beta == 0.0)
                beta = Math.toRadians(180) - gamma - alpha;
            a = Math.sin(beta) * b / Math.sin(alpha);
            return new Trigon(a, b, c, alpha, beta, gamma);
        }
        return new Trigon(a, b, c, alpha, beta, gamma);
    }

    public static Trigon findB(double a, double b, double c, double alpha, double beta, double gamma)
    {
        if (alpha != 0) {
            b = Math.sqrt(a * a + c * c - 2 * a * c * Math.cos(alpha));
            if (gamma == 0.0)
                gamma = Math.asin(Math.sin(alpha) * c / b);
            if (beta == 0.0)
                beta = Math.asin(Math.sin(alpha) * a / b);
            return new Trigon(a, b, c, alpha, beta, gamma);
        }
        if (beta != 0) {
            if (gamma == 0.0)
                gamma = Math.asin(Math.sin(beta) * c / a);
            if (alpha == 0.0)
                alpha = Math.toRadians(180) - gamma - beta;
            b = Math.sin(alpha) / Math.sin(beta) * a;
            return new Trigon(a, b, c, alpha, beta, gamma);
        }
        if (gamma != 0) {
            if (beta == 0.0)
                beta = Math.asin(Math.sin(gamma) * a / c);
            if (alpha == 0.0)
                alpha = Math.toRadians(180) - gamma - beta;
            b = Math.sin(alpha) / Math.sin(beta) * a;
            return new Trigon(a, b, c, alpha, beta, gamma);
        }
        return new Trigon(a, b, c, alpha, beta, gamma);
    }
    public static Trigon findC(double a, double b, double c, double alpha, double beta, double gamma)
    {
        if (alpha != 0) {
            if (beta == 0.0)
                beta = Math.asin(Math.sin(alpha) * a / b);
            if (gamma == 0.0)
                gamma = Math.toRadians(180) - beta - alpha;
            c = Math.sin(gamma) / Math.sin(beta) * a;
            return new Trigon(a, b, c, alpha, beta, gamma);
        }
        if (beta != 0) {
            if (alpha == 0.0)
                alpha = Math.asin(Math.sin(beta) * b / a);
            if (gamma == 0.0)
                gamma = Math.toRadians(180) - alpha - beta;
            c = Math.sin(gamma) / Math.sin(beta) * a;
            return new Trigon(a, b, c, alpha, beta, gamma);
        }
        if (gamma != 0) {
            c = Math.sqrt(a * a + b * b - 2 * a * b * Math.cos(gamma));
            if (alpha == 0.0)
                alpha = Math.asin(Math.sin(gamma) * c / b);
            if (beta == 0.0)
                beta = Math.toRadians(180) - gamma - alpha;
            return new Trigon(a, b, c, alpha, beta, gamma);
        }
        return new Trigon(a, b, c, alpha, beta, gamma);
    }
}
