package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.ui.viewmodel.ProjectRequestFormState

@Composable
fun BuildWithMeSection(
    formState: ProjectRequestFormState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onProjectTypeChange: (String) -> Unit,
    onBudgetChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onTimelineChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onReset: () -> Unit,
    savedInquiriesCount: Int,
    onOpenInquiriesInbox: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val projectTypes = listOf(
        "Mobile App (Android/Flutter)",
        "Full-Stack Web App",
        "Backend / REST API",
        "PropTech / Real Estate",
        "Fintech / M-Pesa Gateway",
        "Cybersecurity Audit",
        "Startup MVP (0 to 1)"
    )

    val budgetRanges = listOf(
        "KES 25,000 - 50,000 ($200 - $400)",
        "KES 50,000 - 120,000 ($400 - $950)",
        "KES 120,000 - 300,000 ($950 - $2,400)",
        "KES 300,000+ ($2,400+)",
        "Open for Discussion"
    )

    val timelines = listOf(
        "1 - 2 Weeks (Rapid Prototype)",
        "2 - 4 Weeks (MVP Release)",
        "1 - 2 Months (Full Build)",
        "Flexible / Retainer"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        SectionHeader(
            badgeText = "Client Acquisition & Collaboration",
            title = "Build With Me",
            subtitle = "Have an idea for a mobile app, web platform, or backend system? Let's turn your vision into high-quality, production-ready software."
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Main Proposal Form Card
        Surface(
            shape = RoundedCornerShape(22.dp),
            color = MaterialTheme.colorScheme.surface,
            border = androidx.compose.foundation.BorderStroke(
                1.2.dp,
                MaterialTheme.colorScheme.primary.copy(alpha = 0.45f)
            ),
            tonalElevation = 4.dp,
            modifier = Modifier.fillMaxWidth().testTag("build_with_me_card")
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                if (formState.isSuccess) {
                    // Success state
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(EmeraldTech.copy(alpha = 0.18f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = EmeraldTech,
                                modifier = Modifier.size(36.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Project Request Received!",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = "Thank you! Your project details have been recorded securely in the local database. Moses will review your requirements and respond within 24 hours.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            lineHeight = 22.sp,
                            modifier = Modifier.padding(top = 6.dp, bottom = 18.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    val waMsg = "Hi Moses, I just submitted a project request regarding *${formState.projectType}* through your portfolio app."
                                    sendWhatsAppIntent(context, "+254700000000", waMsg)
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = EmeraldTech),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(imageVector = Icons.Default.Chat, contentDescription = null, tint = Color(0xFF003314), modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Chat on WhatsApp", color = Color(0xFF003314), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }

                            OutlinedButton(
                                onClick = onReset,
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Submit Another", fontSize = 12.sp)
                            }
                        }
                    }
                } else {
                    // Form fields
                    Text(
                        text = "1. Select Project Type",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp)
                    ) {
                        items(projectTypes) { type ->
                            val isSelected = type == formState.projectType
                            FilterChip(
                                selected = isSelected,
                                onClick = { onProjectTypeChange(type) },
                                label = { Text(type, fontSize = 11.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                                    selectedLabelColor = Color(0xFF00363D)
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                    }

                    Text(
                        text = "2. Estimated Budget Range",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp)
                    ) {
                        items(budgetRanges) { budget ->
                            val isSelected = budget == formState.budgetRange
                            FilterChip(
                                selected = isSelected,
                                onClick = { onBudgetChange(budget) },
                                label = { Text(budget, fontSize = 11.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = CyanAccent,
                                    selectedLabelColor = Color(0xFF00363D)
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                    }

                    Text(
                        text = "3. Desired Timeline",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth().padding(bottom = 14.dp)
                    ) {
                        items(timelines) { time ->
                            val isSelected = time == formState.desiredTimeline
                            FilterChip(
                                selected = isSelected,
                                onClick = { onTimelineChange(time) },
                                label = { Text(time, fontSize = 11.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = IndigoAccent,
                                    selectedLabelColor = Color.White
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                        }
                    }

                    Text(
                        text = "4. Your Details & Requirements",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    OutlinedTextField(
                        value = formState.name,
                        onValueChange = onNameChange,
                        label = { Text("Your Name / Company Name *") },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp).testTag("request_name_input")
                    )

                    OutlinedTextField(
                        value = formState.email,
                        onValueChange = onEmailChange,
                        label = { Text("Email Address *") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp).testTag("request_email_input")
                    )

                    OutlinedTextField(
                        value = formState.phoneOrWhatsApp,
                        onValueChange = onPhoneChange,
                        label = { Text("Phone / WhatsApp (Optional)") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp).testTag("request_phone_input")
                    )

                    OutlinedTextField(
                        value = formState.projectDescription,
                        onValueChange = onDescriptionChange,
                        label = { Text("Project Goals, Core Features & Requirements *") },
                        minLines = 3,
                        maxLines = 6,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp).testTag("request_desc_input")
                    )

                    if (formState.errorMessage != null) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = RoseTech.copy(alpha = 0.15f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, RoseTech.copy(alpha = 0.4f)),
                            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                        ) {
                            Text(
                                text = formState.errorMessage,
                                color = RoseTech,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }

                    // Submit Button
                    Button(
                        onClick = onSubmit,
                        enabled = !formState.isSubmitting,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color(0xFF00363D)
                        ),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
                        modifier = Modifier.fillMaxWidth().heightIn(min = 48.dp).testTag("submit_request_button")
                    ) {
                        if (formState.isSubmitting) {
                            CircularProgressIndicator(
                                color = Color(0xFF00363D),
                                strokeWidth = 2.dp,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text("Recording Project Proposal...", fontWeight = FontWeight.Bold)
                        } else {
                            Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Send Project Request", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Direct Email & WhatsApp shortcuts
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedButton(
                onClick = { sendEmailIntent(context, "omindemoses98@gmail.com") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Direct Email", fontSize = 12.sp)
            }

            if (savedInquiriesCount > 0) {
                FilledTonalButton(
                    onClick = onOpenInquiriesInbox,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    ),
                    modifier = Modifier.weight(1f).testTag("open_inbox_btn")
                ) {
                    Icon(imageVector = Icons.Default.Inbox, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Inbox ($savedInquiriesCount)", fontSize = 12.sp)
                }
            }
        }
    }
}

private fun sendWhatsAppIntent(context: Context, phone: String, message: String) {
    try {
        val uri = Uri.parse("https://api.whatsapp.com/send?text=${Uri.encode(message)}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "WhatsApp not installed. Sending email instead.", Toast.LENGTH_SHORT).show()
        sendEmailIntent(context, "omindemoses98@gmail.com")
    }
}
