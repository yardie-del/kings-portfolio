package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.window.DialogProperties
import com.example.ui.theme.*

@Composable
fun CvViewerDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    val cvText = """
        MOSES OMINDE
        Software Developer | Full-Stack & Mobile Developer
        Nairobi, Kenya • omindemoses98@gmail.com
        GitHub: https://github.com/yardie-del • Portfolio: Kings Portfolio
        
        PROFESSIONAL SUMMARY
        Innovative and disciplined Software Developer and BSc Information Technology student with hands-on experience designing, developing, testing, deploying, and maintaining production-grade web applications, mobile apps, and secure backend architectures. Proven capability in translating authentic market needs across Africa into robust, scalable software products.
        
        CORE TECHNICAL SKILLS
        - Frontend: React.js, Next.js, HTML5, CSS3, Tailwind CSS, JavaScript (ES6+), TypeScript, Jetpack Compose
        - Backend: Python (Django, Django REST Framework, FastAPI), Node.js, Express, RESTful APIs, Webhooks
        - Mobile: Kotlin (Android SDK, Jetpack Compose, Coroutines, Flow), Flutter / Dart
        - Database & Cloud: PostgreSQL, SQLite (Room DB), Redis, Docker, AWS S3, Git/GitHub, CI/CD pipelines
        - Cybersecurity & Foundations: Linux, Network Security, OWASP Top 10, Secure Authentication (JWT, OAuth2, Credential Manager), Defensive Security Testing
        
        KEY PRODUCTION & ENGINEERING PROJECTS
        1. Kings Portfolio (Native Android App)
           - Architectural flagship mobile portfolio built with Kotlin, Jetpack Compose, MVVM Clean Architecture, Room Database, and real-time GitHub API v3 integration.
           - GitHub: https://github.com/yardie-del/kings-portfolio
        
        2. NyumbaLink (PropTech Marketplace & Property Management)
           - Full-stack residential rental platform with verified listings, digital lease signing, and automated Safaricom Daraja M-Pesa STK Push instant payments.
           - Stack: React, Tailwind CSS, Django REST Framework, PostgreSQL, Docker, Redis.
        
        3. ArchConnect KE (Architectural Blueprint Marketplace & Collaboration)
           - Digital marketplace connecting certified architects with property developers and homeowners.
           - Stack: React, Django REST Framework, PostgreSQL, AWS S3, Docker.
        
        4. Smart AgriTech KE (Farm-to-Market Supply Chain Platform)
           - Agricultural marketplace enabling smallholder farmers to sell directly to urban wholesale aggregators with USSD/SMS fallback for rural connectivity.
           - Stack: Flutter, Django REST Framework, PostgreSQL, Africa's Talking API.
        
        5. Cybersecurity Learning Lab & Defensive Sandbox
           - Interactive security analysis suite demonstrating authentication security, SQL injection defenses, password hashing (Argon2/bcrypt), and port scanning.
           - Stack: Python, Bash, Docker, OWASP Testing Standards.
        
        EDUCATION
        Bachelor of Science in Information Technology (3rd Year)
        Focus: Software Engineering, Database Systems, Network Security & Distributed Systems
        
        CERTIFICATIONS & CONTINUOUS LEARNING
        - Network Security & Defensive Best Practices
        - Full-Stack Web Development & Django REST Architectures
        - Android App Development with Kotlin & Jetpack Compose
    """.trimIndent()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.background,
            border = androidx.compose.foundation.BorderStroke(
                1.5.dp,
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            ),
            modifier = Modifier
                .fillMaxWidth(0.96f)
                .fillMaxHeight(0.92f)
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Header bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
                        ) {
                            Text(
                                text = "CURRICULUM VITAE",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.primary,
                                letterSpacing = 1.sp,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Moses Ominde • CV",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(36.dp).testTag("close_cv_dialog_btn")
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = MaterialTheme.colorScheme.onSurface)
                    }
                }

                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))

                // Scrollable CV Content
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                        // Summary card
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colorScheme.surface,
                            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Moses Ominde",
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Software Developer | Full-Stack & Mobile Developer",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = "Nairobi, Kenya • omindemoses98@gmail.com • GitHub: yardie-del",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(top = 2.dp, bottom = 10.dp)
                                )

                                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f), modifier = Modifier.padding(vertical = 6.dp))

                                Text(
                                    text = "Disciplined software developer and BSc Information Technology student with demonstrated capabilities in end-to-end software delivery—from problem formulation and architectural design to full-stack implementation, mobile development, testing, and continuous maintenance.",
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    item {
                        CvSectionHeader("CORE COMPETENCIES")
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            border = androidx.compose.foundation.BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                CvBullet("Languages", "Python, Kotlin, JavaScript, TypeScript, Dart, SQL, Bash")
                                CvBullet("Frameworks", "Django REST Framework, React.js, Jetpack Compose, Flutter, Next.js, Node.js")
                                CvBullet("Databases & Cloud", "PostgreSQL, SQLite/Room, Redis, Docker, AWS S3, Git/GitHub, CI/CD")
                                CvBullet("Security & Architecture", "Linux hardening, OWASP Top 10, REST API design, JWT/OAuth2, STK Push payments")
                            }
                        }
                    }

                    item {
                        CvSectionHeader("FEATURED SOFTWARE PROJECTS")
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            CvProjectItem(
                                title = "Kings Portfolio (Android)",
                                role = "Lead Android Architect",
                                stack = "Kotlin, Jetpack Compose, Room DB, GitHub API, MVVM",
                                points = listOf(
                                    "Engineered high-performance native portfolio app with live GitHub API synchronization, offline Room persistence, and interactive project simulators.",
                                    "Implemented M3 adaptive theming, dynamic filtering, and end-to-end test verification."
                                )
                            )
                            CvProjectItem(
                                title = "NyumbaLink (PropTech Marketplace)",
                                role = "Full-Stack Developer",
                                stack = "React, Django REST Framework, PostgreSQL, Docker, M-Pesa STK Push",
                                points = listOf(
                                    "Engineered rental platform with verified listings, digital lease signing, and automated Safaricom Daraja STK Push instant payments.",
                                    "Built relational schema optimized with Redis caching and Docker containerization."
                                )
                            )
                            CvProjectItem(
                                title = "ArchConnect KE (Architectural Marketplace)",
                                role = "Full-Stack Developer",
                                stack = "React, Django REST Framework, PostgreSQL, AWS S3",
                                points = listOf(
                                    "Created collaborative marketplace connecting licensed architects with property developers.",
                                    "Integrated AWS S3 secure high-resolution blueprint storage and escrow milestone tracking."
                                )
                            )
                            CvProjectItem(
                                title = "Smart AgriTech KE (Food Supply Platform)",
                                role = "Full-Stack & Mobile Developer",
                                stack = "Flutter, Django REST Framework, PostgreSQL, Africa's Talking SMS/USSD",
                                points = listOf(
                                    "Built farm-to-market trade platform with fair commodity pricing analytics and dual smartphone/SMS ordering fallback for rural farmers."
                                )
                            )
                        }
                    }

                    item {
                        CvSectionHeader("EDUCATION & CERTIFICATIONS")
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            border = androidx.compose.foundation.BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "Bachelor of Science in Information Technology (3rd Year)",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Coursework: Software Engineering, Data Structures & Algorithms, Network Security, Database Management Systems, Distributed Computing",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
                                )
                                Divider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f), modifier = Modifier.padding(vertical = 4.dp))
                                Text(
                                    text = "Continuous Learning: Cybersecurity Defense Labs, Django REST API Architecture, Modern Jetpack Compose Patterns",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                    val clip = ClipData.newPlainText("Moses Ominde CV", cvText)
                                    clipboard.setPrimaryClip(clip)
                                    Toast.makeText(context, "CV copied to clipboard!", Toast.LENGTH_SHORT).show()
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                                modifier = Modifier.weight(1f).testTag("copy_cv_button")
                            ) {
                                Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null, tint = Color(0xFF00363D), modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Copy CV Text", color = Color(0xFF00363D), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }

                            OutlinedButton(
                                onClick = { openExternalUrl(context, "https://github.com/yardie-del") },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(imageVector = Icons.Default.Hub, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("GitHub Profile", fontSize = 12.sp)
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun CvSectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 11.sp,
        fontWeight = FontWeight.Black,
        color = MaterialTheme.colorScheme.primary,
        letterSpacing = 1.2.sp,
        modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
    )
}

@Composable
private fun CvBullet(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "• $label: ", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
        Text(text = value, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun CvProjectItem(
    title: String,
    role: String,
    stack: String,
    points: List<String>
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(0.8.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurface)
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                ) {
                    Text(text = role, fontSize = 10.sp, fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                }
            }
            Text(text = "Tech: $stack", fontSize = 10.sp, color = CyanAccent, modifier = Modifier.padding(top = 2.dp, bottom = 6.dp))
            points.forEach { pt ->
                Row(modifier = Modifier.padding(top = 2.dp)) {
                    Text(text = "– ", fontSize = 11.sp, color = MaterialTheme.colorScheme.primary)
                    Text(text = pt, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, lineHeight = 16.sp)
                }
            }
        }
    }
}
