/**
    This is the Clock.java file. It contains the Clock class. Each Clock object 
    has three member variables: hours, minutes, and seconds. The hours, minutes,
    and seconds member variables are self-explanatory. The Clock also has getter
    methods to figure out its minute degrees, hour degrees, and its angle between
    those two. The minute degrees and the hours degrees are the amount of degrees
    from the "starting point" of the hands: 12 o'clock.
  
    The Clock class only has getters since this problem is tailored to starting a
    clock at 12:00AM (00:00:00) and iterating through up until 12:00PM (12:00:00).
    Each new Clock instance is initialized at 12:00AM.
  
    Unit tests are at the end of the file.
 */

public class Clock {
        
    private double hours;
    private double minutes;
    private double seconds;
    
    /**
        Creates a Clock object with time 12:00AM (00:00:00).
    */

    private Clock () {
        this.hours = 0.0;
        this.minutes = 0.0;
        this.seconds = 0.0;
    }

    /**
        Here is a static conctructor for a Clock object.
    */
    
    public static Clock newClock () {
        return new Clock ();
    }

    /**
        Returns the seconds of a Clock object.
    */
    
    public double getSeconds () {
        return this.seconds;
    }

    /**
        Returns the minutes of a Clock object.
    */
    
    public double getMinutes () {
        return this.minutes;
    }
    
    /**
        Returns the hours of a Clock object.
    */

    public double getHours () {
        return this.hours;
    }

    /**
        Returns the minute hand degrees from the 12 of a Clock object.
    */
    
    public double getMinuteDegrees () {
        double minuteDegrees = ((this.getMinutes() + (this.getSeconds() / 60)) * 6);
        return minuteDegrees;
    }

    /**
        Returns the hour hand degrees from the 12 of a Clock object.
    */
    
    public double getHourDegrees () {
        double hourDegrees = (this.getMinuteDegrees() / 12) + (this.getHours() * 30);
        return hourDegrees;
    }
        
    /**
        Returns the degrees between the minute and hour hand. It compares the two
        to see which one is larger then subtracts the smaller one from the larger one.
        It then checks to see if the angle is larger than 360, and if it is, the method
        keeps on subtracting 360 until our angle is less than 360.
    */

    public double getAngle () {
        double angle = this.getMinuteDegrees() > this.getHourDegrees() ?
                   this.getMinuteDegrees() - this.getHourDegrees() :
                   this.getHourDegrees() - this.getMinuteDegrees();

        while (angle >= 360) { angle -= 360;}
        return angle;
    }

    /**
        "Ticks" the Clock by the passed amount of seconds (timeSlice).
    */

    public void tick (double timeSlice) {
        if ((timeSlice < 0) || (timeSlice > 1800)) {
            throw new IllegalArgumentException("The maximum time slice allowed is 30 minutes (1800 seconds).");
        }

        this.seconds += timeSlice;

        while (this.seconds >= 60) {
            this.minutes += 1;
            this.seconds = this.seconds - 60;
        }
        while (this.minutes >= 60) {
            this.hours += 1;
            this.minutes = this.minutes - 60;
        }   
        while (this.hours > 12) {
            this.hours = this.hours - 12;
        }
    }

    /**
        Returns a stringy representaion of a given Clock object.
    */
        
    public String toString () {    
        String h = Integer.toString((int)this.getHours());
        String m = Integer.toString((int)this.getMinutes());
        String s = Double.toString(this.getSeconds());
        
        if (this.getHours() < 10) {
            h = "0" + h;
        } else if (this.getHours() == 0) { 
            h = "12";
        }
        
        if (this.getMinutes() < 10) { 
            m = "0" + m; 
        }
        
        if (this.getSeconds() < 10) {
            s = "0" + s;
        }
            
        String timePrint = h + ":" + m + ":" + s;
        return timePrint;
    }

    /**
        Unit tests.
    */
    
    public static void main (String args[]) {
        toStringTest();
        newClockTest();
        tickTest();
        getSecondsTest();
        getMinutesTest();
        getHoursTest();
        getMinuteDegreesTest();
        getHourDegreesTest();
        getAngleTest();   
    }
    
