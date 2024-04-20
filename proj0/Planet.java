public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67e-11;


    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double rsqured = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) *(this.yyPos - p.yyPos);
        double r = Math.sqrt(rsqured);
        return r;
    }

    public double calcForceExertedBy(Planet p){
        double rsqured = calcDistance(p) * calcDistance(p);
        double F = (this.mass * p.mass * G) / rsqured;
        return F; 
    }

    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - this.xxPos;
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double Fx = (F * dx) / r;
        return Fx;
    }

    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - this.yyPos;
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        double Fy = (F * dy) / r;
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] allP){
        double FnetX = 0;
        for (Planet p : allP){
            if (this.equals(p) == false){
                FnetX += calcForceExertedByX(p); 
            }
        }
        return FnetX;
    }

    public double calcNetForceExertedByY(Planet[] allP){
        double FnetY = 0;
        for (Planet p : allP){
            if (this.equals(p) == false){
                FnetY += calcForceExertedByY(p); 
            }
        }
        return FnetY;
    }

    public void update(double dt, double fX, double fY){
        double anetx = fX / mass;
        double anety = fY / mass;
        xxVel += dt * anetx;
        yyVel += dt * anety;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}