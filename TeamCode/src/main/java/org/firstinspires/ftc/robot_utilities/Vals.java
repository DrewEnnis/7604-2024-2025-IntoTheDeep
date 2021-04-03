package org.firstinspires.ftc.robot_utilities;

import com.acmerobotics.dashboard.config.Config;

@Config
public class Vals {
    public static double flywheel_kp = 0.053; //40
    public static double flywheel_ki = 0.3;
    public static double flywheel_kd = 0.007;
    public static double flywheel_ks = 0;
    public static double flywheel_kv = 0.03;
    public static double flywheel_ff = 0.00827;
    public static double flywheel_tolerance = 1;
    public static double flywheel_speed = 1200;
    public static double flywheel_powershot_speed = 850;
    public static double flywheel_direction = -1;
    public static int flywheel_ready_ticks = 5;
    public static int flywheel_ready_min_speed = 1110;
    public static int flywheel_ready_max_speed = 1200;
    public static double flywheel_filtered_speed = 0;
    public static int flywheel_max_achievable_ticks = 1360;

    public static double rotate_kp = .015;
    public static double rotate_ki = .11;
    public static double rotate_kd = .0003;
    public static double rotate_tolerance = 1;
    public static double rotate_target = 0;

    public static double drive_kp = .1;
    public static double drive_ki = .11;
    public static double drive_kd = .0003;
    public static double drive_tolerance = 1;
    public static double drive_target_x = 45;
    public static double drive_target_y = -65;

    public static final double POSITION_PRE_MOVEMENT = 5;

    public static final double TICKS_PER_INCH_MOVEMENT = 40.58;

    public static double hitter_start = 0.4;
    public static double hitter_end = .7;

    public static double wobble_arm_down_velocity = -0.25;
    public static double wobble_arm_up_velocity = 0.5;
    public static double wobble_hand_open = 1.0;
    public static double wobble_hand_close = 0;

    // Wobble Arm Values
    public static double initialMotorPosition = 0.0;
    public static double initialServoPosition = 0.0;
    public static double servoOpenUp = 1.0;
    public static double servoCloseUp = 0.0;
    public static double motorMoveUp = 0.565;
    public static double motorMoveDown = -0.565;
    public static double motorReturnToDefault = 0.0;
}
