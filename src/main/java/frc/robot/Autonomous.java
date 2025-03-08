
package frc.robot;
public class Autonomous {
	private final int TICK_MS = 20;

	private DriveTrain dt;
	private CoralEffector ce; 

	private int time = 0;
	private int timeInState = 0;
	private int autoState = 0; 


	public Autonomous(DriveTrain dt,CoralEffector ce ) {
		this.dt = dt;
		this.ce = ce; 

	}

	public void incrementTime() {
		this.time += TICK_MS;
		this.timeInState += TICK_MS;
	}
	
	public void resetTime(){
		time = 0;
	}

	public void driveStraightAuto() {
		this.incrementTime();
		if (this.time <= 850) {
			this.dt.driveForward(0.6);
		}
		else{
			this.dt.stop();
		}
	}

	public void centerTroughAuto(){
		incrementTime(); 

		if (autoState== 0){
			this.dt.driveForward(0.6);
				if (this.time >= 1000){
					autoState++;
					timeInState = 0; 
					this.dt.stop();
				}
			}
		else if(autoState == 1){
			this.ce.shootAngleL();
			if (this.timeInState >= 1000){
				autoState++;
				timeInState = 0; 
				this.ce.stop();
			}
		}
	}


}




// public class Autonomous {

// 	private final int TICK_MS = 20;

// 	private DriveTrain dt;
// 	private Elevator el;
// 	private AHRX navX;
// 	private CoralEffector ce;
// 	//...

// 	private int autoState = 0;
// 	private int timeMS = 0;

// 	public Autonomous(DriveTrain dt, Elevator el, CoralEffector ce) {
// 		this.dt = dt;
// 		this.el = el;
// 		//...
// 	}

// 	private int incrementTime() {
// 		this.timeMS += this.TICK_MS;
// 	}

// 	private void stopAll() {
// 		// Run stop command for everything to hold steady in complete state
// 	}

	// private void driveStraightAuto() {
	//	//TODO: implement a drive forward for 2 seconds slowly then wait autonomous
    //}

// 	public void centerTroughAuto() {
// 		//Update passage of time for autonomous
// 		this.incrementTime();
// 		int stateTime = 0;
		

// 		if (autoState == 0) {
// 			//Drive forward
// 			if (/* distance threshold met */) {
// 				// TODO: Stop driving

// 				// Reset stateTime, so it now represents MS since we changed states
// 				stateTime = 0;
// 				this.autoState++;
// 			}
// 		} else if (autoState == 1) {
// 			//Deploy coral
// 			if (/* A second or so has passed */) {
// 				//Stop deploying coral;
// 				stateTime = 0;
// 				this.autoState++;
// 			}
// 		} else {
// 			this.stopAll();
// 		}
// 	}
    
    
// }
