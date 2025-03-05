### Elevator stages
- The limit switch may be ready by the end of the day today. 
`private DigitalInput elevatorLimit = new DigitalInput(/* ROBO_RIO_DIO PORT NUMBER */)` // port is dgital zero 
this field in the elevator will let us read the value. Review the javadoc for the class: `edu.wpi.first.wpilibj.DigitalInput` to find out how to read the boolean value output.
Remember, we can almost always find javadocs by googling the full package path: `import edu.wpi.first.wpilibj.DigitalInput;`


- Try and tie one of the D-Pad buttons, or even the left or right stick click-ins to going to the correct height for L2 coral placement. Note that this will require a function capable of driving up when the elevator is below a set point, and down when it is above a set point. Be careful not to get things backwards! The function you write should use the up and down we wrote with limitations, just to be safe!

### Autonomous 
- Outline a "drive forward and spit" autonomous
- Outline a "drive forward and turn and drive forward and spit" autonomous

```
class Autonomous {

	private final int TICK_MS = 20;

	private DriveTrain dt;
	private Elevator el;
	private AHRX navX;
	private CoralEffector ce;
	//...

	private int autoState = 0;
	private int timeMS = 0;

	public Autonomous(DriveTrain dt, Elevator el, CoralEffector ce) {
		this.dt = dt;
		this.el = el;
		//...
	}

	private int incrementTime() {
		this.timeMS += this.TICK_MS;
	}

	private void stopAll() {
		// Run stop command for everything to hold steady in complete state
	}

	public void centerTroughAuto() {
		//Update passage of time for autonomous
		this.incrementTime();
		int stateTime = 0;
		

		if (autoState == 0) {
			//Drive forward
			if (/* distance threshold met */) {
				// TODO: Stop driving

				// Reset stateTime, so it now represents MS since we changed states
				stateTime = 0;
				this.autoState++;
			}
		} else if (autoState == 1) {
			//Deploy coral
			if (/* A second or so has passed */) {
				//Stop deploying coral;
				stateTime = 0;
				this.autoState++;
			}
		} else {
			this.stopAll();
		}
	}


	/*
	 * Autonomous 2, this is for us to try and score the trough when we
	 * start from either left or right position.
	 */
	public void sideTroughAuto(char direction) {
		this.incrementTime();
		int stateTime = 0;

		/* 
		 * This is sort of like an if statement, but more limited.
		 * It's condition is always checking the variable inside of
		 * switch(var) -> a, against "cases", or possible values of var.
		 * E.g. switch(var) { 
		 *         case 3: 
		 *            /* code goes here */ 
		 *            break;
		 *      }
		 *  is equivalent to an if statement:
		 *  if (var == 3) {
		 *     /* code goes here */
		 *  }
		switch(this.autoState) {
			case 0:
				// Drive forward (p1)
				if (/* distance threshold met */) {
					stateTime = 0;
					this.autoState++;
				}
				/* This keyword break is needed, it's the equivalent
				 * to closing the curly brace for this specific case.
				break;
			case 1:
				// Turn left or right, depending on the direction char
				if (/* turned far enough */) {
					stateTime = 0;
					this.autoState++;
				}
				break;
			case 2:
				// Looks a lot like drive forward p1
				break;
			case 3:
				// Deploy coral
				if (/* a second has passed */) {
					this.autoState++;
				}
				break;
			/* 
			 * Instead of an else, we have default, which covers all
			 * values of var that are not specified in other cases.
			 */
			default:
				this.stopAll();
		}
	}

} 
```


Invoking these autonomous's is then done from autonomous Periodic, where we read the selection of what auto to run, and then determine what to call from autonomous accordingly. 


There are still some missing pieces here.

Two main ones:
1) How should we control the movement of the robot?
2) How do we know how far we've gone / how far we've turned

### Problem 1: movement control
The first one is fairly easy. While we moved the teleop controls to some axis math, there's nothing stopping us from reading tank-drive style functions (`driveSraight()`, `turn()`) like we initially wrote, that are only capable of controlling the drive motors a specific way. If we add these to the DriveTrain subsystem, we're under no obligation to use them in teleop, where we already have a different, math-based movement strategy. All we really need to do is re-add the commands, and we're set!

