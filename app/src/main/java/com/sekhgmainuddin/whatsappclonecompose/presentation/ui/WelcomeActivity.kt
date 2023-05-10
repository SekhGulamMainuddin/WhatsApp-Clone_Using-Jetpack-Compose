package com.sekhgmainuddin.whatsappclonecompose.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.sekhgmainuddin.whatsappclonecompose.R
import com.sekhgmainuddin.whatsappclonecompose.ui.theme.highLightedText
import com.sekhgmainuddin.whatsappclonecompose.ui.theme.lightSilver
import com.sekhgmainuddin.whatsappclonecompose.ui.theme.primaryColor
import com.sekhgmainuddin.whatsappclonecompose.ui.theme.secondaryTextColor
import com.sekhgmainuddin.whatsappclonecompose.ui.theme.whatsAppBackground
import java.util.Locale


class WelcomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val phoneCodeAndCountryList = getCountryAndCodes()
        val countryList = phoneCodeAndCountryList.values.toList()
        val phoneCodes = phoneCodeAndCountryList.keys.toList()
        setContent {

            var agreeAndContinue by remember {
                mutableStateOf(true)
            }

            if (agreeAndContinue) {
                PhoneNumberVerifyCompose(countryList)
            } else {
                WelcomeScreenCompose(
                    onAgreeButtonClicked = {
                        agreeAndContinue = true
                    },
                )
            }
        }

    }

}

@Composable
fun WelcomeScreenCompose(
    onAgreeButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = whatsAppBackground)
            .padding(20.dp)
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .padding(top = 20.dp),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to WhatsApp",
                    style = TextStyle(
                        fontSize = 28.sp,
                        color = lightSilver,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Surface(modifier = Modifier.weight(1f), color = Color.Transparent) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(50.dp),
                        painter = painterResource(id = R.drawable.welcome_background_image),
                        contentDescription = "Welcome Background Image",
                    )
                }
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = secondaryTextColor)) {
                            append("Read our ")
                        }
                        withStyle(style = SpanStyle(color = highLightedText)) {
                            append("Privacy Policy")
                        }
                        withStyle(style = SpanStyle(color = secondaryTextColor)) {
                            append(". Tap \"Agree and continue\" to accept the ")
                        }
                        withStyle(style = SpanStyle(color = highLightedText)) {
                            append("Terms of Service")
                        }
                        withStyle(style = SpanStyle(color = secondaryTextColor)) {
                            append(".")
                        }
                    },
                    style = TextStyle(
                        textAlign = TextAlign.Center
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = onAgreeButtonClicked,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor,
                        contentColor = Color.Black
                    ),
                    shape = RectangleShape,
                ) {
                    Text(
                        text = "AGREE AND CONTINUE",
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "from", style = TextStyle(
                textAlign = TextAlign.Center,
                color = secondaryTextColor
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "FACEBOOK", style = TextStyle(
                textAlign = TextAlign.Center,
                color = lightSilver,
                fontFamily = FontFamily.SansSerif,
                letterSpacing = 2.sp,
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberVerifyCompose(countryList: List<String>) {

    var countryCode by remember {
        mutableStateOf(1)
    }
    var mExpanded by remember { mutableStateOf(false) }
    var mSelectedText by remember { mutableStateOf("United States") }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.ArrowDropDown

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = whatsAppBackground)
            .padding(20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Enter your phone number", style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = lightSilver
                )
            )
            Icon(
                imageVector = Icons.Default.MoreVert,
                tint = secondaryTextColor,
                contentDescription = "Menu Icon"
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            modifier = Modifier.padding(horizontal = 10.dp), text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = lightSilver
                    )
                ) {
                    append("WhatsApp will send an SMS message to verify your phone number. ")
                }
                withStyle(
                    style = SpanStyle(
                        color = highLightedText
                    )
                ) {
                    append("What\'s my number?")
                }
            }, style = TextStyle(
                textAlign = TextAlign.Center
            )
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 40.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .fillMaxWidth()
                    .drawBehind {

                        val strokeWidth = 2f
                        val y = size.height - strokeWidth / 2

                        drawLine(
                            primaryColor,
                            Offset(0f, y),
                            Offset(size.width, y),
                            strokeWidth
                        )
                    }
                    .clickable {
                        mExpanded = !mExpanded
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier.padding(vertical = 5.dp),
                    text = mSelectedText, style = TextStyle(
                        fontSize = 16.sp,
                        color = lightSilver
                    )
                )
                Icon(
                    imageVector = icon,
                    contentDescription = "Show/Hide Country Dropdown",
                    tint = primaryColor
                )
            }

            DropdownMenu(
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp),
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
            ) {
                Box(modifier = Modifier.size(width = 250.dp, height = 250.dp)) {
                    LazyColumn {
                        items(countryList) { item ->
                            DropdownMenuItem(text = {
                                Text(text = item)
                            }, onClick = {
                                mSelectedText = item
                                mExpanded = false
                            })
                        }
                    }
                }
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 10.dp)
        ) {
            Row(modifier = Modifier
                .width(80.dp)
                .clickable {
                }, horizontalArrangement = Arrangement.SpaceBetween) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Country Code",
                    tint = secondaryTextColor
                )
                Text(text = "1")
                Spacer(modifier = Modifier.width(10.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .background(color = Color.Black),
                value = countryCode.toString(),
                onValueChange = {
                    countryCode = it.toInt()
                })

        }
    }
}

fun getCountryAndCodes(): HashMap<Int, String> {
    val result = HashMap<Int, String>()
    for (countryCode in PhoneNumberUtil.getInstance().supportedRegions) {
        val phoneCode = PhoneNumberUtil.getInstance().getCountryCodeForRegion(countryCode)
        val displayCountry = Locale("", countryCode).displayCountry
        result[phoneCode] = displayCountry
    }
    return result
}