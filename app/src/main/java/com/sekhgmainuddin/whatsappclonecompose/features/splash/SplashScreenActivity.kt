package com.sekhgmainuddin.whatsappclonecompose.features.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sekhgmainuddin.whatsappclonecompose.R
import com.sekhgmainuddin.whatsappclonecompose.presentation.ui.WelcomeActivity
import com.sekhgmainuddin.whatsappclonecompose.ui.theme.whatsAppBackground
import com.sekhgmainuddin.whatsappclonecompose.ui.theme.secondaryTextColor
import kotlinx.coroutines.delay


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = whatsAppBackground) {
                var splashTime by remember {
                    mutableStateOf(false)
                }
                if (splashTime) {
                    startActivity(Intent(this@SplashScreenActivity, WelcomeActivity::class.java))
                    finish()
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Surface(modifier = Modifier.weight(1f), color = Color.Transparent) {
                        Image(
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp),
                            painter = painterResource(id = R.drawable.whatsapp_icon),
                            contentDescription = "Splash Screen Icon",
                            colorFilter = ColorFilter.tint(color = Color.White),
                        )
                    }
                    Text(
                        text = "from",
                        style = TextStyle(
                            color = secondaryTextColor,
                            fontSize = 16.sp
                        ),
                    )
                    Row(
                        modifier = Modifier.wrapContentSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .width(25.dp)
                                .height(25.dp),
                            painter = painterResource(id = R.drawable.meta_icon),
                            contentDescription = "Meta Icon",
                            colorFilter = ColorFilter.tint(color = Color.White)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Meta",
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                fontSize = 22.sp
                            ),
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                LaunchedEffect(key1 = Unit) {
                    delay(1000)
                    splashTime = true
                }
            }
        }
    }
}
