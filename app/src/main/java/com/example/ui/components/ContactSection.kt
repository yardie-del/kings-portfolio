package com.example.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.local.ContactInquiryEntity
import com.example.ui.theme.*
import com.example.ui.viewmodel.ContactFormState
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactSection(
    formState: ContactFormState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onSubjectChange: (String) -> Unit,
    onMessageChange: (String) -> Unit,
    onProjectTypeChange: (String) -> Unit,
    onBudgetChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onReset: () -> Unit,
    savedInquiriesCount: Int,
    onOpenInquiriesInbox: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val projectTypes = listOf(
        "Startup MVP / Web Application",
        "Mobile App (Flutter / Compose)",
        "Backend & REST API System",
        "AI & Automation Feature",
        "Internship / Full-time Role",
        "Technical Consultation"
    )

    var projectTypeExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        SectionHeader(
            badgeText = "Get In Touch",
            title = "Have an idea? Let's build it.",
            subtitle = "I'm always interested in interesting projects, collaborations, internships, freelance opportunities, and technology ideas that can create real-world impact."
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Direct Contact Cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SocialButton(
                icon = Icons.Default.Email,
                label = "Email Moses",
                onClick = { sendEmailIntent(context, "omindemoses98@gmail.com") },
                modifier = Modifier.weight(1f)
            )
            SocialButton(
                icon = Icons.Default.WorkOutline,
                label = "LinkedIn",
                onClick = { openExternalUrl(context, "https://linkedin.com/in/moses-ominde") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Location & Info Row
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
            border = androidx.compose.foundation.BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Place, contentDescription = null, tint = CyanAccent, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Based in Nairobi, Kenya 🇰🇪 (EAT / UTC+3)",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                if (savedInquiriesCount > 0) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                        modifier = Modifier.clickable { onOpenInquiriesInbox() }
                    ) {
                        Text(
                            text = "Inbox ($savedInquiriesCount)",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Contact Form GlassCard
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            borderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f)
        ) {
            Text(
                text = "SEND A DIRECT MESSAGE",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.2.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Name Field
            OutlinedTextField(
                value = formState.name,
                onValueChange = onNameChange,
                label = { Text("Your Name *") },
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("contact_name_input")
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Email Field
            OutlinedTextField(
                value = formState.email,
                onValueChange = onEmailChange,
                label = { Text("Your Email Address *") },
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("contact_email_input")
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Project Type Dropdown
            ExposedDropdownMenuBox(
                expanded = projectTypeExpanded,
                onExpandedChange = { projectTypeExpanded = !projectTypeExpanded }
            ) {
                OutlinedTextField(
                    value = formState.projectType,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Project / Collaboration Type") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = projectTypeExpanded) },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = projectTypeExpanded,
                    onDismissRequest = { projectTypeExpanded = false }
                ) {
                    projectTypes.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type, fontSize = 13.sp) },
                            onClick = {
                                onProjectTypeChange(type)
                                projectTypeExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Subject Field
            OutlinedTextField(
                value = formState.subject,
                onValueChange = onSubjectChange,
                label = { Text("Subject") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().testTag("contact_subject_input")
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Message Field
            OutlinedTextField(
                value = formState.message,
                onValueChange = onMessageChange,
                label = { Text("Message / Project Details *") },
                minLines = 4,
                maxLines = 6,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().testTag("contact_message_input")
            )

            // Error display
            if (formState.errorMessage != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "⚠️ ${formState.errorMessage}",
                    color = RoseTech,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Submit Button
            Button(
                onClick = onSubmit,
                enabled = !formState.isSubmitting,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color(0xFF00363D)
                ),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 48.dp)
                    .testTag("submit_contact_button")
            ) {
                if (formState.isSubmitting) {
                    CircularProgressIndicator(
                        color = Color(0xFF00363D),
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Sending Message...", fontWeight = FontWeight.Bold)
                } else {
                    Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Send Message", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
    }

    // Success Modal / Confirmation Dialog
    if (formState.isSuccess) {
        AlertDialog(
            onDismissRequest = onReset,
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldTech, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Message Sent Successfully!")
                }
            },
            text = {
                Column {
                    Text(
                        text = "Thank you, ${formState.name}! Your message has been saved to the portfolio inbox and dispatched.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Moses will review your inquiry and respond to ${formState.email} promptly.",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = onReset,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Done", color = Color(0xFF00363D), fontWeight = FontWeight.Bold)
                }
            }
        )
    }
}

@Composable
fun InquiriesInboxDialog(
    inquiries: List<ContactInquiryEntity>,
    onDeleteInquiry: (ContactInquiryEntity) -> Unit,
    onDismiss: () -> Unit
) {
    val dateFormat = remember { SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.background,
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .fillMaxHeight(0.85f)
                .padding(vertical = 12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Inbox, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Portfolio Inquiries (${inquiries.size})",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), modifier = Modifier.padding(vertical = 8.dp))

                if (inquiries.isEmpty()) {
                    Box(modifier = Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(text = "No inquiries received yet.", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(inquiries) { item ->
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                                border = androidx.compose.foundation.BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                                        IconButton(
                                            onClick = { onDeleteInquiry(item) },
                                            modifier = Modifier.size(28.dp)
                                        ) {
                                            Icon(imageVector = Icons.Default.DeleteOutline, contentDescription = "Delete", tint = RoseTech, modifier = Modifier.size(16.dp))
                                        }
                                    }

                                    Text(text = "✉️ ${item.email}", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                                    Text(text = "🏷️ ${item.projectType} • ${item.subject}", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(vertical = 2.dp))
                                    Text(text = item.message, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface, modifier = Modifier.padding(top = 4.dp))
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = dateFormat.format(Date(item.timestamp)), fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
