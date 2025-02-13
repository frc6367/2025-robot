package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Intake {
    TalonSRX arm;
    TalonSRX roller;

    public Intake(){
        this.arm=new TalonSRX(0);
        this.roller=new TalonSRX(0); 
    }
    
    public void stop(){
        this.arm.set(TalonSRXControlMode.PercentOutput,0);
        this.roller.set(TalonSRXControlMode.PercentOutput,0);
    }
    
}
