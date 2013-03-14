/**
    This is the ClockSolver.java file. This program finds out all of the times in a twelve
    hour perioid (12:00AM-12:00PM) when the hour hand and the minute hand are a certain
    number of degrees from each other. When ran, this program takes in two command-line
    arguments: a degree (ranging from 0-359) and a time slice (an amount of seconds) like
    this:
    <br><br>
    <code>java ClockSolver 180 1</code>
    <br><br>
    This program creates a new Clock ojbect and begins to "tick" the clock with our given
    time slice. If the user would provide 500 for the time slice, each time the clock would
    tick by 3 minutes. A time slice of .01 is acceptable. Note: the smaller the time slice,
    the more accurate the approximation is, but the longer it takes! At each tick of the Clock
    object, we check to see if the angle is within a certain amount of degrees from the angle
    we want. We can find this room for "error" with the getTimeSliceAngle() method. Also, what
    if the time slice is so large that the approximation is too far? Don't fret! ClockSolver
    will print out the closest approximation.
 */

public class ClockSolver {
    
    public static void main (String args[]) {
        Clock clock = Clock.newClock();
        int angle = Integer.parseInt(args[0]);
        double timeSlice = Double.parseDouble(args[1]);
        degreeFinder(clock, angle, timeSlice);
    }

    /**
        Returns a double that lets degreeFinder() approximate when to print out a time.
    */
    
    public static double getTimeSliceAngle (double timeSlice) {
        double timeSliceAngle = (((timeSlice / 60) * 6) -
                                ((((timeSlice / 60) * 6) / 12))) / 2;
        System.out.println(timeSliceAngle);
        return timeSliceAngle;
    }

    /**
        This method "ticks" through a twelve hour period incrementing the Clock object by our
        time slice checking at each incrementation for our passed angle.
    */
    
    public static void degreeFinder (Clock clock, int angle, double timeSlice) {       
        double timeSliceAngle = getTimeSliceAngle(timeSlice);

        if (angle < 0 || angle > 359) {
            throw new IllegalArgumentException("The angle must be int the following range: 0-359.");
        }

        System.out.println("Given a time slice of " + timeSlice + ", the minute and hour hand are at a " + angle + " degree angle at approximately these times:");

        for (double i = 0; i < 43200; i += timeSlice) {
            clock.tick(timeSlice);

            if ((clock.getAngle() < (angle + timeSliceAngle)) && 
                (clock.getAngle() > (angle - timeSliceAngle))) {
                System.out.println(clock.toString());
            }
        }
    }  

}