### Problem 2: Telemetry
Telemetry is a fancy word for "data about movement". Our NavX, an AHRS gives us two main things:
- a set of 3-gyroscope readings (Where we're facing)
- an accelerometer reading (or how fast we're instantaneously accelerating)


**Attitude and Heading**
The gyroscope reading is easy. The only one of the 3 measurements we can get that we need to use for autonomous is `yaw` which can be accessed from the AHRX method `getYaw()`

For example in Robot.java we can do the following:

``` 
public class Robot {

	private AHRS navX = new AHRS(NavXComType.kMXP_SPI);


	public void robotPeriodic {
		SmartDashboard.putNumber("robotHeading", gyro.getYaw());
	}

}
```

*Note these examples are incomplete snippets. In our code, our robot periodic function is not empty, we would have to put the lines listed here alongside other code that already exists in robotPeriodic. We don't have to re-add any function declarations, since they exist, we just need to add example code from the bodies of these functions.

We can try that out and record what readings are like. In addition, we may want to zero the yaw when the robot reboots, so that our heading is being reset every time, and not carrying over from one instance to the next.

```
	robotInit() {
		navX.zeroYaw();
	}
```

Here is the Kauai labs Java docs for everything the NavX board can do! https://www.kauailabs.com/public_files/navx-mxp/apidocs/java/com/kauailabs/navx/frc/AHRS.html


**Acceleration**
If we want to know how far we've gone, we need to keep track of our velocity over time. This is easy enough due to the cyclical nature of the WPILibrary, and the fact we know every autonomous will start off with an acceleration and velocity of zero.

We're going to use two basic physics equations to track our movement:

`Distance = Rate * Time` in order to track this over time, we'll be continuously recalculating this equation: `currentDistance = previousDistance + (Rate * Time)`

However the navX gives us the acceleration, so at any given time rate (or velocity, as we will refer to it) is unknown to us. Rate itself becomes parameterized by this function:
`Rate = Acceleration * Time` or rather, constantly recalculating this:
`currentVelocity = previousVelocity + (Acceleration * Time)`

At the very start of a match, our robot is stationary on the field. In other words, its velocity (rate) and its acceleration are both 0. As soon as the robot starts moving, it will be using the code we write to track its velocity and distance traveled!

For example, if I want to track how far I have moved forward, I can use the following logic:

```
public class Robot {
	private double yVelocity = 0.0;
	private double yDistance = 0.0;

	// This is roughly the amount of time that 
	// has passed between calculations in seconds
	private static final double TIME_PASSED = 0.02;

	public void robotPeriodic {
		// This updates our current velocity
		double yAcceleration = navX.getWorldLinearAccelY();
		yVelocity = yVelocity + (yAcceleration * TIME_PASSED);
		yDistance = yDistance + (yVelocity * TIME_PASSED);

		SmartDashboard.putNumber('yAcceleration', yAcceleration);
		SmartDashboard.putNumber('yVelocity', yVelocity);
		SmartDashboard.putNumber('yDistance', yDistance);
	}

}
```

Now we have a yDistance and yVelocity value, and we are outputting them to smart dashboard. Drag them from the list of values on the left, somewhere onto the screen, and drive the robot around and see how the values change. 

**How does the value change when you drive in a straight line?**
**How about when you drive perpendicular, either right or left?**
**If you turn the robot, does that value behave differently?**
**What happens if you drive forward, then come back to where you started from?**

We could just as easily do these calculations in Autonomous.java, provided we make sure that wherever we update our velocity and distance is called every cycle, either in robotPeriodic or in autonomousPeriodic.

**2D Acceleration**
Hopefully you've noticed by now that the values we're reading doesn't treat all movement the same. That's because the acceleration we're reading is parameterized. Our robot essentially moves in two-dimensions, forward/back and left/right, but we're only currently reading one of those dimensions. Physics calculations generally take a look at multi-dimensional problems in components.

The function name: `getWorldLinearAccelY()` gives us a hint about what component we've been talking about in the previous example. The Y-component!

Since we're interested in 2 dimensions of movement, it's useful for us to track both the Y-component, and the X-component.

To have access to an X value... we simply need another set of calculations and variables!

**Task: Go ahead and modify the previous example so that we also have an X-acceleration, x velocity and x distance, and output them to SmartDashboard, the same as the Y values**

**Run through the previous list of motions, and see if you start to think about the motion differently**





**Frame of reference**

Ultimately if all our autonomous is wanting to track is driving straight-forward, we only really need one of the components. This is due to a concept called "frame of reference." Our components are based on the way the robot is oriented. Whichever component changes when we drive straight ahead is still the component that changes if we turn fully to our left, then drive straight ahead, or even turn all the way around and come back the way we came.

To us, a robot driving straight ahead for four feet, turning 90 degrees, and then driving straight ahead another 4 ft, we expect a Y-component and an X-component of 4 ft. But in reality, the robot perceived itself moving straight ahead for all 8 feet of the motion!

The navX board on the robot is measuring the direction of acceleration *relative* to itself. It doesn't really care which way is North, East, West, or which side of the field the driver station is on. It knows when it's accelerating one way or the other.

