package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CoralEffector {
    
    private VictorSPX leadingMotor = new VictorSPX(5);
    private VictorSPX laggingMotor = new VictorSPX(6); 
    public AnalogInput coralSensor = new AnalogInput(0);
    private boolean sawCoral = false; 

    // make instance varible for the sensor that will be used 
    

    public boolean canSeeCoral(){
        return (coralSensor.getValue() > Constant.coralThreshold );  
    }

    public boolean cannotSeeCoral(){
        return !this.canSeeCoral();
    }


    public void setMotors(double speed1, double speed2){
        leadingMotor.set(VictorSPXControlMode.PercentOutput,speed1);
        laggingMotor.set(VictorSPXControlMode.PercentOutput,speed2);

        this.leadingMotor.setNeutralMode(NeutralMode.Brake);
        this.laggingMotor.setNeutralMode(NeutralMode.Brake);

    }

    public void setMotors(double speed){
        this.setMotors(speed,speed);
    }


    public void intake(){
        this.setMotors(0.4);

        // move at same speed
        // run to limit based on the senor 
    }

    public void takeIn(){
        if ((!sawCoral && this.cannotSeeCoral())){
            this.intake(); 
        }
        else if(canSeeCoral()){
            this.setMotors(0.25); 
            sawCoral = true; 
        }
        else{
            this.stop();
        }
    }

    public void shootAngleL( ){
        this.setMotors(Constant.effectorRightSpeed,Constant.effectorLeftLowSpeed);
        // run at differnt speeds 
    }
    public void shootAngleR(){
        this.setMotors(Constant.effectorRightSpeed,Constant.effectorLeftLowSpeed);
        // run at differnt speeds 
    }

    public void shootStraight(){
        // run at the same speed 
        this.sawCoral = false;
        this.setMotors(0.86);    
    }

    public void stop(){
        this.setMotors(0.0);
    }

    public void resetSawCoral() {
        this.sawCoral = false;
    }

    public void putReadings(){
        SmartDashboard.putNumber("coral sensor", coralSensor.getValue());
    }


    
}
