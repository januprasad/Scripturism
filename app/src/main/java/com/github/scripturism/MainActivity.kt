package com.github.scripturism

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.scripturism.ui.theme.ScripturismTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScripturismTheme {
                Surface(
                    color = Color(0xFF101010),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        App(
                            totalTime = 5L * 1000L,
                            buttonWidth = 200.dp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun App(
    totalTime: Long,
    buttonWidth: Dp,
    modifier: Modifier = Modifier
) {
    // create variable for current time
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    // create variable for timeState
    var timerState by remember {
        mutableStateOf<TimerState>(TimerState.Stop)
    }

    val buttonText by remember(timerState) {
        mutableStateOf(timerState.label)
    }

    val buttonColor by remember(timerState) {
        mutableStateOf(timerState.color)
    }

    LaunchedEffect(key1 = timerState, key2 = currentTime) {
        if (currentTime > 0 && timerState == TimerState.Run) {
//            delay(100L)
//            currentTime -= 100L
            delay(1000L)
            currentTime -= 1000L
        } else if (currentTime == 0L && timerState == TimerState.Run) {
            timerState = TimerState.Stop
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        // create button to start or stop the timer
        Button(
            onClick = {
                if (currentTime <= 0L) {
                    currentTime = totalTime
                }
                if (timerState == TimerState.Stop) {
                    timerState = TimerState.Run
                } else if (timerState == TimerState.Run) {
                    timerState = TimerState.Pause
                } else if (timerState == TimerState.Pause) {
                    timerState = TimerState.Run
                }
            },
            modifier = Modifier
                .width(buttonWidth)
                .align(Alignment.BottomCenter),
            // change button color
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonColor
            )
        ) {
            Text(
                // change the text of button based on values
                text = buttonText
            )
            Spacer(Modifier.width(50.0.dp))
            Text(
                text = (currentTime / 1000L).toString(),
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
