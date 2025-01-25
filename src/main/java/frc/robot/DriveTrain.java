package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;

public class DriveTrain{
    TalonFX backleft;
    TalonFX backright;
    TalonFX frontleft;
    TalonFX frontright;
    
    public DriveTrain(){
        this.backleft=new TalonFX(3);
        this.backright=new TalonFX(1);
        this.frontleft=new TalonFX(2);
        this.frontright=new TalonFX(0);
    }

    public void driveForward(){
        backleft.set(0.5);
        frontleft.set(0.5);
        backright.set(-0.5);
        frontright.set(-0.5);
    }

    public void stop(){
        backleft.set(0);
        frontleft.set(0);
        backright.set(0);
        frontright.set(0);
    }
    
    public void driveBack(){
        backleft.set(-0.5);
        frontleft.set(-0.5);
        backright.set(0.5);
        frontright.set(0.5);
    }


}