    static void toStringTest () {
        String newclock = "12:00:00.0";
        assert newclock.equals(Clock.newClock().toString());
    }
    
    static void newClockTest () {
        Object clock = Clock.newClock();
        String newclock = "12:00:00.0";
        assert clock instanceof Clock;
        assert newclock.equals(clock.toString());
    }

    static void tickTest () {
        Clock clock = Clock.newClock();
        clock.tick(1800);
        assert "12:30:00.0".equals(clock.toString());
        clock.tick(1800);
        assert "01:00:00.0".equals(clock.toString());
        clock.tick(900);
        assert "01:15:00.0".equals(clock.toString());
        clock.tick(920);
        assert "01:30:20.0".equals(clock.toString());
        clock.tick(300);
        assert "01:35:20.0".equals(clock.toString());
        clock.tick(1);
        assert "01:35:21.0".equals(clock.toString());
        clock.tick(.01);
        assert "01:35:21.01".equals(clock.toString());
        clock.tick(38.99);
        assert "01:36:00.0".equals(clock.toString());
    }
    
    static void getSecondsTest () {
        Clock clock = Clock.newClock();
        assert 0.0 == clock.getSeconds();
        clock.tick(1);
        assert 1.0 == clock.getSeconds();
        clock.tick(40);
        assert 41.0 == clock.getSeconds();
        clock.tick(18.1);
        assert 59.1 == clock.getSeconds();
        clock.tick(.9);
        assert 0.0 == clock.getSeconds();
    }
    
    static void getMinutesTest () {
        Clock clock = Clock.newClock();
        assert 0.0 == clock.getMinutes();
        clock.tick(60);
        assert 1.0 == clock.getMinutes();
        clock.tick(600);
        assert 11.0 == clock.getMinutes();
        clock.tick(1800);
        assert 41.0 == clock.getMinutes();
        clock.tick(1140);
        assert 0.0 == clock.getMinutes();
    }
    
    static void getHoursTest () {
        Clock clock = Clock.newClock();
        assert 0.0 == clock.getHours();
        clock.tick(1800);
        clock.tick(1800);
        assert 1.0 == clock.getHours();
        clock.tick(900);
        clock.tick(900);
        clock.tick(900);
        assert 1.0 == clock.getHours();
        clock.tick(900);
        assert 2.0 == clock.getHours();
    }
    
    static void getMinuteDegreesTest () {
        Clock clock = Clock.newClock();
        clock.tick(600);
        assert 60 == clock.getMinuteDegrees();
        clock.tick(300);
        assert 90 == clock.getMinuteDegrees();
        clock.tick(150);
        assert 105 == clock.getMinuteDegrees();
        clock.tick(75);
        assert 112.5 == clock.getMinuteDegrees();
        clock.tick(37.5);
        assert 116.25 == clock.getMinuteDegrees();
        clock.tick(18.75);
        assert 118.125 == clock.getMinuteDegrees();
    }
    
    static void getHourDegreesTest () {
        Clock clock = Clock.newClock();
        clock.tick(600);
        assert 5 == clock.getHourDegrees();
        clock.tick(300);
        assert 7.5 == clock.getHourDegrees();
        clock.tick(150);
        assert 8.75 == clock.getHourDegrees();
        clock.tick(75);
        assert 9.375 == clock.getHourDegrees();
        clock.tick(37.5);
        assert 9.6875 == clock.getHourDegrees();
        clock.tick(18.75);
        assert 9.84375 == clock.getHourDegrees();   
    }
    
    static void getAngleTest () {
        Clock clock = Clock.newClock();
        clock.tick(600);
        assert 55 == clock.getAngle();
        clock.tick(300);
        assert 82.5 == clock.getAngle();
        clock.tick(150);
        assert 96.25 == clock.getAngle();
        clock.tick(75);
        assert 103.125 == clock.getAngle();
        clock.tick(37.5);
        assert 106.5625 == clock.getAngle();
        clock.tick(18.75);
        assert 108.28125 == clock.getAngle();
    }
    
}