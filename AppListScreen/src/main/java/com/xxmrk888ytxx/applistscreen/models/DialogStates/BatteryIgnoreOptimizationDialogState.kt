package com.xxmrk888ytxx.applistscreen.models.DialogStates

sealed class BatteryIgnoreOptimizationDialogState() {

    object Hidden : BatteryIgnoreOptimizationDialogState()

    object Visible : BatteryIgnoreOptimizationDialogState()
}
