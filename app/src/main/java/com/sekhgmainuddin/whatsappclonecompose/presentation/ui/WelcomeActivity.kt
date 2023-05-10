package com.sekhgmainuddin.whatsappclonecompose.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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

enum class WelcomeScreen {
    welcomeScreen,
    phoneNumberVerfication,
    updateProfileInfo
}

class WelcomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val phoneCodeAndCountryList = getCountryAndCodes()
        val countryList = phoneCodeAndCountryList.values.toList()
        val phoneCodes = phoneCodeAndCountryList.keys.toList()

        setContent {

            var welcomeState by remember {
                mutableStateOf(WelcomeScreen.welcomeScreen)
            }

            when (welcomeState) {
                WelcomeScreen.welcomeScreen -> {
                    WelcomeScreenCompose(
                        onAgreeButtonClicked = {
                            welcomeState = WelcomeScreen.phoneNumberVerfication
                        },
                    )
                }
                WelcomeScreen.phoneNumberVerfication -> {
                    PhoneNumberVerifyCompose(countryList, phoneCodes) {
                        welcomeState = WelcomeScreen.updateProfileInfo
                    }
                }
                else -> {
                    UpdateProfileInfoCompose{
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
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


@Composable
fun PhoneNumberVerifyCompose(
    countryList: List<String>,
    countryCodeList: List<Int>,
    onVerificationComplete: () -> Unit
) {

    var phoneNumber by remember { mutableStateOf("") }
    var countrySelection by remember { mutableStateOf(false) }
    var countryCodeSelection by remember { mutableStateOf(false) }
    var selectedCountry by remember { mutableStateOf("United States") }
    var selectedCountryCode by remember { mutableStateOf(1) }
    var isOTPSent by remember { mutableStateOf(false) }
    var OTP by remember { mutableStateOf("") }

    val icon = if (countrySelection)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.ArrowDropDown

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = whatsAppBackground)
            .padding(20.dp)
            .padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
                        countrySelection = !countrySelection
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier.padding(vertical = 5.dp),
                    text = selectedCountry, style = TextStyle(
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
                expanded = countrySelection,
                onDismissRequest = { countrySelection = false },
            ) {
                Box(modifier = Modifier.size(width = 250.dp, height = 250.dp)) {
                    LazyColumn {
                        items(countryList) { item ->
                            DropdownMenuItem(text = {
                                Text(text = item)
                            }, onClick = {
                                selectedCountry = item
                                countrySelection = false
                            })
                        }
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .width(80.dp)
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
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .clickable {

                        }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { countryCodeSelection = !countryCodeSelection },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            modifier = Modifier.size(15.dp),
                            imageVector = Icons.Default.Add,
                            contentDescription = "Country Code",
                            tint = secondaryTextColor
                        )
                        Text(
                            text = selectedCountryCode.toString(),
                            style = TextStyle(color = lightSilver, fontSize = 15.sp)
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    DropdownMenu(
                        modifier = Modifier
                            .height(250.dp)
                            .width(80.dp),
                        expanded = countryCodeSelection,
                        onDismissRequest = { countryCodeSelection = false },
                    ) {
                        Box(modifier = Modifier.size(width = 250.dp, height = 250.dp)) {
                            LazyColumn {
                                items(countryCodeList) { item ->
                                    DropdownMenuItem(text = {
                                        Text(text = item.toString())
                                    }, onClick = {
                                        selectedCountryCode = item
                                        countryCodeSelection = false
                                    })
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
            BasicTextField(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .weight(1f)
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
                    .padding(bottom = 5.dp),
                value = phoneNumber,
                onValueChange = {
                    phoneNumber = it
                },
                textStyle = TextStyle(color = lightSilver, fontSize = 15.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                cursorBrush = SolidColor(lightSilver)
            )

        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Carrier SMS charges may apply", style = TextStyle(color = secondaryTextColor))
        Spacer(modifier = Modifier.height(10.dp))
        if (isOTPSent) {
            BasicTextField(
                modifier = Modifier
                    .background(color = Color.Transparent)
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
                    .padding(bottom = 5.dp),
                value = OTP,
                onValueChange = {
                    OTP = it
                },
                textStyle = TextStyle(
                    color = lightSilver,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                cursorBrush = SolidColor(lightSilver)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor,
                contentColor = Color.Black
            ), onClick = {
                if (isOTPSent)
                    onVerificationComplete()
                isOTPSent = true
            }, shape = RectangleShape
        ) {
            Text(text = if (isOTPSent) "CONTINUE" else "NEXT")
        }
    }
}

@Composable
fun UpdateProfileInfoCompose(
    onUpdateComplete: () -> Unit
) {

    var userName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(whatsAppBackground)
            .padding(horizontal = 20.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile Information",
            style = TextStyle(color = Color.White, fontSize = 18.sp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Please provide your Name and Profile Picture (Optional)",
            style = TextStyle(color = secondaryTextColor)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .border(4.dp, primaryColor, CircleShape),
            painter = painterResource(id = R.drawable.default_user_icon),
            contentDescription = "Default User Profile Pic",
        )
        Spacer(modifier = Modifier.height(30.dp))
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 30.dp)
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
                .padding(bottom = 5.dp),
            value = userName,
            onValueChange = {
                userName = it
            },
            textStyle = TextStyle(
                fontSize = 16.sp,
                color = Color.White
            ),
            cursorBrush = SolidColor(primaryColor),
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onUpdateComplete,
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryColor,
                contentColor = Color.Black
            ),
            shape = RectangleShape
        ) {
            Text(text = "NEXT")
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