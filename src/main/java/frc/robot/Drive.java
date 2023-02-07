package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Timer;

public class Drive {
    private DifferentialDrive m_myRobot1;
    private DifferentialDrive m_myRobot2;
    private static final int leftDeviceID1 = 1; 
    private static final int leftDeviceID2 = 2;
    private static final int rightDeviceID1 = 4;
    private static final int rightDeviceID2 = 5;
    
    private CANSparkMax m_leftMotor1;
    private CANSparkMax m_rightMotor1;
    private CANSparkMax m_leftMotor2;
    private CANSparkMax m_rightMotor2;

    
  private Timer timer;

    public void set(){
        m_leftMotor1 = new CANSparkMax(leftDeviceID1, MotorType.kBrushless);
        m_rightMotor1 = new CANSparkMax(rightDeviceID1, MotorType.kBrushless);
        m_leftMotor2 = new CANSparkMax(leftDeviceID2, MotorType.kBrushless);
        m_rightMotor2 = new CANSparkMax(rightDeviceID2, MotorType.kBrushless);
        m_rightMotor1.setInverted(false);
        m_rightMotor2.setInverted(false);

        m_leftMotor1.restoreFactoryDefaults();
        m_rightMotor1.restoreFactoryDefaults();
        m_leftMotor2.restoreFactoryDefaults();
        m_rightMotor2.restoreFactoryDefaults();
    
        m_myRobot1 = new DifferentialDrive(m_leftMotor1, m_rightMotor1);
        m_myRobot2 = new DifferentialDrive(m_leftMotor2, m_rightMotor2);
    
    }
    
    public void drive(double Forward, double Turn){
        m_myRobot1.arcadeDrive(Forward,Turn);
        m_myRobot2.arcadeDrive(Forward,Turn);
    }

    public void empinar(){
        timer.reset();
        timer.start();

        while(timer.get()<0.2){
            m_myRobot1.arcadeDrive(0, 0.5);
            m_myRobot2.arcadeDrive(0, 0.5);
          }
          while(timer.get()>0.2 && timer.get()<0.45){
            m_myRobot1.arcadeDrive(0, -0.7);
            m_myRobot2.arcadeDrive(0, -0.7);
          }
          while(timer.get()>0.45 && timer.get()<3){
            m_myRobot1.arcadeDrive(0, -0.4);
            m_myRobot2.arcadeDrive(0, -0.4);
          }
          while(timer.get()>3 && timer.get()<7){
            m_myRobot1.arcadeDrive(0, 0);
            m_myRobot2.arcadeDrive(0, 0);
          }
    }


}
