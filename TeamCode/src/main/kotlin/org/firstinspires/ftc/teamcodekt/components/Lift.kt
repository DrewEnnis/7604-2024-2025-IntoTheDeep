package org.firstinspires.ftc.teamcodekt.components

import com.acmerobotics.dashboard.config.Config
import com.arcrobotics.ftclib.controller.PIDController
import com.arcrobotics.ftclib.controller.PIDFController
import com.arcrobotics.ftclib.hardware.motors.Motor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import ftc.rouge.blacksmith.util.kt.clamp
import ftc.rouge.blacksmith.util.kt.invoke
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcodekt.util.DataSupplier

@Config
object LiftConfig {
    @JvmField var P = 0.0195
    @JvmField var I = 0.0
    @JvmField var D = 0.0002

    @JvmField var ZERO = 0
    @JvmField var LOW  = 737
    @JvmField var MID  = 1170
    @JvmField var HIGH = 1600
    
    @JvmField var MANUAL_ADJUSTMENT_MULTI = 50.0
}

class Lift(hwMap: HardwareMap, private val voltageScaler: VoltageScaler) {
    private val liftMotor: DcMotorSimple

    val liftEncoder: Motor
    private val liftPID: PIDFController

    var targetHeight = 0

    var height: Int
        get() = targetHeight
        set(height) {
            targetHeight = height.clamp(LiftConfig.ZERO, LiftConfig.HIGH)
        }

    init {
        liftMotor = hwMap("LI")

        liftEncoder = Motor(hwMap, "FR")
        liftEncoder.resetEncoder()

        liftPID = PIDController(LiftConfig.P, LiftConfig.I, LiftConfig.D)
    }

    fun goToZero() {
        targetHeight = LiftConfig.ZERO
    }

    fun goToLow() {
        targetHeight = LiftConfig.LOW
    }

    fun goToMid() {
        targetHeight = LiftConfig.MID
    }

    fun goToHigh() {
        targetHeight = LiftConfig.HIGH
    }

    fun update(telemetry: Telemetry) {
        val voltageCorrection = voltageScaler.voltageCorrection

        val correction =
            liftPID.calculate(-liftEncoder.currentPosition.toDouble(), targetHeight.toDouble())

        telemetry.addData("Corection", correction)

        liftMotor.power = correction
    }

    fun logData(telemetry: Telemetry, dataSupplier: DataSupplier<Lift>) {
        telemetry.addData("Lift motor", dataSupplier(this))
    }
}
