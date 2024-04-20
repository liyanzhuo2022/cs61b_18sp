public class NBody{

    public static double readRadius(String filename){
        In in = new In(filename);
        int first = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);

        int size = in.readInt();
        double radius = in.readDouble();

        Planet[] Planets = new Planet[size];
        for (int i = 0; i < size; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }

        return Planets;
    }

    public static void main(String[] args){
        if (args.length != 3){
            System.out.println("Please type in 3 arguments");
        }
        /** read the file*/
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] Planets = readPlanets(filename);
        /** Drawing the Background*/
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        /** Drawing the Planets*/
        for (Planet p : Planets){
            p.draw();
        }
        /** Creating an Animation*/
        StdDraw.enableDoubleBuffering();

        for (double i = 0; i <= T; i += dt){
            // create arrays
            double xForces[] = new double[Planets.length];
            double yForces[] = new double[Planets.length];
            // Calculate the net x and y forces for each planet
            int index = 0;
            for (Planet p : Planets){
                double Fnetx = p.calcNetForceExertedByX(Planets);
                double FnetY = p.calcNetForceExertedByY(Planets);
                xForces[index] = Fnetx;
                yForces[index] = FnetY;
                index += 1;
            }
            // Call update on each of the Planets
            for (int j = 0; j < Planets.length; j++){
                Planets[j].update(dt, xForces[j], yForces[j]);
            }
            // Draw the background image. Draw all of the Planets.
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : Planets){
                p.draw();
            }
            // Show the offscreen buffer. Pause the animation for 10 milliseconds.
            StdDraw.show();
            StdDraw.pause(10);

        }

        /** Printing the universe.*/
        StdOut.printf("%d\n", Planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < Planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                        Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);   
        }
        
    }
    
}