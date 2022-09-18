package com.github.scripturism

import androidx.compose.ui.graphics.Color

sealed class TimerState(val label: String, val color: Color) {
    object Stop : TimerState("Start", Color(0xFF00FF00))
    object Run : TimerState("Pause", Color(0xFFFF0000))
    object Pause : TimerState("Resume", Color(0xFF888888))
}
