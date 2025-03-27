
package frc.robot;
public class Autonomous {
	private final int TICK_MS = 20;

	private DriveTrain dt;
	private CoralEffector ce; 
	private Elevator el; 

	private int time = 0;
	private int timeInState = 0;
	private int autoState = 0; 


	public Autonomous(DriveTrain dt, CoralEffector ce, Elevator el ) {
		this.dt = dt;
		this.ce = ce; 
		this.el = el; 

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


		// if(autoState == 0){
		// 	// 	this.ce.takeAlge();
		// 	// 	if (this.timeInState >=1000){
		// 	// 		autoState++;
		// 	// 		timeInState=0;
		// 	// 		this.ce.stop(); 
		// 	// 	}
		// 	// }
	}

	public void centerTroughAuto(){
		incrementTime(); 

		if (autoState== 0){
			//What we want to do in state
			this.dt.driveForward(0.4);
			
			//CONDITION TO MOVE ON
				if (this.time >= 1400){
					//preparing for next state
					autoState++;
					timeInState = 0; 

					//Cleaning up after this state
					this.dt.setcoast(); 
					this.dt.stop();
				
				}
			}

		else if(autoState== 1){
			this.dt.driveForward(0.2);
			if (timeInState > 1200){
				autoState++;
				timeInState = 0; 
				this.dt.stop();

			}

		}
		
		else if(autoState == 2){
			this.ce.shootAngleL();
			if (this.timeInState >= 1000){
				autoState++;
				timeInState = 0; 
				this.ce.stop();
			}
		}
		// else if(autoState == 3){
		// 	this.ce.takeAlge();
		// 	if (this.timeInState >=1000){
		// 		autoState++;
		// 		timeInState=0;
		// 		this.ce.stop(); 
		// 	}
		// }

		else if(autoState== 3){
			this.dt.driveForward(-0.2);
			if (timeInState > 500){
				autoState++;
				timeInState = 0; 
				this.dt.stop();

			}

		}

	}


	public void takeAlgeAuto(){
		// incrementTime();

		this.centerTroughAuto();

		if(autoState ==5){
			this.el.elevatorUp();
			this.ce.takeAlge();
			if(this.timeInState >=250){
				autoState++;
				timeInState = 0; 
				this.el.stopElevator();
				this.ce.stopAlge();
			}
		}
		if(autoState ==6){
			this.ce.takeAlge();
			if(this.timeInState>=1000){
				autoState++;
				timeInState = 0;
				this.ce.stopAlge();
			}
		}

	}
	public void sideTroughAuto(){
		incrementTime(); 

		if (autoState== 0){
			this.dt.driveForward(0.4);
				if (this.time >= 3000){
					autoState++;
					timeInState = 0; 
					this.dt.setcoast(); 
					this.dt.stop();
				
				}
			}

		else if(autoState== 1){
			this.dt.driveForward(0.2);
			if (timeInState > 1200){
				autoState++;
				timeInState = 0; 
				this.dt.stop();

			}

		}
		
		else if(autoState == 2){
			this.ce.shootAngleL();
			if (this.timeInState >= 1000){
				autoState++;
				timeInState = 0; 
				this.ce.stop();
			}
		}
		else if(autoState == 3){
			this.ce.takeAlge();
			if (this.timeInState >=1000){
				autoState++;
				timeInState=0;
				this.ce.stop(); 
			}
		}

		else if(autoState== 4){
			this.dt.driveForward(-0.2);
			if (timeInState > 500){
				autoState++;
				timeInState = 0; 
				this.dt.stop();

			}

		}


	}
	public void centerL3Auto(){
		incrementTime(); 

		if (autoState== 0){
			this.dt.driveForward(0.4);
				if (this.time >= 1400){
					autoState++;
					timeInState = 0; 
					this.dt.setcoast(); 
					this.dt.stop();
				
				}
			}

		else if(autoState== 1){
			this.dt.driveForward(0.2);
			if (timeInState > 1200){
				autoState++;
				timeInState = 0; 
				this.dt.stop();

			}

		}
		
		else if(autoState == 2){
			this.dt.strafeRight(0.2);
			if (this.timeInState >= 800){
				autoState++;
				timeInState = 0; 
				this.dt.stop();
			}
		}

		else if(autoState == 3){
			if (this.el.elevatorUp()&& timeInState <3600){
				autoState++;
				timeInState = 0; 
				this.el.stopElevator();
			}
		}

		else if(autoState == 4){
			this.ce.shootAngleL();
			if (this.timeInState >= 1000){
				autoState++;
				timeInState = 0; 
				this.ce.stop();
			}
		}
	}



}

