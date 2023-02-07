package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Robot extends TimedRobot {

  private CANSparkMax m_garra;
  private Garra claw;
  private Timer timer;
  private Limelight limelight;
  private static final int clawID = 3;
  private Joystick m_joyStick;
  private Drive drive;

  @Override
  public void robotInit() {
  /**
   * SPARK MAX controllers are intialized over CAN by constructing a CANSparkMax object
   * 
   * The CAN ID, which can be configured using the SPARK MAX Client, is passed as the
   * first parameter
   * 
   * The motor type is passed as the second parameter. Motor type can either be:
   *  com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless
   *  com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushed
   * 
   * The example below initializes four brushless motors with CAN IDs 1 and 2. Change
   * these parameters to match your setup
   */

    m_garra = new CANSparkMax(clawID, MotorType.kBrushed);
    claw = new Garra(m_garra, clawID);
    m_joyStick = new Joystick(0);
    limelight = new Limelight();
    drive = new Drive();
    timer = new Timer();
   
  }

  @Override
  public void teleopPeriodic() {
    drive.drive(m_joyStick.getZ()*0.45, m_joyStick.getY()*0.60);

  if (m_joyStick.getRawButtonPressed(6))
  {
      claw.levantarGarra();
  }
  if (m_joyStick.getRawButtonReleased(6))
  {
      claw.pararGarra();
  }

  if (m_joyStick.getRawButtonPressed(5))
  {
      claw.abaixarGarra();
  }

  if (m_joyStick.getRawButtonReleased(5))
  {
      claw.pararGarra();
  }
  
  }

  @Override
  public void autonomousInit(){
    timer.reset();
    timer.start();
  }

  @Override
  public void autonomousPeriodic(){
    
    limelight.update();
    limelight.dashboard();

    drive.drive(limelight.pid(limelight.x), -0.32);
    
  }
}