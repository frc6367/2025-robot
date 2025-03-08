package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj.DigitalInput;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// package edu.wpi.first.wpilibj.examples.elevatorsimulation.subsystems;


import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkLowLevel.MotorType;


public class Elevator {
  SparkMax elevator;
  RelativeEncoder elevatorEncoder;
  private DigitalInput elevatorLimit = new DigitalInput(0);

    public Elevator(){
      this.elevator= new SparkMax(9,MotorType.kBrushless);
      

      SparkMaxConfig config = new SparkMaxConfig();
      config.idleMode(IdleMode.kBrake);
      this.elevator.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters); 
 
      this.elevatorEncoder=this.elevator.getEncoder();
    }

  public void sensorReadings(){
      SmartDashboard.putBoolean("magnetic sensor", this.elevatorLimit.get());
    }

  public void putReadings(){
    SmartDashboard.putNumber("elevatorEncoder", this.elevatorEncoder.getPosition());
  }

    public void elevatorDown(){
      elevator.set(0.7);
      if(!elevatorLimit.get()){
          stopElevator();
      }
      
  }

  public void elevatorUp(){
    if(this.elevatorEncoder.getPosition()>-215){
      elevator.set(-0.7);
    }
    else{
      stopElevator();
    }

      

  }
  
  public void stopElevator(){
      this.elevator.set(0);
      this.elevator.stopMotor();

  }




}
