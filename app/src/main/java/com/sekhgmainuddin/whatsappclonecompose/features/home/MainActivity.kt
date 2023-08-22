package com.sekhgmainuddin.whatsappclonecompose.features.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sekhgmainuddin.whatsappclonecompose.ui.theme.whatsAppBackground
import com.sekhgmainuddin.whatsappclonecompose.ui.theme.WhatsAppCloneUsingJetpackComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                content = {
                    Row(
                        Modifier
                            .fillMaxSize()
                            .padding(it)
                    ) {
                        Text(text = "Hello World")
                    }
                }
            )
        }
    }
}

@Composable
fun TabLayoutCompose() {
    
}

